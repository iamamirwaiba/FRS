package com.example.demo.Demo;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class demo1 {
    public static void main(String[] args) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String currentDate=formatter.format(date);
        String year=currentDate.substring(0,10);
        System.out.println(year);
        String hour=currentDate.substring(11,13);
        System.out.println(hour);

    }
}
