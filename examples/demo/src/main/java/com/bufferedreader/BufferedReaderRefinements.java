package com.bufferedreader;

import java.io.Reader;
import java.util.stream.Stream;

import liquidjava.specification.ExternalRefinementsFor;
import liquidjava.specification.Refinement;
import liquidjava.specification.StateRefinement;
import liquidjava.specification.StateSet;

@ExternalRefinementsFor("java.io.BufferedReader")
@StateSet({"start", "closed"})
public interface BufferedReaderRefinements {
    
    @StateRefinement(to="start(this)")
    void BufferedReader(Reader in);

    @StateRefinement(to="start(this)")
    void BufferedReader(Reader in, @Refinement("sz > 0") int sz);

    @StateRefinement(from="start(this)")
    public int read();

    @StateRefinement(from="start(this)")
    @Refinement(" _ >= -1")
    public int read(char[] cbuf, @Refinement("off >= 0") int off, @Refinement("len > 0") int len);

    @StateRefinement(from="start(this)")
    public String readLine();

    @StateRefinement(from="start(this)")
    public boolean ready();

    @StateRefinement(from="start(this)")
    public boolean markSupported();

    @StateRefinement(from="start(this)")
    public void mark(@Refinement("_ >= 0") int readAheadLimit);

    @StateRefinement(from="start(this)")
    public void reset();

    @StateRefinement(from="start(this)")
    public Stream<String> lines();

    @StateRefinement(from="start(this)", to="closed(this)")
    public void close();


}
