package org.example.subjects.builders;

import java.time.DateTimeException;
import java.time.LocalDateTime;

public class NumberBuilder {
    public static Double  buildCoordX(String x){
        Double output = x != null ? (Double.parseDouble(x) > -951 ? Double.parseDouble(x) : null) :
                null;
        return output;
    }
    public static int  buildCoordY(String y){
        int output = y != null && !y.trim().isEmpty()? Integer.parseInt(y): -1;
        return output;
    }
    public static Integer buildstudentsCount(String count){
    Integer output = count != null && Integer.parseInt(count) > 0 ? Integer.parseInt(count) : null;
    return output;
    }
    public static int buildTransferredStudents(String count){
        int output = count != null && Integer.parseInt(count) > 0 ? Integer.parseInt(count) : -1;
        return output;
    }
    public static long buildHeight(String height){
    long output = height != null && Long.parseLong(height) > 0 ? Long.parseLong(height) : -1;
    return output;}
    public static long buildLocationY(String y){
        long output = y != null  && !y.trim().isEmpty() ? Integer.parseInt(y):-1 ;
        return output;}
    public static double buildLocationX(String x){
        double output = x != null  && !x.trim().isEmpty() ? Double.parseDouble(x):-1 ;
        return output;}
    public static float buildLocationZ(String z){
        float output = z != null  && !z.trim().isEmpty() ? Float.parseFloat(z):-1 ;
        return output;}
    public static int buildPersonBirthdayYear(String count){
        int output = count != null && Integer.parseInt(count) < LocalDateTime.now().getYear()  ? Integer.parseInt(count) : -1;
        return output;
    }
    public static int buildPersonBirthdayMonth(String count){
        int output = count != null && Integer.parseInt(count) <= 12 && Integer.parseInt(count) >=1   ? Integer.parseInt(count) : -1;
        return output;
    }
    public static int buildPersonBirthdayDay(String count){
        int output = count != null && Integer.parseInt(count) <= 31 && Integer.parseInt(count) >=1   ? Integer.parseInt(count) : -1;
        return output;
    }
    public static int buildPersonBirthdayHour(String count){
        int output = count != null && Integer.parseInt(count) <= 23 && Integer.parseInt(count) >=0  ? Integer.parseInt(count) : -1;
        return output;
    }
    public static int buildPersonBirthdayMinute(String count){
        int output = count != null && Integer.parseInt(count) <= 59 && Integer.parseInt(count) >=0   ? Integer.parseInt(count) : -1;
        return output;
    }



}

