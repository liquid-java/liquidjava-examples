package com.barista.urlconnection;
import java.io.InputStream;
import java.io.OutputStream;

import java.net.URL;
import java.util.List;
import java.util.Map;

import liquidjava.specification.ExternalRefinementsFor;
import liquidjava.specification.Refinement;
import liquidjava.specification.StateRefinement;
import liquidjava.specification.StateSet;

@ExternalRefinementsFor("java.net.URLConnection")
@StateSet({"setup", "connected"})
public interface URLConnectionRefinements {
 
    @StateRefinement(to="setup(this)")
    public void URLConnection(URL url);

    @StateRefinement(from="setup(this)", to="connected(this)")
    public void connect();

    //###################################
    // Setters in Setup phase
    //###################################
    @StateRefinement(from="setup(this)")
    public void setAllowUserInteraction(boolean b);
    @StateRefinement(from="setup(this)")
    public void setDoInput(boolean doinput);
    @StateRefinement(from="setup(this)")
    public void setDoOutput(boolean dooutput);
    @StateRefinement(from="setup(this)")
    public void setIfModifiedSince(@Refinement("_ >= 0")long ifmodifiedsince);
    @StateRefinement(from="setup(this)")
    public void setRequestProperty(String key, String value);
    @StateRefinement(from="setup(this)")
    public void setUseCaches(boolean usecaches);
    @StateRefinement(from="setup(this)")
    public void addRequestProperty(String key, String value);
   
    //###################################
    // Getters in Setup state
    @StateRefinement(from="setup(this)")
    public Map<String,List<String>> getRequestProperties();
    @StateRefinement(from="setup(this)")
    public String getRequestProperty(String key);

    //###################################
    // Get Contents in Connected state
    @StateRefinement(from="connected(this)")
    public Object getContent();
    @StateRefinement(from="connected(this)")
    public Object getContent(Class<?>[] classes);
    @StateRefinement(from="connected(this)")
    public String getContentEncoding();
    
    @StateRefinement(from="connected(this)")
    @Refinement("return >= -1")
    public int getContentLength();
    
    @StateRefinement(from="connected(this)")
    @Refinement("_ >= -1")
    public long getContentLengthLong();
    @StateRefinement(from="connected(this)")
    public String getContentType();

    //###################################
    // Get Headers in Connected state
    @StateRefinement(from="connected(this)")
    public String getHeaderField(int n);
    @StateRefinement(from="connected(this)")
    public String getHeaderField(String n);
    
    @StateRefinement(from="connected(this)")
    @Refinement("_ >= 0")
    public long getHeaderFieldDate(String name, long Default);
    
    @StateRefinement(from="connected(this)")
    public int getHeaderFieldInt(String name, int Default);
    @StateRefinement(from="connected(this)")
    public String getHeaderFieldKey(int n);
    @StateRefinement(from="connected(this)")
    public long getHeaderFieldLong(String name, long Default);
    @StateRefinement(from="connected(this)")
    public Map<String,List<String>> getHeaderFields();


    //###################################
    // Getters in Connected state
    @StateRefinement(from="connected(this)")
    public InputStream getInputStream();
    @StateRefinement(from="connected(this)")
    public OutputStream getOutputStream();
    @StateRefinement(from="connected(this)")
    public long getDate();    

    @StateRefinement(from="connected(this)")
    @Refinement("_ >= 0")
    public long getExpiration();
    
    @StateRefinement(from="connected(this)")
    @Refinement("_ >= 0")
    public long getLastModified();

    @StateRefinement(from="connected(this)")
    @Refinement("_ >= 0")
    public long getIfModifiedSince();




    public void setConnectTimeout(@Refinement("_ >= 0") int timeout);
    public void setReadTimeout(@Refinement("_ >= 0") int timeout);
    @Refinement("_ >= 0") public int getConnectTimeout();   
    @Refinement("_ >= 0") public int getReadTimeout(); 

    // Available in any state - getting states
    // public boolean getDoInput();
    // public boolean getDoOutput();
    // public Permission getPermission();
    // public URL getURL();
    // public boolean getUseCaches();
    // public boolean getAllowUserInteraction();

}