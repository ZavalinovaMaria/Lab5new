package org.example.console;

import org.example.fileWork.FileManager;

import java.io.File;

public class Console {
    public void toStart(String[] args) {
        String path;
        try {
            path = args[0];
            File file = new File(path);
            if(!file.exists()){
                System.out.println("Файл с указанной директорией не обнаружен");
                return;
            }
            if(file.length() == 0){
                System.out.println("Файл пустой");
                return;
            }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Необходимо ввести путь до файла при запуске программы ");
                return;
            }

            if (path.isEmpty()) {
                System.out.println("Передан пустой файл");
                return;
            }
            String firstFilePath = path;
            GroupsCollectionManager collection = new GroupsCollectionManager(new FileManager().read(firstFilePath),firstFilePath);
            Invoker invoker = new Invoker(collection);
            invoker.work();

    }


}
