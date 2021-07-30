package com.example.demo.rating;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;


@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name="rating")
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="rating_id")
    private Long id;
    @Column(name="rating_value")
    private int ratingValue;
    @Column(name="book_id")
    private Long bookId;
    @Column(name="rated_date")
    private String ratedDate;

    public Rate(int ratingValue, Long bookId) {
        this.ratingValue = ratingValue;
        this.bookId = bookId;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        this.ratedDate=formatter.format(date);
    }
}
