package com.barista.iterator;

import java.util.Iterator;

public class TestIt {

    void test(){

        ItImpl i = new ItImpl();
        i.remove();
        

    }
    
}


class ItImpl implements Iterator<Integer>{

    @Override
    public boolean hasNext() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hasNext'");
    }

    @Override
    public Integer next() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'next'");
    }
    
}