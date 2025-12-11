package com.vector;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

import liquidjava.specification.ExternalRefinementsFor;
import liquidjava.specification.Refinement;
import liquidjava.specification.RefinementAlias;
import liquidjava.specification.StateRefinement;
import liquidjava.specification.StateSet;

@ExternalRefinementsFor("java.util.Vector")
@RefinementAlias("Index(int x) {x >= 0}")
@StateSet({"empty", "maybeElements"})
public interface VectorRefinements<E> {

    // ######### Constructors ####### //
    @StateRefinement(to="empty(this)")
    public void Vector();

    @StateRefinement(to="empty(this)")
    public void Vector(@Refinement("_ >= 0")int initialCapacity);

    @StateRefinement(to="empty(this)")
    public void Vector(@Refinement("_ >= 0") int initialCapacity, @Refinement("_ >= 0") int capacityIncrement);

    @StateRefinement(to="maybeElements(this)")
    public void Vector(Collection<? extends E> c);

    // ######### Adders ####### //

    @StateRefinement(from="empty(this)", to="maybeElements(this)")
    @StateRefinement(from="maybeElements(this)")
    public void setElementAt(E obj, @Refinement("Index(_)")int index);

    @StateRefinement(from="empty(this)", to="maybeElements(this)")
    @StateRefinement(from="maybeElements(this)")
    public void insertElementAt(E obj, @Refinement("Index(_)") int index);


    @StateRefinement(from="empty(this)", to="maybeElements(this)")
    @StateRefinement(from="maybeElements(this)")
    public void addElement(E obj);

    @StateRefinement(from="empty(this)", to="maybeElements(this)")
    @StateRefinement(from="maybeElements(this)")    
    public E set(@Refinement("Index(_)") int index, E element);


    @StateRefinement(from="empty(this)", to="maybeElements(this)")
    @StateRefinement(from="maybeElements(this)")        
    public boolean add(E e);

    @StateRefinement(from="empty(this)", to="maybeElements(this)")
    @StateRefinement(from="maybeElements(this)")    
    public void add(@Refinement("Index(_)") int index, E element);

    @StateRefinement(from="empty(this)", to="maybeElements(this)")
    @StateRefinement(from="maybeElements(this)")  
    public boolean addAll(Collection<? extends E> c);

    @StateRefinement(from="empty(this)", to="maybeElements(this)")
    @StateRefinement(from="maybeElements(this)")  
    public boolean addAll(@Refinement("Index(_)") int index, Collection<? extends E> c);



    // ######### Removers ####### //

    @StateRefinement(from="maybeElements(this)")
    public void removeElementAt(@Refinement("Index(_)")int index);

    @StateRefinement(from="maybeElements(this)")
    public boolean removeElement(Object obj);

    @StateRefinement(from="maybeElements(this)")
    public boolean remove(Object o);

    @StateRefinement(from="maybeElements(this)")    
    public E remove(@Refinement("Index(_)")int index);

    @StateRefinement(from="maybeElements(this)")    
    public boolean removeAll(Collection<?> c);

    @StateRefinement(from="maybeElements(this)")   
    public boolean retainAll(Collection<?> c);

    @StateRefinement(from="maybeElements(this)")   
    public boolean removeIf(Predicate<? super E> filter);


    @StateRefinement(from="maybeElements(this)")   
    public void replaceAll(UnaryOperator<E> operator);


    // ######### Clear ####### //
    @StateRefinement(to="empty(this)")
    public void removeAllElements();

    @StateRefinement(to="empty(this)")
    public void clear();

    // ######### Getters ####### //
    @StateRefinement(from="maybeElements(this)")
    public E elementAt(@Refinement("Index(_)")int index);

    @StateRefinement(from="maybeElements(this)")
    public E firstElement();

    @StateRefinement(from="maybeElements(this)")
    public E lastElement();

    @StateRefinement(from="maybeElements(this)")
    @Refinement("_ >= -1")
    public int indexOf(Object o);

    @StateRefinement(from="maybeElements(this)")
    @Refinement("_ >= -1")
    public int indexOf(Object o, @Refinement("Index(_)") int index);
    
    @StateRefinement(from="maybeElements(this)")
    @Refinement("_ >= -1")
    public int lastIndexOf(Object o);

    @StateRefinement(from="maybeElements(this)")
    @Refinement("_ >= -1")
    public int lastIndexOf(Object o, @Refinement("Index(_)") int index);

    @StateRefinement(from="maybeElements(this)")
    public E get(@Refinement("Index(_)") int index);

    @StateRefinement(from="maybeElements(this)")
    public List<E> subList(@Refinement("Index(_)")int fromIndex, @Refinement("Index(_) && fromIndex <= toIndex")int toIndex);

    @StateRefinement(from="maybeElements(this)")
    public boolean containsAll(Collection<?> c);


    // ######### Others ####### //
    public void setSize(@Refinement("_ >= 0") int newSize);
    public ListIterator<E> listIterator(@Refinement("Index(_)")int index);

    // public void copyInto(Object[] anArray)
    // public void trimToSize()
    // public void ensureCapacity(int minCapacity);
    // public int capacity()
    // public int size()
    // public boolean isEmpty()
    // public Enumeration<E> elements()
    // public boolean contains(Object o)
    // public Object clone()
    // public Object[] toArray()
    // public <T> T[] toArray(T[] a)
    // public boolean equals(Object o)
    // public int hashCode()
    // public String toString()
    // public ListIterator<E> listIterator()
    // public Iterator<E> iterator()
    // public void forEach(Consumer<? super E> action)
    // public Spliterator<E> spliterator()
    // protected void removeRange(int fromIndex, int toIndex) ;



}
