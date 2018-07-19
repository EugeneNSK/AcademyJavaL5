package com.jcourse.zonov.httpserver;


import javax.activation.MimetypesFileTypeMap;
import java.io.*;
import java.net.Socket;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.util.*;

public class Client implements Runnable{

    Socket socket;

    public Client(Socket s) {
        this.socket = s;
        System.out.println("Конструктор: " + socket);
    }

    @Override
    public void run() {
        try (
                Reader in = new InputStreamReader(new BufferedInputStream(socket.getInputStream()));
//                Writer out = new OutputStreamWriter(new BufferedOutputStream(socket.getOutputStream()),"CP1251")){
//                OutputStream out = socket.getOutputStream()){
                OutputStream out = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(new OutputStreamWriter(out, "UTF-8"), true)){

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
            System.out.println("Запрашиваемый элемент: " + args[1].substring(1));

            String elementName;
            if (!args[1].substring(1).isEmpty()){
                elementName = URLDecoder.decode(args[1].trim());
                System.out.println("Элемент не пуст: " + elementName);
            }
            else {
                elementName = "D:/Programming/_Books";
                System.out.println("Элемент по умолчанию: " + elementName);
            }

            File file = new File(elementName);
            File [] files = file.listFiles();
            String outputString="";


            if(cmd.equals("GET")&&file.isDirectory()) {
                System.out.println("=======Зашли в блок условий========");


                List<File> listFiles = new ArrayList<>(Arrays.asList(files));
                Collections.sort(listFiles, Comparator.comparing(o->!o.isDirectory()));
                sbHtml = HtmlBuilder.toHtmlFile(listFiles);

                System.out.println("=======Вышли из в блока условий========");

                // пишем ответ
                outputString = sbHtml.toString();

            }else if(!file.isDirectory()&&file.isFile()){
                String contType = new MimetypesFileTypeMap().getContentType(file);
                System.out.println("Определяем contType: " + contType);
                out.write(("HTTP/1.0 200 OK\r\n").getBytes());
                out.write(("Content-Type: " + contType+ ";charset=utf-8\r\n").getBytes());
                out.write(("Content-Length: "+file.length()+"\r\n").getBytes());
                out.write(("\r\n").getBytes());
                Files.copy(file.toPath(),out);
//                out.write(file.getName().getBytes());

            }

//            //пишем статус ответа
//            out.write("HTTP/1.0 200 OK\r\n");
//            //минимально необходимые заголовки, тип и длина
//            out.write("Content-Type: text/html\r\n");
//            out.write("Content-Length: "+outputString.length()+"\r\n");
//            //пустая строка отделяет заголовки от тела
//            out.write("\r\n");
//            //тело
//            out.write(outputString);

            out.write(("HTTP/1.0 200 OK\r\n").getBytes());
            out.write(("Content-Type: text/html;charset=utf-8\r\n").getBytes());
//            out.write(("Content-Length: "+outputString.length()+"\r\n").getBytes());
            //пустая строка отделяет заголовки от тела
            out.write(("\r\n").getBytes());
            out.write(outputString.getBytes());


            out.flush();
            sbHtml.setLength(0);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
