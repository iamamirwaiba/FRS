package com.example.demo.book;

import lombok.*;

import javax.persistence.Column;

@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseBody {
    private Long id;

    private Long ground_id;

    private Long user_id;

    private String LocalDateTime;

    private String bookedDate;

    private String bookedTime;

    private int ratingEnabled;

    private String name;
}
