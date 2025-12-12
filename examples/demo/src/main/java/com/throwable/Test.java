
package com.throwable;


public class Test{

    void test1(){
        Throwable t = new Throwable();
        t.getCause();
    }

    void test2(){
        Throwable t = new Throwable("Example");
        t.getMessage();
        t.initCause(null);
        t.getCause();
    }

    void test3(){
        Throwable t = new Throwable("Example", new Throwable());
        t.getMessage();
        t.initCause(null);
        t.getCause();
    }

    void test4(){
        Throwable originT = new Throwable();
        Throwable t = new Throwable(originT); // should be an error but its not - not sure why
        t.initCause(null);
        t.getCause();
    }

}