package org.example.console;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Console {

    public static ArrayList<Integer> idStoragee = new ArrayList<>();
    public static String firstFilePath;
    public Console(){
    }


    public void toStart(String[] args) {
        String path;
        try {
            path = args[0];
            File file = new File(path);
            if(!file.exists()){
                System.out.println("Файл с указанной директорией не обнаружен");
            }
            } catch (NoSuchElementException e) {
                System.out.println("Пустая строка, выход из программы");
                return;
            }

            if (path.isEmpty()) {
                System.out.println("Передан пустой файл");
                return;
            }
            firstFilePath = path;
            Invoker invoker = new Invoker(path);
            invoker.work();
//хуй знает

    }


}
