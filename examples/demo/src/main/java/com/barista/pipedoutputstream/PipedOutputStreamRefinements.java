package com.barista.pipedoutputstream;

import java.io.PipedInputStream;

import liquidjava.specification.ExternalRefinementsFor;
import liquidjava.specification.Refinement;
import liquidjava.specification.RefinementAlias;
import liquidjava.specification.StateRefinement;
import liquidjava.specification.StateSet;

@RefinementAlias("Nat(int x) {x >= 0}")
@StateSet({"connected", "created", "closed"})
@ExternalRefinementsFor("java.io.PipedOutputStream")
public interface PipedOutputStreamRefinements {
    
    @StateRefinement(to="connected(this)")
    public void PipedOutputStream(PipedInputStream snk);

    @StateRefinement(to="created(this)")
    public void PipedOutputStream();

    @StateRefinement(from="created(this) || closed(this)", to="connected(this)")
    public void connect(PipedInputStream snk);

    @StateRefinement(from="connected(this)", to="connected(this)")
    public void write(int b);

    @StateRefinement(from="connected(this)", to="connected(this)")
    public void write(byte[] b, @Refinement("Nat(_)") int off, @Refinement("Nat(_)") int len);

    // Testing the execution, I can do flush, no exceptions are raised.
    public void flush();

    @StateRefinement(from="created(this) || connected(this)", to="closed(this)")
    public void close();
}
