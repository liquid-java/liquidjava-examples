package refined_classes.other;

import java.util.concurrent.TimeUnit;

import liquidjava.specification.*;

@ExternalRefinementsFor("java.util.concurrent.CyclicBarrier")
@Ghost("int parties")
@Ghost("int count")
@RefinementAlias("Await(CyclicBarrier cb) { count(cb) == count(old(cb)) + 1 }")
public interface CyclicBarrierRefinements {

	// ======== Constructors ======== //

	@StateRefinement(to = "parties(this) == parties")
	public abstract void CyclicBarrier(int parties);

	@StateRefinement(to = "parties(this) == parties")
	public abstract void CyclicBarrier(int parties, Runnable barrierAction);

	// ======== Observers ======== //

	@Refinement("return == count(this)")
	public abstract int getNumberWaiting();

	@Refinement("return == parties(this)")
	public abstract int getParties();

	public abstract boolean isBroken();

	// ========= Mutators ========== //

	@StateRefinement(to = "Await(this)")
	public abstract int await() throws InterruptedException;

	@StateRefinement(to = "Await(this)")
	public abstract int await(long timeout, @Refinement("_ != null") TimeUnit unit) throws InterruptedException;

	@StateRefinement(to = "count(this) == 0")
	public abstract void reset();
}
