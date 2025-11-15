package refined_classes;

import java.util.concurrent.TimeUnit;

import liquidjava.specification.*;

@ExternalRefinementsFor("java.util.concurrent.CountDownLatch")
@RefinementAlias("Zero(CountDownLatch cdl) { count(cdl) == 0 }")
@Ghost("int count")
public interface CountDownLatchRefinements {

	// ======== Constructors ======== //

	@StateRefinement(to = "count(this) == count")
	public abstract void CountDownLatch(int count);

	// ========= Observers ========= //

	@StateRefinement(to = "Zero(this)")
	public abstract void await() throws InterruptedException;

	@StateRefinement(to = "Zero(this)")
	public abstract boolean await(long timeout, @Refinement("_ != null && _ > 0") TimeUnit unit) throws InterruptedException;

	@Refinement("return == count(this)")
	public abstract long getCount();

	/* public abstract String toString(); Not included because its default Object method */
	// ========= Mutators ========== //

	@StateRefinement(to = "count(this) == count(old(this)) - 1")
	public abstract void countDown();
}
