package com.example.demo.ground;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/public")
public class GroundController {

    private final GroundService groundService;

    @PostMapping("/addground")
    public ResponseEntity<Map<String,String>> addgrounds(@RequestBody GroundRequest request){

        Map<String,String> book=groundService.addNewGround(request);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @RequestMapping ("getbyFutsalid")
    public List<Ground> findbyfutsalid (@RequestBody Map<String,Object> request){
        String id=(String) request.get("futsal_id");
        Long futsal_id=Long.parseLong(id);
        return groundService.findbyid(futsal_id);
    }
}
