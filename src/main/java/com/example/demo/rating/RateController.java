package com.example.demo.rating;

import com.example.demo.ground.Ground;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/public")
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
        String id=(String) request.get("futsal_id");
        Long futsal_id=Long.parseLong(id);
        Double averagevalue=rateService.averageRating(futsal_id);
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

    @GetMapping("/getFutsalRating")
    public List<Rate> getFutsalRating(@RequestBody Map<String,Object> request){
        String id=(String) request.get("futsal_id");
        Long futsalId=Long.parseLong(id);
        return rateService.getFutsalRating(futsalId);
    }
}
