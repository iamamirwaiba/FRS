package com.example.demo.rating;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class RateController {

    private final RateServiceImpl rateService;

    @PostMapping("/rate")
    public Map<String,String> saveRating(@RequestBody RateRequest request){

        return rateService.saveRating(request);

    }

    @GetMapping("/getaverageRating")
    public Map<String,Double> averageRating(@RequestBody Map<String,Object> request){
        String id=(String) request.get("ground_id");
        Long ground_id=Long.parseLong(id);
        Double averagevalue=rateService.averageRating(ground_id);
        Map<String,Double> average=new HashMap<>();
        average.put("Average is",averagevalue);
        return average;

    }

    @GetMapping("/getRating")
    public Rate getRating(@RequestBody Map<String,Object> request){
        String id=(String) request.get("book_id");
        Long book_id=Long.parseLong(id);
        return rateService.getRating(book_id);
    }
}
