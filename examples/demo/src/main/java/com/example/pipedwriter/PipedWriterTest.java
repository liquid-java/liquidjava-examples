package com.example.pipedwriter;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

public class PipedWriterTest {
    void test1() throws IOException {
        PipedWriter p = new PipedWriter();
        PipedReader r = new PipedReader();
        p.connect(r);
        p.write('a');
        p.flush();
        p.close();
        r.close();
    }
    void test2() throws IOException {
        PipedReader r = new PipedReader();
        PipedWriter p = new PipedWriter(r);
        char [] arr = {'a', 'b', 'c'};
        p.write(arr, 1, 2);
        p.flush();
        p.close();
        r.close();
    }
    void testFail() throws IOException {
        PipedWriter p = new PipedWriter();
        PipedReader r = new PipedReader();
        p.flush();
        char [] arr = {'a', 'b', 'c'};
        p.write(arr, 2, 2);
        p.close();
        r.close();
    }
}
