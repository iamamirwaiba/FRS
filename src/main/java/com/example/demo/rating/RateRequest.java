package com.example.demo.rating;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;


@RequiredArgsConstructor
@Data
@ToString
@EqualsAndHashCode
public class RateRequest {
    private final Long book_id;
    private final int rating_value;
}
