package com.bufferedreader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Test {

    void test1() throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("foo.in"));
        br.close();
        br.read(); //error
    }

    void test2() throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("foo.in"));
        br.read();
        br.mark(-1);
    }

    void test3() throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("foo.in"));
        br.read();
        if(br.markSupported()){
            br.mark(10);
            br.readLine();
        }
    }


    
}
