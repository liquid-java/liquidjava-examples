// Protocols in the wild
package refined_classes.in_papers;

import liquidjava.specification.*;
import java.io.IOException;
import java.io.OutputStream;

@ExternalRefinementsFor("java.io.OutputStream")
@StateSet({ "open", "closed" })
public interface OutputStreamRefinements {

	// ======== Constructors ======== //

	@StateRefinement(to = "open(this)")
	public abstract void OutputStream();

	// ======== Observers ======== //

	@Refinement("return != null")
	public abstract OutputStream nullOutputStream();

	@StateRefinement(from = "open(this)")
	public abstract void write(int b) throws IOException;

	@StateRefinement(from = "open(this)")
	public abstract void write(@Refinement("b != null") byte[] b) throws IOException;

	@StateRefinement(from = "open(this)")
	public abstract void write(@Refinement("b != null") byte[] b, @Refinement("off >= 0") int off, @Refinement("len >= 0") int len) throws IOException;

	@StateRefinement(from = "open(this)")
	public abstract void flush() throws IOException;

	// ======== Mutators ======== //

	@StateRefinement(from = "open(this)", to = "closed(this)")
	public abstract void close() throws IOException;
}
