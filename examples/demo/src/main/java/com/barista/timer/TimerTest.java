package com.barista.timer;

import java.util.Timer;

public class TimerTest {
    public static void main(String[] args) {
        //test1();
        //test2();
        //test3();
        //test4();
        //test5();
    }

    public static void test1() {
        Timer t = new Timer();
        t.schedule(null, -1);
    }

    public static void test2() {
        Timer t = new Timer();
        t.schedule(null, 1, 0);
    }
}
