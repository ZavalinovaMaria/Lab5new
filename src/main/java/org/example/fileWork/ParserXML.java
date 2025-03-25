package org.example.fileWork;

import org.example.exceptions.InvalidValueException;
import org.example.subjects.StudyGroup;
import org.example.subjects.creators.StudyGroupsCreator;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Класс FileManager отвечает за чтение и запись учебных групп в XML-файл.
 */
public class ParserXML {


    /**
     * Метод осуществляет парсинг данных из XML-файла и создает список групп {@link StudyGroup}.
     * В процессе работы метод:
     * <ul>
     *   <li>Парсит XML-строку, преобразуя её в документ XML с использованием {@link DocumentBuilder}.</li>
     *   <li>Извлекает из документа данные для каждой группы и ее полей.</li>
     *   <li>Создает объект {@link StudyGroup} для каждой группы, используя {@link StudyGroupsCreator}.</li>
     *   <li>Проверяет уникальность ID для каждой группы перед добавлением в список.</li>
     * </ul>
     * </p>
     *
     * @param xmlContent строка, содержащая XML-данные, которые необходимо распарсить.
     * @return {@link ArrayList<StudyGroup>} список групп, извлеченных из XML.
     * @throws ParserConfigurationException если произошла ошибка конфигурации XML-парсера.
     * @throws IOException                  если возникли проблемы при работе с входными данными.
     * @throws SAXException                 если произошли ошибки при парсинге XML.
     * @throws IllegalArgumentException     если переданы некорректные данные в XML или при создании объекта {@link StudyGroup}.
     */
    public ArrayList<StudyGroup> parseXml(String xmlContent) throws ParserConfigurationException, IOException, SAXException, IllegalArgumentException {
        StudyGroupsCreator creator = new StudyGroupsCreator();
        ArrayList<StudyGroup> groupTest = new ArrayList<>();
        InputStream inputStream = new ByteArrayInputStream(xmlContent.getBytes());
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = documentBuilder.parse(inputStream);
        Node root = document.getDocumentElement();
        NodeList studyGroups = root.getChildNodes(); //создаем список всех групп
        for (int i = 0; i < studyGroups.getLength(); i++) {
            Node group = studyGroups.item(i); // работаем с конкретной группой из списка
            if (group.getNodeType() != Node.TEXT_NODE) {
                String[] builder = new String[15];
                NodeList groupProps = group.getChildNodes(); // получаем список полей группы
                for (int j = 0; j < groupProps.getLength(); j++) {
                    Node element = groupProps.item(j);
                    if (element.getNodeType() == Node.TEXT_NODE) continue;
                    //в зависимости от тега записываем в нужное место в билдер информацию
                    switch (element.getNodeName()) {
                        case "id" -> builder[0] = element.getTextContent();
                        case "name" -> builder[1] = element.getTextContent();
                        case "coordinates" -> {
                            NodeList coordinatesProp = element.getChildNodes();
                            for (int k = 0; k < coordinatesProp.getLength(); k++) {
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
                            for (int w = 0; w < personProp.getLength(); w++) {// идем по полям человека
                                Node elementPerson = personProp.item(w);
                                if (elementPerson.getNodeType() == Node.TEXT_NODE) continue;
                                switch (elementPerson.getNodeName()) {
                                    case "name" -> builder[9] = elementPerson.getTextContent();
                                    case "birthday" -> builder[10] = elementPerson.getTextContent();
                                    case "height" -> builder[11] = elementPerson.getTextContent();
                                    case "location" -> {
                                        NodeList locationProp = elementPerson.getChildNodes();
                                        for (int z = 0; z < locationProp.getLength(); z++) {
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
                try {
                    StudyGroup newgroup = creator.createGroup(builder, false);
                    if (newgroup.getId() != 0) {
                        groupTest.add(newgroup);
                    }
                } catch (InvalidValueException e) {
                    System.out.println(e.getMessage() + ", продукт не будет собран");
                }
            }
        }
        return groupTest;
    }

    /**
     * Добавляет новый дочерний элемент в родительский элемент в документ XML.
     * Метод создает элемент с указанным именем и значением, затем добавляет его в качестве дочернего элемента
     * к родительскому элементу в предоставленном XML-документе.
     *
     * @param document XML-документ, в котором будет создан новый элемент.
     * @param parent   родительский элемент, к которому будет добавлен новый дочерний элемент.
     * @param name     имя создаваемого элемента.
     * @param value    текстовое значение, которое будет присвоено созданному элементу.
     */
    private void appendChild(Document document, Element parent, String name, String value) {
        Element element = document.createElement(name);
        element.appendChild(document.createTextNode(value));
        parent.appendChild(element);
    }

    /**
     * Создает XML-документ на основе коллекции {@link StudyGroup}.
     * Метод принимает список групп {@link StudyGroup} и создает XML-документ, где каждая группа представлена
     * в виде элемента `<StudyGroup>`. Для каждой группы добавляются её поля, такие как id, name и другие.
     * В случае разрешенного отсутствия некоторых полей, в полученном докумете будует отсутствовать соответствующие теги
     *
     * @param studyGroups список объектов {@link StudyGroup}, которые нужно преобразовать в XML-документ.
     * @return {@link Document} XML-документ, созданный на основе списка групп.
     * @throws ParserConfigurationException если произошла ошибка при конфигурации парсера XML.
     */
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
