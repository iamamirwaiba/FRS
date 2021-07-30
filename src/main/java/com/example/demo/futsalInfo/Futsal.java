package com.example.demo.futsalInfo;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "futsal")
public class Futsal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "futsal_id")
    private Long id;
    @Column(name = "address")
    private String address;
    @Column(name = "ground_numbers")
    private int groundNumbers;
    @Column(name = "name")
    private String name;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name="latitude",nullable = true)
    private String latitude;
    @Column(name="longitude", nullable = true)
    private String longitude;
    @Column(name="futsal_image",nullable = true)
    private String futsalImage;
    @Column(name="description", nullable = true)
    private String Description;
    @Column(name="user_id",nullable = true)
    private Long user_id;

    public Futsal(String address, int groundNumbers, String name, String phoneNumber,String latitude,String longitude) {
        this.address = address;
        this.groundNumbers = groundNumbers;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.latitude=latitude;
        this.longitude=longitude;
    }
}
