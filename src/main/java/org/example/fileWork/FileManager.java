package org.example.fileWork;
import org.example.subjects.StudyGroup;
import org.xml.sax.SAXException;
import java.io.*;
import java.util.ArrayList;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Класс FileManager отвечает за чтение и запись учебных групп в XML-файл.
 */
public class FileManager {
    private final ParserXML parser;
    public FileManager() {
        parser = new ParserXML();
    }

    /**
     * Читает содержимое файла и возвращает его в виде строки.
     * @param filePath путь к файлу.
     * @return содержимое файла в виде строки или пустая строка в случае ошибки.
     */
    public String getContent(String filePath) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла");
        }
        return content.toString();
    }


    /**
     * Читает XML-файл,парсит его с помощью {@link ParserXML}.
     * @param filePath путь к XML-файлу.
     * @return список объектов {@link StudyGroup}, полученных из файла.
     */
    public ArrayList<StudyGroup> read(String filePath) {
        ArrayList<StudyGroup> groups = new ArrayList<>();
        String content = getContent(filePath);
        try {
            groups = parser.parseXml(content);
        } catch (ParserConfigurationException | IOException |IllegalArgumentException e) {
            System.out.println("Ошибка при обработке XML: " + e.getMessage());
        } catch (SAXException e) {
            System.out.println("Ошибка парсинга XML: " + e.getMessage());
        }
        return groups;
    }


    /**
     * Записывает список учебных групп {@link StudyGroup} в XML-файл.
     * Метод создает {@link Transformer}, который преобразует DOM-объект,
     * полученный с помощью {@link ParserXML#createDocument(ArrayList)}, в XML-формат.
     * @param groups  список учебных групп для сохранения.
     * @param filePath путь к XML-файлу, в который будут записаны данные.
     */
    public void write(ArrayList<StudyGroup> groups, String filePath) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            //Устанавливаем параметры, такие как наличие отступа и его размер
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            try (FileOutputStream outputStream = new FileOutputStream((filePath))) {
                transformer.transform(new DOMSource(parser.createDocument(groups)), new StreamResult(outputStream));
            }
            System.out.println("Данные успешно сохранены в " + filePath);
        } catch (ParserConfigurationException | TransformerException |IOException e) {
            System.out.println("Описание ошибки: " + e.getMessage());
        }
    }
}
















