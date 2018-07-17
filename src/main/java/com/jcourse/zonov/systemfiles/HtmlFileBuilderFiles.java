package com.jcourse.zonov.systemfiles;

import java.io.*;
import java.nio.file.Path;
import java.util.List;

public class HtmlFileBuilderFiles {

    public static void toHtmlFile(List<File> list) throws IOException {
        try (Writer out = new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream("index.html")),"UTF-16")) {

            StringBuffer sb = new StringBuffer();

            out.write("<html>" + "\n");
            out.write(         "<head>" + "\n");
            out.write(             "<title>" + "\n");
            out.write(                 "HTML autogenerated file" + "\n");
            out.write(             "</title>" + "\n");
            out.write(         "</head>" + "\n");
            out.write(         "<body>" + "\n");

            out.write(             "<table border = 0>" + "\n");

            out.write(                  "<tr>" + "\n");
            out.write(                      "<td><a href=" + "\""+ list.get(0).getParent()+ "\\"+ "\""+"><b>[Родительский каталог]</b></a></td>" + "\n");
            out.write(                  "</tr>" + "\n");

            out.write(                 "<td><b>-----------</b></td>" + "\n");

            out.write(                  "<tr>" + "\n");
            out.write(                      "<td><b>[Название]</b></td>" + "\n");
            out.write(                      "<td><b>[Размер]</b></td>" + "\n");
            out.write(                      "<td><b>[Дата последнего изменения]</b></td>" + "\n");
            out.write(                  "</tr>" + "\n");

            for (int i =0; i<list.size(); i++){
                out.write(                     HtmlStringBuilderFiles.toHmtlString(list.get(i)) + "\n");
            }

            out.write(             "</table>" + "\n");
            out.write(         "</body>" + "\n");
            out.write(    "</html>" + "\n");
        }

    }

}


