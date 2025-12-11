package com.vector;

import java.util.Vector;

public class Test {

    void test1(){
         Vector<Integer> v = new Vector<>();
         v.remove(10);
    }


    void test2(){
         Vector<Integer> v = new Vector<>();
         v.add(9);
         v.set(0, 10);
         v.elementAt(1);
         v.lastElement();
         v.clear();
         v.lastElement();
    }
    
     void test3(){
         Vector<Integer> v = new Vector<>();
         v.add(9);
         v.set(0, 10);
         v.elementAt(1);
         v.lastElement();
         v.subList(10,1);
    }

    void test4(){
         Vector<Integer> v = new Vector<>();
         v.add(9);
         v.add(0, 11);
         v.insertElementAt(0, 0);
         v.removeAllElements();
         v.get(0);
    }

    void test5(){
         Vector<Integer> v = new Vector<>();
         v.add(9);
         v.add(0, 11);
         v.insertElementAt(0, 0);
         v.removeAllElements();
         v.add(0);
         v.firstElement();
         v.elementAt(0);

    }
}
