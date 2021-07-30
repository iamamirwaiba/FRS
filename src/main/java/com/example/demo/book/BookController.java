package com.example.demo.book;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BookController {

    private final BookService bookService;

    @PostMapping("/book")
    public ResponseEntity<Map<String,String>> makeBooking (@RequestBody BookRequest request){
        Map<String,String> frs=bookService.makeBooking(request);
        return new ResponseEntity<>(frs,HttpStatus.OK);

    }

    @GetMapping("/findbookbyfutsal")
    public List<Book> findbooking(@RequestBody Map<String,Object> request){
        String id=(String) request.get("futsal_id");
        Long futsal_id=Long.parseLong(id);
        return bookService.bookingonfutsal(futsal_id);

    }
    @GetMapping("/findbookbyground")
    public List<Book> findbookingbyground(@RequestBody Map<String,Object> request){
        String id=(String) request.get("ground_id");
        Long ground_id=Long.parseLong(id);
        System.out.println(ground_id);
        return bookService.bookingonground(ground_id);
    }

    @GetMapping("/findallbook")
    public List<Book> findall(){
        return bookService.listAllBooking();
    }

    @GetMapping("/findbookbyuser")
    public List<Book> bookbyuser(@RequestBody Map<String,Object> request){
        String id=(String) request.get("user_id");
        Long user_id=Long.parseLong(id);
       return bookService.bookbyuser(user_id);

    }

}

//findBooking By FUtsal samma vako xa

