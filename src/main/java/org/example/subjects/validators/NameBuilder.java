package org.example.subjects.validators;

public class NameBuilder {
    public static String  build(String name){
        String output = name != null && !name.trim().isEmpty()?  name : "Null";
        return output;
    }



}
