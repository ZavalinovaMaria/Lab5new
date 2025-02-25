package org.example.console;

import org.example.subjects.StudyGroup;

import java.util.*;
import java.rmi.StubNotFoundException;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;

public class GroupsCollection implements Checking {
    private ArrayList<StudyGroup> groups;
    private Date initializationDate;
    private String type;
    private int countOfElements;
    private String internalFileType;

    public GroupsCollection(ArrayList<StudyGroup> groups){
        this.groups = groups;
        type = groups.getClass().getSimpleName();
        internalFileType = "StudyGroups";
        countOfElements = groups.size();
        initializationDate = new Date();
        for(StudyGroup group:groups){
            addNewId(group.getId());
        }
        }

    public void updateData() {
        countOfElements = groups.size();
        initializationDate = new Date();
    }
    public int findIndexById(int id) {
        for (int i = 0; i < groups.size(); i++) {
            if (groups.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    public ArrayList<StudyGroup> getCollection() {
        return groups;
    }
    @Override
    public String toString() {
        return  "\n1. Initialization date: " + initializationDate +
                "\n2. Collection type: " + type +
                "\n3. Internal files type: " + internalFileType +
                "\n4. Amount of elements: " + countOfElements;
    }
    public int getCountOfElements() {
        return countOfElements;
    }



}

