package com.example.demo.futsalInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@ToString
public class FutsalRequest {

        private final String name;
        private final String address;
        private final String phoneNumber;
        private final int groundNumbers;
        private final String latitude;
        private final String longitude;
        private final String futsalImage;

}
