package com.jcourse.zonov.systemfiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class HtmlStringBuilderPaths {

    public static String toHmtlString(Path path) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer
                .append("<tr>" + "\n")
                .append(    "<td><a href=" +"\"" +path.toAbsolutePath()+ "\"" + ">" + path.getFileName()+ "</a></td>" + "\n")
                .append(    "<td>" + Files.getAttribute(path,"size") +"</td>" + "\n")
                .append(    "<td>" + Files.getAttribute(path,"lastModifiedTime") +"</td>" + "\n")
                .append("</tr>" + "\n");

        return stringBuffer.toString();
    }
}


// System.out.println("============Path=============");
//        System.out.println(Files.getAttribute(path,"size"));
//        System.out.println(Files.getAttribute(path,"creationTime"));
//        System.out.println(Files.getAttribute(path,"lastAccessTime"));
//        System.out.println(Files.getAttribute(path,"lastModifiedTime"));