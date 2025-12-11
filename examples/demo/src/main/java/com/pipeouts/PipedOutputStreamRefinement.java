package com.pipeouts;

import java.io.PipedInputStream;

import liquidjava.specification.ExternalRefinementsFor;
import liquidjava.specification.Refinement;
import liquidjava.specification.StateRefinement;
import liquidjava.specification.StateSet;

@ExternalRefinementsFor("java.io.PipedOutputStream")
@StateSet({"start", "connected", "closed"})
public interface PipedOutputStreamRefinement {

    @StateRefinement(to="start(this)")
    public void PipedOutputStream();

    @StateRefinement(to="connected(this)")
    public void PipedOutputStream(PipedInputStream snk);

    @StateRefinement(from="start(this)", to="connected(this)")
    public void connect(PipedInputStream snk);

    @StateRefinement(from="connected(this)")
    public void write(int b);
    
    @StateRefinement(from="connected(this)")
    public void write(byte[] b, @Refinement("_ >= 0") int off, @Refinement("_ >= 0") int len);

    @StateRefinement(from="connected(this)")
    public void flush();

    @StateRefinement(from="connected(this)", to="closed(this)")
    public void close();


}
