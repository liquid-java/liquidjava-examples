package refined_classes.other;


import java.util.concurrent.TimeUnit;
import liquidjava.specification.Refinement;
import liquidjava.specification.StateRefinement;
import liquidjava.specification.ExternalRefinementsFor;
import liquidjava.specification.Ghost;
import liquidjava.specification.RefinementAlias;

@Ghost("int availablePermits")
@Ghost("boolean fair")
@RefinementAlias("Acquire(Semaphore s) { availablePermits(s) == availablePermits(old(s)) - 1}")
@RefinementAlias("Release(Semaphore s) { availablePermits(s) == availablePermits(old(s)) + 1 }")
@ExternalRefinementsFor("java.util.concurrent.Semaphore")
public interface SemaphoreRefinements {

	// ======== Constructors ======== //

	@StateRefinement(to = "availablePermits(this) == permits")
	public abstract void Semaphore(int permits);


	@StateRefinement(to = "availablePermits(this) == permits && fair(this) == fair")
	public abstract void Semaphore(int permits, boolean fair);

	// ========= Observers ========= //

	@Refinement("return == availablePermits(this)")
	public abstract int availablePermits();

	@Refinement("return == fair(this)")
	public abstract boolean isFair();

	@Refinement("availablePermits(this) > 0 --> (return == false)")
	public abstract boolean hasQueuedThreads();

	@Refinement("return >= 0 && (return == 0 --> (availablePermits(this) <= 0))")
	public abstract int getQueueLength();

	/*protected abstract Collection<Thread> getQueuedThreads(); Not included because its protected*/

	// ========= Mutators ========== //

	@StateRefinement(to = "Acquire(this)")
	public abstract void acquire() throws InterruptedException;

	@StateRefinement(to = "Acquire(this)")
	public abstract void acquireUninterruptibly();

	@StateRefinement(to = "availablePermits(old(this)) > 0 --> Acquire(this)")
	@Refinement("return == availablePermits(old(this)) > 0") // TODO: check if LiquidJava checks this condition before or after new state is applied
	public abstract boolean tryAcquire();

	public abstract boolean tryAcquire(/* Specification explicitly states it can handle negative values for timeout as 0 */long timeout, @Refinement("_ != null") TimeUnit unit) throws InterruptedException;

	@StateRefinement(to = "Release(this)")
	public abstract void release();

	@StateRefinement(to = "availablePermits(this) == availablePermits(old(this)) - permits")
	public abstract void acquire(@Refinement("_ >= 0") int permits) throws InterruptedException;

	@StateRefinement(to = "availablePermits(this) == availablePermits(old(this)) - permits")
	public abstract void acquireUninterruptibly(@Refinement("_ >= 0") int permits);

	@StateRefinement(to = "availablePermits(this) == availablePermits(old(this)) - permits")
	public abstract boolean tryAcquire(@Refinement("_ >= 0") int permits);

	@StateRefinement(to = "availablePermits(this) == availablePermits(old(this)) - permits")
	public abstract boolean tryAcquire(@Refinement("_ >= 0") int permits, /* Specification explicitly states it can handle negative values for timeout as 0 */long timeout, @Refinement("_ != null") TimeUnit unit);

	@StateRefinement(to = "availablePermits(this) == availablePermits(old(this)) + permits")
	public abstract void release(@Refinement("_ >= 0") int permits);

	@StateRefinement(to = "availablePermits(this) == 0")
	public abstract int drainPermits();
}
