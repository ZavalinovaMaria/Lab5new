package org.example.fileWork;
import org.example.subjects.StudyGroup;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ParserToXML {
    ParserToXML(){}
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
