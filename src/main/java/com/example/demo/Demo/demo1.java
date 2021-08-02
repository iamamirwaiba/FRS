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
        String day=year.substring(0,2);
        String month=year.substring(3,5);
        String year1=year.substring(6,10);
        System.out.println(day);
        System.out.println(month);
        System.out.println(year1);
        System.out.println(year);
        String hour=currentDate.substring(11,13);
        System.out.println(hour);

    }
}
