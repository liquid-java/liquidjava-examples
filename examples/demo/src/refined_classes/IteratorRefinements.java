package refined_classes;

import liquidjava.specification.*;
import java.util.function.Consumer;

@ExternalRefinementsFor("java.util.Iterator")
@Ghost("int removable")
@RefinementAlias("CanRemove(Iterator it) { removable(it) > 0 }")
public interface IteratorRefinements<E> {

	// ======== Observers ======== //
	public abstract boolean hasNext();

	@StateRefinement(to = "removable(this) == removable(old(this)) + 1")
	public abstract E next();

	// ======== Mutators ======== //

	@StateRefinement(from = "CanRemove(this)", to = "removable(this) == removable(old(this)) - 1")
	public abstract void remove();

	public abstract void forEachRemaining(@Refinement("action != null") Consumer<? super E> action);
}

