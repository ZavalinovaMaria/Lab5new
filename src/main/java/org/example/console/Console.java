package org.example.console;

import org.example.utils.GroupsCollectionManager;
import org.example.fileWork.FileManager;

import java.io.File;


public class Console {
    /**
     * Метод выполняет начальную настройку и запуск программы.
     * Он проверяет аргументы командной строки, путь к файлу, а также его существование и размер.
     * В случае ошибок выводит соответствующие сообщения.
     * В случае успешной проверки создается и инициализируется менеджер коллекции групп, а затем
     * выполняется команда с использованием {@link Invoker}.
     *
     * @param args Массив строк, содержащий аргументы командной строки. Ожидается, что первым аргументом будет путь к файлу.
     */

    public void toStart(String[] args) {
        String path;
        try {
            path = args[0];
            File file = new File(path);
            if (!file.exists()) {
                System.out.println("Файл с указанной директорией не обнаружен");
                return;
            }
            if (file.length() == 0) {
                System.out.println("Указанный файл пустой");
                return;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Необходимо ввести путь до файла при запуске программы ");
            return;
        }
        if (path.isEmpty()) {
            System.out.println("Передан пустой путь до файла");
            return;
        }
        String firstFilePath = path;
        GroupsCollectionManager collection = new GroupsCollectionManager(new FileManager().read(firstFilePath), firstFilePath);
        Invoker invoker = new Invoker(collection);
        invoker.work();
    }
}
