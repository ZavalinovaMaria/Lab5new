package org.example.subjects;

import java.time.LocalDateTime;

public class Person {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private java.time.LocalDateTime birthday; //Поле не может быть null
    private long height; //Значение поля должно быть больше 0
    private Location location; //Поле может быть null

    public Person(String name, LocalDateTime birthday, long height, Location location) {
        this.name = name;
        this.birthday = birthday;
        this.height = height;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public long getHeight() {
        return height;
    }

    public Location getLocation() {
        return location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public void setHeight(long height) {
        this.height = height;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", birthday=" + birthday +
                ", height=" + height +
                ", location=" + location +
                '}';
    }
}
