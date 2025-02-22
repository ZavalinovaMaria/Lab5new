package org.example.fileWork;

import org.example.subjects.StudyGroup;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ParserXML {

    ParserXML(){}


    public ArrayList<StudyGroup> parseXml(String xmlContent) throws ParserConfigurationException, IOException, SAXException {
        ArrayList<StudyGroup>  groupTest = new ArrayList<>();
        InputStream inputStream = new ByteArrayInputStream(xmlContent.getBytes());
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = documentBuilder.parse(inputStream);

        Node root = document.getDocumentElement(); //studygroup
        NodeList studyGroups = root.getChildNodes(); //создаем список всех групп
        for (int i = 0; i < studyGroups.getLength(); i++) {
            Node group = studyGroups.item(i); // работаем с конкретной группой из списка
            if (group.getNodeType() != Node.TEXT_NODE) {

                 String[] builder = new String[15];
                NodeList groupProps = group.getChildNodes(); // получаем список полей группы
                for (int j = 0; j < groupProps.getLength(); j++) {// идем по полям группы
                    Node element = groupProps.item(j); //отдельное поле
                    if (element.getNodeType() == Node.TEXT_NODE) continue;
                    switch (element.getNodeName()) { // в зависимости от поля
                        case "id"-> {
                            builder[0] = element.getTextContent();
                            System.out.println(builder[0]);
                        }

                        case "name" -> builder[1] = element.getTextContent();
                        case "coordinates" -> {
                            NodeList coordinatesProp = element.getChildNodes();
                            for (int k = 0; k < coordinatesProp.getLength(); k++) {// идем по полям группы
                                Node elementCoordinates = coordinatesProp.item(k);
                                if (elementCoordinates.getNodeType() == Node.TEXT_NODE) continue;
                                switch (elementCoordinates.getNodeName()) {
                                    case "x" -> {builder[2] = elementCoordinates.getTextContent();
                                        System.out.println(builder[2]);}
                                    case "y" -> builder[3] = elementCoordinates.getTextContent();
                                }
                            }
                        }
                        case "creationDate" -> builder[4] = element.getTextContent();
                        case "studentsCount" -> builder[5] = element.getTextContent();
                        case "transferredStudents" -> builder[6] = element.getTextContent();
                        case "formOfEducation" -> builder[7] = element.getTextContent();
                        case "semesterEnum" -> builder[8] = element.getTextContent();
                        case "groupAdmin" -> {
                            NodeList personProp = element.getChildNodes();
                            for (int w = 0; w < personProp.getLength(); w++) {// идем по полям группы
                                Node elementPerson = personProp.item(w);
                                if (elementPerson.getNodeType() == Node.TEXT_NODE) continue;
                                switch (elementPerson.getNodeName()) {
                                    case "name" -> builder[9] = elementPerson.getTextContent();
                                    case "birthday" -> builder[10] = elementPerson.getTextContent();
                                    case "height" -> builder[11] = elementPerson.getTextContent();
                                    case "location" -> {
                                        NodeList locationProp = elementPerson.getChildNodes();
                                        for (int z = 0; z < locationProp.getLength(); z++) {// идем по полям группы
                                            Node elementLocation = locationProp.item(z);
                                            if (elementLocation.getNodeType() == Node.TEXT_NODE) continue;
                                            switch (elementLocation.getNodeName()) {
                                                case "x" -> builder[12] = elementLocation.getTextContent();
                                                case "y" -> builder[13] = elementLocation.getTextContent();
                                                case "z" -> builder[14] = elementLocation.getTextContent();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                }
                StudyGroup newgroup = StudyGroupsFactory.createGroup(builder);
                groupTest.add(newgroup);


            }



        }
        return groupTest;
    }
}
