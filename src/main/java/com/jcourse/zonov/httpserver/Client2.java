package com.jcourse.zonov.httpserver;

import com.jcourse.zonov.systemfiles.FileComparator;

import javax.activation.MimetypesFileTypeMap;
import java.io.*;
import java.net.Socket;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.util.*;

public class Client2 implements Runnable{

    Socket socket;

    public Client2(Socket s) {
        this.socket = s;
        System.out.println("Конструктор: " + socket);
    }

    @Override
    public void run() {
        try (
                Reader in = new InputStreamReader(new BufferedInputStream(socket.getInputStream()));
                OutputStream out = socket.getOutputStream()){

        StringBuffer sbWeb = new StringBuffer();
        StringBuffer sbHtml = new StringBuffer();

            //читаем первую строку запроса, игнорируем все заголовки которые идут дальше первой строки
            int c;
            while((c =in.read())!=-1 && c!=10 && c!=13){
                sbWeb.append((char)c);
            }
            //получаем команду и ее аргументы
            String data = sbWeb.toString();
            System.out.println("Запрос из браузера: " + data);
            String args[] = data.split(" ");

            String cmd = args[0].trim().toUpperCase(); //Получили GET

            String elementName;
            if (!args[1].substring(1).isEmpty()){
                elementName = URLDecoder.decode(args[1].substring(1));
                System.out.println("Элемент не пуст: " + elementName);
            }
            else {
                elementName = "D:/Programming/_Books";
                System.out.println("Элемент по умолчанию: " + elementName);
            }

//         ================================================

            File file = new File(elementName);
            File [] files = file.listFiles();

            if(cmd.equals("GET")&&file.isDirectory()) {

                List<File> listFiles = new ArrayList<>(Arrays.asList(files));
                Collections.sort(listFiles, new FileComparator());
                sbHtml = HtmlBuilder.toHtmlFile(listFiles);
                String outputString = sbHtml.toString();
                out.write(("HTTP/1.0 200 OK\r\n").getBytes());
                out.write(("Content-Type: text/html;charset=utf-8\r\n").getBytes());
                out.write(("Content-Length: "+outputString.length()+"\r\n").getBytes());
                out.write(("\r\n").getBytes());
                out.write(outputString.getBytes());

            }else if(file.isFile()){

                String contType = new MimetypesFileTypeMap().getContentType(file);
                System.out.println("Определяем contType: " + contType);
                out.write(("HTTP/1.0 200 OK\r\n").getBytes());
                out.write(("Content-Type: " + contType+ ";charset=utf-8\r\n").getBytes());
                out.write(("Content-Length: "+file.length()+"\r\n").getBytes());
                out.write(("\r\n").getBytes());
                Files.copy(file.toPath(),out);
            }


            out.flush();
            sbHtml.setLength(0);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
