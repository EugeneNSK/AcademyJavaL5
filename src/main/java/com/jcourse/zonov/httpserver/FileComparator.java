package com.jcourse.zonov.httpserver;

import java.io.File;
import java.util.Comparator;

public class FileComparator implements Comparator <File>{

    @Override
    public int compare(File f1, File f2) {
        boolean dir1 = f1.isDirectory();
        boolean dir2 = f2.isDirectory();
        if (!dir1&&dir2){
            return 1;
        }
        if (dir1&&!dir2){
            return -1;
        }
        return f1.getName().compareTo(f2.getName());
    }



}
