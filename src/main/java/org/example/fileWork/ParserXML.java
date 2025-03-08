package org.example.fileWork;

import org.example.subjects.StudyGroup;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ParserXML {
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
                initializePossibleNullFields(builder);
                NodeList groupProps = group.getChildNodes(); // получаем список полей группы
                for (int j = 0; j < groupProps.getLength(); j++) {// идем по полям группы
                    Node element = groupProps.item(j); //отдельное поле
                    if (element.getNodeType() == Node.TEXT_NODE) continue;
                    switch (element.getNodeName()) { // в зависимости от поля
                        case "id"-> builder[0] = element.getTextContent();
                        case "name" -> builder[1] = element.getTextContent();
                        case "coordinates" -> {
                            NodeList coordinatesProp = element.getChildNodes();
                            for (int k = 0; k < coordinatesProp.getLength(); k++) {// идем по полям группы
                                Node elementCoordinates = coordinatesProp.item(k);
                                if (elementCoordinates.getNodeType() == Node.TEXT_NODE) continue;
                                switch (elementCoordinates.getNodeName()) {
                                    case "x" -> builder[2] = elementCoordinates.getTextContent();
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
                if(newgroup.getId() != 0) {groupTest.add(newgroup);}
            }
        }
        return groupTest;
    }

    private void initializePossibleNullFields(String[] builder) {
        builder[8] = null;  // name
        builder[9] = null;  // name
        builder[10] = null; // birthday
        builder[11] = null; // height
        builder[12] = null; // location.x
        builder[13] = null; // location.y
        builder[14] = null; // location.z
    }

    private void appendChild(Document document, Element parent, String name, String value) {
        Element element = document.createElement(name);
        element.appendChild(document.createTextNode(value));
        parent.appendChild(element);
    }
    public Document createDocument(ArrayList<StudyGroup> studyGroups) throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();

        // Корневой элемент <StudyGroups>
        Element rootElement = document.createElement("StudyGroups");
        document.appendChild(rootElement);

        for (StudyGroup group : studyGroups) {
            Element groupElement = document.createElement("StudyGroup");
            rootElement.appendChild(groupElement);

            appendChild(document, groupElement, "id", String.valueOf(group.getId()));
            appendChild(document, groupElement, "name", group.getName());

            // Coordinates
            Element coordinatesElement = document.createElement("coordinates");
            groupElement.appendChild(coordinatesElement);
            appendChild(document, coordinatesElement, "x", String.valueOf(group.getCoordinates().getX()));
            appendChild(document, coordinatesElement, "y", String.valueOf(group.getCoordinates().getY()));

            appendChild(document, groupElement, "creationDate", group.getCreationDate().format(DateTimeFormatter.ISO_ZONED_DATE_TIME));
            appendChild(document, groupElement, "studentsCount", String.valueOf(group.getStudentsCount()));
            appendChild(document, groupElement, "transferredStudents", String.valueOf(group.getTransferredStudents()));
            appendChild(document, groupElement, "formOfEducation", group.getFormOfEducation().name());

            // Semester (может быть null)
            if (group.getSemesterEnum() != null) {
                appendChild(document, groupElement, "semesterEnum", group.getSemesterEnum().name());
            }

            // GroupAdmin (может быть null)
            if (group.getGroupAdmin() != null) {
                Element adminElement = document.createElement("groupAdmin");
                groupElement.appendChild(adminElement);
                appendChild(document, adminElement, "name", group.getGroupAdmin().getName());
                appendChild(document, adminElement, "birthday", group.getGroupAdmin().getBirthday().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                appendChild(document, adminElement, "height", String.valueOf(group.getGroupAdmin().getHeight()));

                // Location (может быть null)
                if (group.getGroupAdmin().getLocation() != null) {
                    Element locationElement = document.createElement("location");
                    adminElement.appendChild(locationElement);
                    appendChild(document, locationElement, "x", String.valueOf(group.getGroupAdmin().getLocation().getX()));
                    appendChild(document, locationElement, "y", String.valueOf(group.getGroupAdmin().getLocation().getY()));
                    appendChild(document, locationElement, "z", String.valueOf(group.getGroupAdmin().getLocation().getZ()));
                }
            }
        }
        return document;
    }

}
