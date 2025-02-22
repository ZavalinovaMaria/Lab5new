package org.example.fileWork;

import org.example.subjects.StudyGroup;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;

import java.io.*;
import java.util.ArrayList;


public class FileManager {
    private final ParserXML parser;

    public FileManager() {
        parser = new ParserXML();

    }


    public String getContent(String filePath) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n"); // Добавляем строку и перенос строки
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString(); // Возвращаем прочитанное содержимое файла
    }

    public ArrayList<StudyGroup> read(String filePath) {
        ArrayList<StudyGroup> groups = new ArrayList<>();

        String content = getContent(filePath);
        try {
            groups = parser.parseXml(content);// решение ищи в парсере  просто парсер
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
        return groups;
    }











/*Алгоритм:
  1. создали поток для чтения файла
  метод read вернет нам строку
  2. Теперь надо из полученной строки выудить обекты и привести типы
  3. на основе вытащенных значений создаем обьекты
 --> я думаю рассмотреть опять шаблон фабрики


 */


    // метод save такой как нужно
    public void saveFile(String data) throws IOException {
        byte[] strToBytes = data.getBytes();
        try (FileOutputStream outputStream = new FileOutputStream(data)) {
            outputStream.write(strToBytes);
        }

    }
}
