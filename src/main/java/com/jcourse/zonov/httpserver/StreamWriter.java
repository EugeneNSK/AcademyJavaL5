package com.jcourse.zonov.httpserver;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

public class StreamWriter {


    public static void write (OutputStream out, String s){

        try {
            out.write(("HTTP/1.0 " + s +"\r\n").getBytes());
            out.write(("Content-Type: text/html;charset=utf-8\r\n").getBytes());
            out.write(("Content-Length: 230\r\n").getBytes());
            out.write(("Connection: Closed\r\n").getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void write (OutputStream out, Exception exc){

//        String outputString = "500 Internal Server Error: " + exc.toString();
        try {
            out.write(("HTTP/1.0 500 Internal Server Error\r\n").getBytes());
            out.write(("Content-Type: text/html;charset=utf-8\r\n").getBytes());
//            out.write(("Content-Length: "+ outputString.length()+"\r\n").getBytes());
            out.write(("Content-Length: 230\r\n").getBytes());
            out.write(("Connection: Closed\r\n").getBytes());
//            out.write(("\r\n").getBytes());
//            out.write(outputString.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void write (OutputStream out, StringBuffer sbHtml){

        try {
            String outputString = sbHtml.toString();
            out.write(("HTTP/1.0 200 OK\r\n").getBytes());
            out.write(("Content-Type: text/html;charset=utf-8\r\n").getBytes());
            out.write(("Content-Length: "+outputString.length()+"\r\n").getBytes());
            out.write(("\r\n").getBytes());
            out.write(outputString.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void write (OutputStream out, File file){

        try {
            String contType = new MimetypesFileTypeMap().getContentType(file);
            out.write(("HTTP/1.0 200 OK\r\n").getBytes());
            out.write(("Content-Type: " + contType+ ";charset=utf-8\r\n").getBytes());
            out.write(("Content-Length: "+file.length()+"\r\n").getBytes());
            out.write(("\r\n").getBytes());
            Files.copy(file.toPath(),out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

