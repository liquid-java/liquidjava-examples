package com.barista.urlconnection;

import java.io.IOException;
import java.net.URLConnection;

public class Testurl {

    void test(){

        URLExt u = new URLExt();
        u.setDoInput(false); // should not be an error
        u.getContent(); // should be an error
    }
    
}

class URLExt extends URLConnection{

    public URLExt(){
        super(null);
    }

    @Override
    public void connect() throws IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'connect'");
    }
    
}
