package com.sfauto.base.global.utils;

import java.io.IOException;
import java.io.InputStream; 
 

public class MergedInputStream extends InputStream { 
 
    private InputStream ins1, ins2; 
    private boolean eof; 
     

    public MergedInputStream(InputStream ins1, InputStream ins2) { 
        this.ins1 = ins1; 
        this.ins2 = ins2; 
        eof = false; 
    } 
 
 
    public int read() throws IOException { 
        if (!eof && ins1 != null) { 
            int i = ins1.read(); 
            if (i != -1) { 
                return i; 
            } 
            else { 
                eof = true; 
            } 
        } 
        return ins2==null? -1:ins2.read(); 
    } 
}
