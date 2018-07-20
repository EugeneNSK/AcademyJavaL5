package com.jcourse.zonov.httpserver;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;

public class HtmlStringBuilder {

    public static String toHmtlString(File file) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        String filePath = file.getPath();
        String fileName = file.getName();

        stringBuffer
                .append("<tr>" + "\n")
                .append(    "<td><a href=" +"\"" + URLEncoder.encode(filePath, "UTF-8")+ "\"" + ">" + fileName+ "</a></td>" + "\n")
                .append(    "<td>" + Files.getAttribute(file.toPath(),"size") +"</td>" + "\n")
                .append(    "<td>" + Files.getAttribute(file.toPath(),"lastModifiedTime") +"</td>" + "\n")
                .append("</tr>" + "\n");

        return stringBuffer.toString();
    }
}
