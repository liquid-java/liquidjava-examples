package com.pipeouts;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class Test {
    
    void test1() throws Exception{
        PipedOutputStream p = new PipedOutputStream();
        p.connect(new PipedInputStream(10));
        p.write(1000);
        p.flush();
        p.write(0);
        p.close();
        p.write(0);
    }


    void test2() throws Exception{

        PipedInputStream pi = new PipedInputStream(10);
        PipedOutputStream p = new PipedOutputStream(pi);
        p.connect();
    }


    void test3() throws Exception{
        PipedInputStream pi = new PipedInputStream(10);
        PipedOutputStream p = new PipedOutputStream(pi);
        p.write(0);
        p.flush();
        p.flush();
        p.connect(pi);
    }
}
