package com.jcourse.zonov.systemfiles;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class HtmlStringBuilderFiles {

    public static String toHmtlString(File file) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer
                .append("<tr>" + "\n")
                .append(    "<td><a href=" +"\"" +file.getAbsolutePath()+ "\"" + ">" + file.getName()+ "</a></td>" + "\n")
                .append(    "<td>" + Files.getAttribute(file.toPath(),"size") +"</td>" + "\n")
                .append(    "<td>" + Files.getAttribute(file.toPath(),"lastModifiedTime") +"</td>" + "\n")
                .append("</tr>" + "\n");

        return stringBuffer.toString();
    }
}
