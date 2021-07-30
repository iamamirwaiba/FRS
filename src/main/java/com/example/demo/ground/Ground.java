package com.example.demo.ground;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString
@Entity
@NoArgsConstructor
@Table(name="ground")
public class Ground {
    @Id
    @Column(name="ground_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ground_id;
    @Column(name="ground_type")
    private String ground_type;
    @Column(name="cost")
    private int cost;
    @Column(name="ground_image")
    private String ground_image;
    @Column(name="futsal_id")
    private Long futsal_id;

    public Ground(String ground_type, int cost, String ground_image, Long futsal_id) {
        this.ground_type = ground_type;
        this.cost = cost;
        this.ground_image = ground_image;
        this.futsal_id = futsal_id;
    }
}
