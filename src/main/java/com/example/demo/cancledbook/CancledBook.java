package com.example.demo.cancledbook;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name="cancled_book")
public class CancledBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cancledbook_id")
    private Long Id;
    @Column(name="user_id")
    private Long userId;
    @Column(name="ground_id")
    private Long groundId;
    @Column(name="booked_date")
    private String bookedDate;
    @Column(name="booked_time")
    private String bookedTime;
    @Column(name="localdatetime")
    private String localDateTime;


    public CancledBook(Long userId, Long groundId, String bookedDate, String bookedTime) {
        this.userId = userId;
        this.groundId = groundId;
        this.bookedDate = bookedDate;
        this.bookedTime = bookedTime;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        this.localDateTime= formatter.format(date);
    }
}
