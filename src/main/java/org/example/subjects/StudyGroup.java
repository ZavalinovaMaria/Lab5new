package org.example.subjects;

import java.time.ZonedDateTime;

/**
 * Класс, объекты которого будут лежать в коллекции
 */
public class StudyGroup implements Comparable<StudyGroup> {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Integer studentsCount; //Значение поля должно быть больше 0, Поле не может быть null
    private int transferredStudents; //Значение поля должно быть больше 0
    private FormOfEducation formOfEducation; //Поле не может быть null
    private Semester semesterEnum; //Поле может быть null
    private Person groupAdmin; //Поле может быть null


    public StudyGroup() {
    }

    /**
     * Создание экземпляра класса
     *
     * @param name                имя
     * @param coordinates         координаты
     * @param creationDate        дата создания
     * @param studentsCount       количество студентов
     * @param transferredStudents количество переведенных студентов
     * @param formOfEducation     форма образования
     * @param semesterEnum        тип семестра
     * @param groupAdmin          староста группы
     */
    public StudyGroup(String name, Coordinates coordinates, ZonedDateTime creationDate, Integer studentsCount, int transferredStudents, FormOfEducation formOfEducation, Semester semesterEnum, Person groupAdmin) {
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.studentsCount = studentsCount;
        this.transferredStudents = transferredStudents;
        this.formOfEducation = formOfEducation;
        this.semesterEnum = semesterEnum;
        this.groupAdmin = groupAdmin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getStudentsCount() {
        return studentsCount;
    }

    public void setStudentsCount(Integer studentsCount) {
        this.studentsCount = studentsCount;
    }

    public int getTransferredStudents() {
        return transferredStudents;
    }

    public void setTransferredStudents(int transferredStudents) {
        this.transferredStudents = transferredStudents;
    }

    public FormOfEducation getFormOfEducation() {
        return formOfEducation;
    }

    public void setFormOfEducation(FormOfEducation formOfEducation) {
        this.formOfEducation = formOfEducation;
    }

    public Semester getSemesterEnum() {
        return semesterEnum;
    }

    public void setSemesterEnum(Semester semesterEnum) {
        this.semesterEnum = semesterEnum;
    }

    public Person getGroupAdmin() {
        return groupAdmin;
    }

    public void setGroupAdmin(Person groupAdmin) {
        this.groupAdmin = groupAdmin;
    }

    @Override
    public int compareTo(StudyGroup group) {
        return group.studentsCount.compareTo(this.studentsCount);
    }

    @Override
    public String toString() {
        return "Group{id=" + id + '\n' + " name='" + name + '\'' + '\n' + " coordinates=" + coordinates + '\n' +
                " creationDate=" + creationDate + '\n' +
                " studentsCount='" + studentsCount + '\'' + '\n' +
                " transferredStudents=" + transferredStudents + '\n' +
                " formOfEducation=" + formOfEducation + '\n' +
                " semestrEnum=" + semesterEnum + '\n' +
                " groupAdmin=" + groupAdmin + '\n' +
                '}';
    }

}

