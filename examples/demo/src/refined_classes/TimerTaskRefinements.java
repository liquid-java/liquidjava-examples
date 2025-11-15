package refined_classes;

import java.util.TimerTask;

import liquidjava.specification.ExternalRefinementsFor;
import liquidjava.specification.Ghost;
import liquidjava.specification.StateSet;
import liquidjava.specification.StateRefinement;
import liquidjava.specification.Refinement;

@ExternalRefinementsFor("java.util.TimerTask")
@Ghost("boolean executed")
@StateSet({ "active", "cancelled" })
public interface TimerTaskRefinements {

	// ======== Constructors ======== //

	@StateRefinement(to = "active(this) && executed(this) == false")
	public abstract void TimerTask();

	// ========= Observers ========== //

	@Refinement("return >= 0")
	public abstract long scheduledExecutionTime();

	// ========== Mutators ========== //

	@StateRefinement(from = "active(this)")
	public abstract void run();

	@StateRefinement(to = "cancelled(this)")
	@Refinement("executed(this) || cancelled(this) --> (return == false) && (!executed(this) && !cancelled(this) --> (return == true))")
	public abstract boolean cancel();
}

