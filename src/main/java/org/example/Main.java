package org.example;
import org.example.console.Console;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        Console console  = new Console();
        System.out.println(LocalDateTime.of(2005,06,30,22,5));
        //2005-06-30T22:05
        console.toStart(args);
    }
}
