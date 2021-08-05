package com.example.demo.book;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BookRequestWrapper {
    @JsonProperty("BookRequest")
    private BookRequest bookRequest;
}
