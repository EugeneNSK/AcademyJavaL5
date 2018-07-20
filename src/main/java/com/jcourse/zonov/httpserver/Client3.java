package com.jcourse.zonov.httpserver;

import com.jcourse.zonov.systemfiles.FileComparator;

import java.io.*;
import java.net.Socket;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Client3 implements Runnable{

    Socket socket;

    public Client3(Socket s) {
        this.socket = s;
    }

    @Override
    public void run() {
        try (
                Reader in = new InputStreamReader(new BufferedInputStream(socket.getInputStream()));
                OutputStream out = socket.getOutputStream()){

        StringBuffer sbWeb = new StringBuffer();
        StringBuffer sbHtml;

            int c;
            while((c =in.read())!=-1 && c!=10 && c!=13){
                sbWeb.append((char)c);
            }
            String data = sbWeb.toString();
            System.out.println("Запрос из браузера: " + data);
            String args[] = data.split(" ");

            String cmd = args[0].trim().toUpperCase(); //Получили GET


            switch (cmd) {
                case "GET":
//                    System.out.println("GET command from WebClient");
                    break;
                case "HEAD":
//                    System.out.println("HEAD command from WebClient");
                    break;
                default :
                   StreamWriter.write(out, "501 Not Implemented"); //пишем в поток ошибку 501
                    break;
            }

            String elementName;
            if (!args[1].substring(1).isEmpty()){
                elementName = URLDecoder.decode(args[1].substring(1));
                System.out.println("Element from WebClient: " + elementName);
            }
            else {
                elementName = "D:/Programming/_Books";
                System.out.println("Element for default value: " + elementName);
            }

//         ============================I====================

            File file = new File(elementName);

            if(cmd.equals("GET")&&file.exists()&&file.isDirectory()) {
                File [] files = file.listFiles();
                List<File> listFiles = new ArrayList<>(Arrays.asList(files));
                Collections.sort(listFiles, new FileComparator());

                //блок проверки на index.html
                for (File f: files){
                    if(f.getName().equals("index.html")){
                        System.out.println("В папке: " + file.getName() + " файл index.html существует");
                    }
                }

                sbHtml = HtmlBuilder.toHtmlFile(listFiles); //Генерируем HTML и заполняем StringBuff
                StreamWriter.write(out,sbHtml); //пишем в поток HTML doc

            }else if(file.exists()&&file.isFile()){
                StreamWriter.write(out,file); //пишем в поток файл

            }else if(!file.getName().equals("favicon.ico")){
                StreamWriter.write(out, "404 Not Found"); //пишем в поток ошибку 404
            }


            out.flush();
            System.out.println("*********************");

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
