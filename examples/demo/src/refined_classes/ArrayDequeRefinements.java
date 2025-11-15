package refined_classes;

import liquidjava.specification.RefinementAlias;
import liquidjava.specification.Refinement;
import liquidjava.specification.StateRefinement;
import liquidjava.specification.ExternalRefinementsFor;
import liquidjava.specification.Ghost;

import java.util.Collection;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.ArrayDeque;

@ExternalRefinementsFor("java.util.ArrayDeque")
@Ghost("int size")
@RefinementAlias("Empty(ArrayDeque ad) { size(ad) == 0 }")
@RefinementAlias("Inc(ArrayDeque ad) { size(ad) == size(old(ad)) + 1 }")
@RefinementAlias("Dec(ArrayDeque ad) { size(ad) == size(old(ad)) - 1 }")
// Ensuring that NotEmpty restricts to positive values is important to adhere to
// the cocept of having maximally strict refinements. This is important when
// comparing refinements to make sure that the generated refinement is not
// incorrect when its stricter (by > 0) than the expected refinements (by only
// declaring != 0).
@RefinementAlias("NotEmpty(ArrayDeque ad) { size(ad) > 0 }")
public interface ArrayDequeRefinements<E> {

	// ======== Constructors ======== //

	@StateRefinement(to = "Empty(this)")
	public abstract void ArrayDeque();

	@StateRefinement(to = "Empty(this)")
	public abstract void ArrayDeque(@Refinement("_ > 0") int initialCapacity);

	@StateRefinement(to = "Empty(this) || NotEmpty(this)")
	public abstract void ArrayDeque(@Refinement("c != null") Collection<? extends E> c);

	// ======== Observers ======== //

	@StateRefinement(from = "NotEmpty(this)")
	public abstract E getFirst();

	@StateRefinement(from = "NotEmpty(this)")
	public abstract E getLast();

	@StateRefinement(from = "NotEmpty(this)")
	public abstract E element();

	public abstract E peek();

	@Refinement("return == size(this)")
	public abstract int size();

	@Refinement("Empty(this)")
	public abstract boolean isEmpty();

	public abstract Iterator<E> iterator();

	public abstract Iterator<E> descendingIterator();

	public abstract Spliterator<E> spliterator();

	public abstract void forEach(@Refinement("action != null") Consumer<? super E> action);

	public abstract boolean contains(@Refinement("o != null")/* Implicit condition because specification references o.equals() method that would throw NullPointerException if o is null*/ Object o);

	public abstract Object[] toArray();

	public abstract <T> T[] toArray(@Refinement("array != null") T[] array);

	public abstract ArrayDeque<E> clone();

	// ======== Mutators ======== //

	@StateRefinement(to = "Inc(this)")
	public abstract void addFirst(@Refinement("elem != null") E elem);

	@StateRefinement(to = "Inc(this)")
	public abstract void addLast(@Refinement("elem != null") E elem);

	@StateRefinement(to = "size(this) >= size(old(this))")
	public abstract boolean addAll(@Refinement("c != null") Collection<? extends E> c);

	@StateRefinement(to = "Inc(this)")
	public abstract boolean offerFirst(@Refinement("elem != null") E elem);

	@StateRefinement(to = "Inc(this)")
	public abstract boolean offerLast(@Refinement("elem != null") E elem);

	@StateRefinement(from = "NotEmpty(this)", to = "Dec(this)")
	public abstract E removeFirst();

	@StateRefinement(from = "NotEmpty(this)", to = "Dec(this)")
	public abstract E removeLast();

	// returns null if empty
	@StateRefinement(to = "NotEmpty(old(this)) --> Dec(this)")
	@StateRefinement(to = "Empty(old(this)) --> Empty(this)")
	// Equivalent to:
	// @StateRefinement(to = "NotEmpty(old(this))? (size(this) == size(old(this)) - 1) : Empty(this)")
	public abstract E pollFirst();

	// returns null if empty
	@StateRefinement(to = "NotEmpty(old(this)) --> Dec(this)")
	@StateRefinement(to = "Empty(old(this)) --> Empty(this)")
	// Equivalent to:
	// @StateRefinement(to = "NotEmpty(old(this))? (size(this) == size(old(this)) - 1) : Empty(this)")
	public abstract E pollLast();

	@StateRefinement(to = "size(this) <= size(old(this))")
	public abstract boolean removeFirstOccurrence(@Refinement("o != null")/* Implicit condition because specification references o.equals() method that would throw NullPointerException if o is null*/ Object o);

	@StateRefinement(to = "size(this) <= size(old(this))")
	public abstract boolean removeLastOccurrence(@Refinement("o != null")/* Implicit condition because specification references o.equals() method that would throw NullPointerException if o is null*/ Object o);

	// This implementation of Collection allows duplicate elements and capacity
	// resizing, therefore the element is always added causing a guaranteed size
	// increase and guaranteed return true unless null pointer exception is thrown
	@StateRefinement(to = "Inc(this)")
	@Refinement("return == true")
	// Equivalent to:
	// @Refinement("true")
	public abstract boolean add(@Refinement("elem != null") E elem);

	// This implementation of Queue allows capacity resizing, therefore the element
	// is always added unless null pointer exception is thrown
	@StateRefinement(to = "Inc(this)")
	@Refinement("true")
	// Equivalent to:
	// @Refinement("return == true")
	public abstract boolean offer(@Refinement("elem != null") E elem);

	@StateRefinement(from = "NotEmpty(this)", to = "Dec(this)")
	public abstract E remove();

	@StateRefinement(to = "NotEmpty(old(this)) --> Dec(this)")
	@StateRefinement(to = "Empty(old(this)) --> Empty(this)")
	public abstract E poll();

	@StateRefinement(to = "Inc(this)")
	public abstract void push(@Refinement("elem != null") E elem);

	@StateRefinement(from = "NotEmpty(this)", to = "Dec(this)")
	public abstract E pop();

	@StateRefinement(to = "size(this) <= size(old(this))")
	public abstract boolean removeIf(@Refinement("filter != null") Predicate<? super E> filter);

	@StateRefinement(to = "size(this) <= size(old(this))")
	public abstract boolean removeAll(@Refinement("c != null") Collection<?> c);

	@StateRefinement(to = "size(this) <= size(old(this))")
	public abstract boolean retainAll(@Refinement("c != null") Collection<?> c);

	@StateRefinement(to = "size(this) <= size(old(this))")
	public abstract boolean remove(@Refinement("o != null")/* Implicit condition because specification references o.equals() method that would throw NullPointerException if o is null*/ Object o);

	@StateRefinement(to = "Empty(this)")
	public abstract void clear();
}
