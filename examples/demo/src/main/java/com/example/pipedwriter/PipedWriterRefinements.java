package com.example.pipedwriter;

import java.io.IOException;
import java.io.PipedReader;

import liquidjava.specification.ExternalRefinementsFor;
import liquidjava.specification.Refinement;
import liquidjava.specification.StateRefinement;
import liquidjava.specification.StateSet;

@ExternalRefinementsFor("java.io.PipedWriter")
//@RefinementAlias("Index(int x) {x >= 0}")
@StateSet({"unconnected", "connected", "broken", "closed"})
public interface PipedWriterRefinements {
    @StateRefinement(to="connected(this)")
    public void PipedWriter(PipedReader snk)
                throws IOException;

    @StateRefinement(to="unconnected(this)")
    public void PipedWriter();

    @StateRefinement(from="unconnected(this)", to="connected(this)")
    public void connect(PipedReader snk)
             throws IOException;

    @StateRefinement(from="connected(this)")
    public void write(@Refinement("c >= 0 && c <= 65535") int c)
           throws IOException;

    @StateRefinement(from="connected(this)")
    public void write(char[] cbuf,
                        @Refinement("off >= 0") int off,
                        @Refinement("off >= 0") int len)
           throws IOException;

    @StateRefinement(from="connected(this)")
    public void flush()
           throws IOException;

    @StateRefinement(to="closed(this)")
    public void close()
           throws IOException;
}
