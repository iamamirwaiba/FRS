package com.example.demo.book;


import lombok.*;

@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Data
public class BookRequest {
    private final Long ground_id;
    private final Long user_id;
    private final String BookedDate;
    private final String BookedTime;

}
