package com.example.demo.book;

import lombok.*;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


@Data
@ToString
@EqualsAndHashCode
@Entity
@Table(name="book")
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @Column(name="book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (name="ground_id")
    private Long ground_id;
    @Column(name="user_id")
    private Long user_id;
    @Column(name="local_date")
    private String LocalDateTime;
    @Column(name="booked_date")
    private String bookedDate;
    @Column(name="booked_time")
    private String bookedTime;
    @Column(name="rating_enabled")
    private int ratingEnabled;

    public Book( Long ground_id, Long user_id, String bookedDate,String bookedTime) {
        this.ground_id = ground_id;
        this.user_id = user_id;
        this.bookedDate = bookedDate;
        this.bookedTime=bookedTime;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        this.LocalDateTime= formatter.format(date);
        this.ratingEnabled=0;
    }
}
