package refined_classes;

import java.util.TimerTask;
import java.util.Date;

import liquidjava.specification.ExternalRefinementsFor;
import liquidjava.specification.StateSet;
import liquidjava.specification.StateRefinement;
import liquidjava.specification.Refinement;

@ExternalRefinementsFor("java.util.Timer")
@StateSet({ "active", "cancelled" })
public interface TimerRefinements {

	// ======== Constructors ======== //

	@StateRefinement(to = "active(this)")
	public abstract void Timer();

	@StateRefinement(to = "active(this)")
	public abstract void Timer(boolean isDaemon);

	@StateRefinement(to = "active(this)")
	public abstract void Timer(@Refinement("name != null") String name);

	@StateRefinement(to = "active(this)")
	public abstract void Timer(@Refinement("name != null") String name, boolean isDaemon);

	// ========= Observers ========== //

	@StateRefinement(from = "active(this)")
	public abstract void schedule(@Refinement("task != null") TimerTask task, @Refinement("_ >= 0") long delay);

	@StateRefinement(from = "active(this)")
	public abstract void schedule(@Refinement("task != null") TimerTask task, @Refinement("time != null") Date time);

	@StateRefinement(from = "active(this)")
	public abstract void schedule(@Refinement("task != null") TimerTask task, @Refinement("_ >= 0") long delay,
			@Refinement("_ > 0") long period);

	@StateRefinement(from = "active(this)")
	public abstract void schedule(@Refinement("task != null") TimerTask task,
			@Refinement("firstTime != null") Date firstTime, @Refinement("_ > 0") long period);

	@StateRefinement(from = "active(this)")
	public abstract void scheduleAtFixedRate(@Refinement("task != null") TimerTask task,
			@Refinement("_ >= 0") long delay, @Refinement("_ > 0") long period);

	@StateRefinement(from = "active(this)")
	public abstract void scheduleAtFixedRate(@Refinement("task != null") TimerTask task,
			@Refinement("firstTime != null") Date firstTime, @Refinement("_ > 0") long period);

	@StateRefinement(from = "active(this) || cancelled(this)")
	@Refinement("return >= 0")
	public abstract int purge();


	// ========== Mutators ========== //

	@StateRefinement(to = "cancelled(this)")
	public abstract void cancel();
}

