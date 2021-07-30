package com.example.demo.ground;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface GroundService {
    public Map<String,String> addNewGround(GroundRequest request);
    public List<Ground> findbyid(Long id);
}
