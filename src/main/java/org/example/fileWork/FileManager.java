package org.example.fileWork;

import org.example.subjects.StudyGroup;
import org.xml.sax.SAXException;
import java.io.*;
import java.util.ArrayList;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import javax.xml.parsers.ParserConfigurationException;


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
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла");
        }
        return content.toString();

    }



    public ArrayList<StudyGroup> read(String filePath) {
        ArrayList<StudyGroup> groups = new ArrayList<>();
        String content = getContent(filePath);
        try {
            groups = parser.parseXml(content);
        } catch (ParserConfigurationException | IOException e) {
            System.err.println("Ошибка при обработке XML: " + e.getMessage());
        } catch (SAXException e) {
            System.err.println("Ошибка парсинга XML: " + e.getMessage());
        }
        return groups;
    }



    public void write(ArrayList<StudyGroup> groups, String filePath) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes"); // Красивый XML с отступами
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            try (FileOutputStream outputStream = new FileOutputStream(new File(filePath))) {
                transformer.transform(new DOMSource(parser.createDocument(groups)), new StreamResult(outputStream));
            }

            System.out.println("Данные успешно сохранены в " + filePath);
        } catch ( Exception e) {
            System.out.println("Описание ошибки: " + e.getMessage());

        }
    }

    }
















