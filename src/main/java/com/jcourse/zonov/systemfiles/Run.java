package com.jcourse.zonov.systemfiles;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Run{

    public static void main(String[] args) throws IOException {

        String name = "D:/Programming/_Books";
        File file = new File(name);
        File[] files = file.listFiles();

        List <File> listFiles = new ArrayList<>(Arrays.asList(files));

        System.out.println("=====Без сортировки========");
        for (File f: listFiles) {
            System.out.println(f.getName());
        }

        System.out.println("=========Comparator=========");
        Collections.sort(listFiles, new FileComparator());

        for (File f: listFiles) {
            System.out.println(f.getName());
        }

//        System.out.println("===========Lambda==============");
//        Collections.sort(listFiles, Comparator.comparing(o->!o.isDirectory()));
//
//        for (File f: listFiles) {
//            System.out.println(f.getName());
//        }


        HtmlFileBuilderFiles.toHtmlFile(listFiles);


//        Path path = Paths.get(name);
//
//        Stream<Path> streamElement = Files.walk(path);
//        List<Path> listElements = streamElement
//                .sorted(Comparator.comparing(Path::getFileName).thenComparing(o->o.toFile().isDirectory()))
//                .collect(Collectors.toList());
//
//
//        for (Path p: listElements){
//            System.out.println(p.getFileName());
//        }
//
//        HtmlFileBuilderPaths.toHtmlFile(listElements);


    }

}
