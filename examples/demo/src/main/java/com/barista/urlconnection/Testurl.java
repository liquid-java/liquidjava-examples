package com.barista.urlconnection;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Timer;


class Testurl {
    void test() throws URISyntaxException, IOException {

        URLExt u = new URLExt(new URI("http://example.com").toURL());
        // u.setDoInput(false); // should not be an error
        // u.getContent(); // should be an error

        Timer t = new Timer();
    }

}


class URLExt extends URLConnection{

    public URLExt(URL url){
        super(url);
    }

    @Override
    public void connect() throws IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'connect'");
    }
    
}