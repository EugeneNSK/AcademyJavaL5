package com.jcourse.zonov.exception;

public class Run implements ExceptionGenerator{

    public static void main(String[] args) {

        Run run = new Run();


        try {
            run.generateNullPointerException();
        }
        catch (NullPointerException e){
            System.out.println("Проверка NPE");
            e.printStackTrace();
        }


        try {
            run.generateClassCastException();
        }
        catch (ClassCastException e){
            System.out.println("Проверка ClassCastException");
            e.printStackTrace();
        }


        try {
            run.generateNumberFormatException();
        }
        catch (NumberFormatException e){
            System.out.println("Проверка NumberFormatException");
            e.printStackTrace();
        }


        try {
            run.generateStackOverflowError();
        }
        catch (StackOverflowError e){
            System.out.println("Проверка StackOverflowError");
            e.printStackTrace();
        }


        try {
            run.generateOutOfMemoryError();
        }
        catch (OutOfMemoryError e){
            System.out.println("Проверка OutOfMemoryError");
            e.printStackTrace();
        }



        try {
            run.generateMyException("Сообщение при вызове метода");
        }
        catch (MyException e){
            System.out.println("Проверка своего исключения");
            e.printStackTrace();
            System.out.println(e.getMessage());
        }


    }

    @Override
    public void generateNullPointerException() {
        String s = null;
        int i = s.length();
    }

    @Override
    public void generateClassCastException() {
        Object i = Integer.valueOf(42);
        String s = (String)i;
    }

    @Override
    public void generateNumberFormatException() {
        int num=Integer.parseInt ("TEXT");
    }

    @Override
    public void generateStackOverflowError() {
        generateStackOverflowError();
    }

    @Override
    public void generateOutOfMemoryError() {
        long[] l = new long[Integer.MAX_VALUE];
    }

    @Override
    public void generateMyException(String message) throws MyException {
        throw new MyException(message);
    }
}
