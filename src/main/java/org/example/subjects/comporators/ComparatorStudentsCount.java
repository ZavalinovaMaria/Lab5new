package org.example.subjects.comporators;

import org.example.subjects.StudyGroup;

import java.util.Comparator;

public class ComparatorStudentsCount implements Comparator<StudyGroup> {
    @Override
    public  int compare(StudyGroup g1,StudyGroup g2) {
        return (int) (g1.getStudentsCount()-g2.getStudentsCount());
    }

}
