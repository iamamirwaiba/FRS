package com.example.demo.futsalInfo;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/public")
@CrossOrigin(origins = "*")
public class FutsalController {

    private final FutsalRepo futsalRepo;


    @PostMapping("/futsalregister")
    public Map<String,String> FutsalRegister(@RequestBody Map<String,Object> request){
        String name=(String) request.get("name");
        String address=(String) request.get("address");
        String number=(String) request.get("groundNumbers");
        int groundNumbers=Integer.parseInt(number);
        String phoneNumbers=(String) request.get("phoneNumber");
        String latitude=(String) request.get("latitude");
        String longitude=(String) request.get("longitude");
        //boolean isPresent=futsalValidator.Futsalvalidator(phoneNumbers);

        futsalRepo.save(new Futsal(address,groundNumbers,name,phoneNumbers,latitude,longitude));
        Map<String,String> frs=new HashMap<>();
        frs.put("message","New Futsal Saved");
        return frs;

    }

    @GetMapping("/findAll")
    public List<Futsal> futsalList(){
        List<Futsal> frs=futsalRepo.findAll();
        //Optional<AppUser> user=  appUserRepository.findById(id);
        /*if(user!=null){
            AppUser user1=user.get();
            String Latitude=user1.getLatitude();
            String longitude=user1.getLongitude();

            for(int i=0;i<frs.size();i++){

            }

        }*/


        // amir.put("name",fr.getName());
        //amir.put("groundNUmber",fr.getGroundNumbers()+"");
        //amir.put("address",fr.getAddress());
        //amir.put("phoneNumber",fr.getPhoneNumber());

        return frs;

    }


    @GetMapping("/findByName")
    public List<Futsal> searchByName(@RequestBody Map<String,Object> request){
        String name = (String) request.get("name");
        return futsalRepo.findByName(name);

    }

    @GetMapping("/findByAddress")
    public List<Futsal> searchByAddress(@RequestBody Map<String,Object> request){
        String address= (String) request.get("address");
        return futsalRepo.findByAddress(address);

    }

    @PostMapping("/findbyground")
    public Futsal findbyground(@RequestBody Map<String,String> request){
        Long futsal_id=Long.parseLong((String) request.get("futsal_id"));
        Optional<Futsal> futsal=futsalRepo.findById(futsal_id);
        return futsal.get();
    }



}
