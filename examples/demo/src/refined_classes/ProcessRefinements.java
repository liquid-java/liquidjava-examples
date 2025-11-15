package refined_classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import java.util.concurrent.TimeUnit;

import liquidjava.specification.*;

@ExternalRefinementsFor("java.lang.Process")
@StateSet({ "running", "terminated" })
public interface ProcessRefinements {

		// ======== Constructors ======== //

		@StateRefinement(to = "running(this)")
		public abstract void Process();

		// ========= Observers ========== //

		@StateRefinement(from = "running(this)")
		public abstract OutputStream getOutputStream();

		@StateRefinement(from = "running(this)")
		public abstract InputStream getInputStream();

		@StateRefinement(from = "running(this)")
		public abstract InputStream getErrorStream();

		@StateRefinement(from = "running(this)")
		public abstract BufferedReader inputReader();

		@StateRefinement(from = "running(this)")
		public abstract BufferedReader errorReader();

		@StateRefinement(from = "running(this)")
		public abstract BufferedWriter outputWriter();

		@StateRefinement(from = "terminated(this)")
		public abstract int exitValue();

		// ========== Mutators ========== //

		@StateRefinement(from = "running(this)")
		public abstract BufferedReader inputReader(@Refinement("_ != null") Charset charset); // changes state by setting which mutator can be used but this cannot be described in the refinements (read specification)

		@StateRefinement(from = "running(this)")
		public abstract BufferedReader errorReader(@Refinement("_ != null") Charset charset); // changes state by setting which mutator can be used but this cannot be described in the refinements (read specification)

		@StateRefinement(from = "running(this)")
		public abstract BufferedWriter outputWriter(@Refinement("_ != null") Charset charset); // changes state by setting which mutator can be used but this cannot be described in the refinements (read specification)

		@StateRefinement(to = "terminated(this)")
		public abstract int waitFor() throws InterruptedException;

		@StateRefinement(to = "terminated(this)")
		@Refinement("(timeout <= 0) --> (return == false)")
		public abstract boolean waitFor(/* Specification explicitly states it can handle negative values for timeout as 0 */long timeout , @Refinement("_ != null && _ > 0") TimeUnit unit) throws InterruptedException;

		@StateRefinement(/* Unspecified if IllegalStateException is thrown when process is already terminated from = "running(this)",*/ to = "terminated(this)") // Specification states program should terminate although the default implementation does not force it
		public abstract void destroy();

		@StateRefinement(/* Unspecified if IllegalStateException is thrown when process is already terminated from = "running(this)",*/ to = "terminated(this)") // Specification states program should terminate although the default implementation does not force it
		public abstract Process destroyForcibly();

		public abstract boolean supportsNormalTermination();

		@Refinement("return == running(this)")
		public abstract boolean isAlive();

		@Refinement("return >= 0")
		public abstract long pid();

		/*@StateRefinement(from = "running(this)") // Unspecified if method can handle terminated processes or not, seems useless to call this method after termination*/
		public abstract CompletableFuture<Process> onExit();

		/*@StateRefinement(from = "running(this)") // Unspecified if method can handle terminated processes or not, seems useless to call this method after termination*/
		public abstract ProcessHandle toHandle();

		/* Unspecified behavior if process is terminated */
		public abstract ProcessHandle.Info info();

		public abstract Stream<ProcessHandle> children();

		public abstract Stream<ProcessHandle> descendants();
}
