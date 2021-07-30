package com.example.demo.ground;


import lombok.*;

@AllArgsConstructor
@Data
@ToString
@EqualsAndHashCode
public class GroundRequest {

    private final String ground_type;
    private final int cost;
    private final String ground_image;
    private final Long futsal_id;

}
