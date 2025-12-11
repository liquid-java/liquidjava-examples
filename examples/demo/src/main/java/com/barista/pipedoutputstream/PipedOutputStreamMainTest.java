package com.barista.pipedoutputstream;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class PipedOutputStreamMainTest {
    public static void main(String[] args) throws IOException{
        //test1();
        //test2();
        //test3();
        //test4();
        //test5();
    }

    public static void test1() throws IOException {
        PipedOutputStream pos = new PipedOutputStream(new PipedInputStream());
        // I cannot connect if PipedInputStream is provided.
        pos.connect(null);
    }

    public static void test2() throws IOException {
        PipedOutputStream pos = new PipedOutputStream(new PipedInputStream());
        pos.close();
        pos.flush();
    }

    public static void test3() throws IOException {
        PipedOutputStream pos = new PipedOutputStream(new PipedInputStream());
        pos.close();
        // I cannot write a bit after close.
        pos.write(1);
    }

    public static void test4() throws IOException {
        PipedOutputStream pos = new PipedOutputStream(new PipedInputStream());
        pos.close();
        byte[] x = {1, 2, 3};
        // I cannot write bits, offsent, and len after close.
        pos.write(x, 2, 3);
    }

    public static void test5() throws IOException {
        PipedOutputStream pos = new PipedOutputStream(new PipedInputStream());
        byte[] x = {1, 2, 3};
        // I cannot have negative values for arguments.
        pos.write(x, -2, -3);
        pos.close();
    }
}
