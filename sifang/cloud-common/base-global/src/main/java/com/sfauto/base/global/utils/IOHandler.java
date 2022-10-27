package com.sfauto.base.global.utils;

import java.io.*;
import java.nio.charset.Charset; 
 
public final class IOHandler { 
 
    private static final int BUFFER_SIZE = 512; 
     
    private IOHandler() { 
    } 
 

    public static InputStream merge(InputStream ins1, InputStream ins2) { 
        return new MergedInputStream(ins1, ins2); 
    } 
     

    public static void pipe(InputStream ins, OutputStream out) throws IOException { 
        byte[] buffer = new byte[BUFFER_SIZE]; 
 
        int len = ins.read(buffer); 
        while (len != -1) { 
            out.write(buffer, 0, len); 
            len = ins.read(buffer); 
        } 
        out.flush(); 
    } 
     
    public static void pipe(Reader reader, Writer writer) throws IOException { 
        char[] buffer = new char[BUFFER_SIZE]; 
 
        int len = reader.read(buffer); 
        while (len != -1) { 
            writer.write(buffer, 0, len); 
            len = reader.read(buffer); 
        } 
        writer.flush(); 
    } 
 
    public static byte[] readBytes(InputStream ins) throws IOException { 
        ByteArrayOutputStream out = new ByteArrayOutputStream(ins.available()); 
        pipe(ins, out); 
        byte[] bytes = out.toByteArray(); 
        out.close(); 
        return bytes; 
    } 
     
    public static byte[] readBytes(Reader reader, Charset charset) throws IOException { 
        ByteArrayOutputStream out = new ByteArrayOutputStream(); 
        OutputStreamWriter writer = new OutputStreamWriter(out, charset);  
        pipe(reader, writer); 
        byte[] bytes = out.toByteArray(); 
        writer.close(); 
        out.close(); 
        return bytes; 
    } 
     
    public static String readString(InputStream ins, Charset charset) throws IOException { 
        InputStreamReader reader; 
        if (charset == null) { 
            reader = new InputStreamReader(ins); 
        } 
        else { 
            reader = new InputStreamReader(ins, charset); 
        } 
        String s = readString(reader); 
        reader.close(); 
        return s; 
    } 
     
    public static String readString(Reader reader) throws IOException { 
        StringWriter writer = new StringWriter(); 
        pipe(reader, writer); 
        String s = writer.toString(); 
        writer.close(); 
        return s; 
    } 
     
    public static void writeBytes(byte[] bytes, OutputStream out) throws IOException { 
        ByteArrayInputStream ins = new ByteArrayInputStream(bytes);  
        pipe(ins, out); 
        ins.close(); 
    } 
     
    public static void writeBytes(byte[] bytes, Writer writer, Charset charset) throws IOException { 
        ByteArrayInputStream ins = new ByteArrayInputStream(bytes); 
        InputStreamReader reader; 
        if (charset == null) { 
            reader = new InputStreamReader(ins);  
        } 
        else { 
            reader = new InputStreamReader(ins, charset); 
        } 
        pipe(reader, writer); 
        reader.close(); 
        ins.close(); 
    } 
     
    public static void writeString(String s, OutputStream out, Charset charset) throws IOException { 
        OutputStreamWriter writer; 
        if (charset == null) { 
            writer = new OutputStreamWriter(out); 
        } 
        else { 
            writer = new OutputStreamWriter(out, charset); 
        } 
        writeString(s, writer); 
        writer.close(); 
    } 
     
    public static void writeString(String s, Writer writer) throws IOException { 
        StringReader reader = new StringReader(s); 
        pipe(reader, writer); 
        reader.close(); 
    } 
}
