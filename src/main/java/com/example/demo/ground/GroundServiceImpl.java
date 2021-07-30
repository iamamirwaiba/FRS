package com.example.demo.ground;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Data
@EqualsAndHashCode
@ToString
public class GroundServiceImpl implements GroundService {
    private final GroundRepo groundRepo;
    @Override
    public Map<String, String> addNewGround(GroundRequest  request) {
        try {
            Ground ground = new Ground(request.getGround_type(), request.getCost(), request.getGround_image(), request.getFutsal_id());
            groundRepo.save(ground);
            Map<String,String> frs=new HashMap<>();
            frs.put("message","ground added");
            return frs;
        }
        catch( Exception e){
            Map<String,String> frs=new HashMap<>();
            frs.put("message","error could not add ground");
            return frs;

        }


    }

    @Override
    public List<Ground> findbyid(Long id) {
        return groundRepo.findbyFutsal(id);
    }
}
