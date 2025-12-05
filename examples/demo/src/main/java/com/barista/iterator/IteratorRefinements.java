package com.barista.iterator;

import liquidjava.specification.ExternalRefinementsFor;
import liquidjava.specification.StateRefinement;
import liquidjava.specification.StateSet;

@ExternalRefinementsFor("java.util.Iterator")
@StateSet({"start", "checkedNext", "inNext"})
public interface IteratorRefinements<E> {

    @StateRefinement(to="start(this)")
    public void Iterator();

    @StateRefinement(from="start(this)", to="checkedNext(this)")
    @StateRefinement(from="inNext(this)", to="checkedNext(this)")
    public boolean hasNext();

    @StateRefinement(from="checkedNext(this)", to="inNext(this)")
    public E next();

    @StateRefinement(from="inNext(this)", to="start(this)")
    public void remove();

}
