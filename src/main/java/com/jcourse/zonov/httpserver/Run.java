package com.jcourse.zonov.httpserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Run {

    public static void main(String[] args) throws IOException {

        ServerSocket s = new ServerSocket(5400);

        while(true){
            Socket clientS = s.accept();
            new Thread(new Client(clientS), "client thread").start();
        }

    }
}
