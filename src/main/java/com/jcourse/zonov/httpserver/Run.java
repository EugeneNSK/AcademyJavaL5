package com.jcourse.zonov.httpserver;

import com.jcourse.zonov.systemfiles.FileComparator;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Run {

    public static void main(String[] args) throws IOException {

        ServerSocket s = new ServerSocket(5400);

        while(true){
            Socket clientS = s.accept();
            new Thread(new Client(clientS), "client thread").start();
        }

    }
}
