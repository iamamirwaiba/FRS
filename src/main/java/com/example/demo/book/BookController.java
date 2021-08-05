package com.example.demo.book;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/public")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BookController {

    private final BookService bookService;

    @PostMapping("/book")
    public ResponseEntity<Map<String,String>> makeBooking (@RequestBody BookRequest request){
        System.out.println();
        System.out.println();
        System.out.println(request.toString());
        System.out.println();
        System.out.println();
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
        Long  ground_id=Long.parseLong((String) request.get("ground_id"));
        System.out.println(ground_id);
        return bookService.bookingonground(ground_id);
    }

    @GetMapping("/findallbook")
    public List<Book> findall(){
        return bookService.listAllBooking();
    }

    @RequestMapping("/findbookbyuser")
    public List<BookResponseBody> bookbyuser(@RequestBody Map<String,Object> request){
        String id=(String) request.get("user_id");
        System.out.println();
        System.out.println();
        System.out.println(id);
        System.out.println();
        System.out.println();
        Long user_id=Long.parseLong(id);
        return bookService.bookbyuser(user_id);

    }
    @PostMapping("/cancleBook")
    public Map<String, String> cancleBook(@RequestBody Map<String,Object> request){
        String Id=(String) request.get("bookId");
        Long bookId=Long.parseLong(Id);
        Map<String,String> message=bookService.cancelBooking(bookId);
        return message;
    }

}

//findBooking By FUtsal samma vako xa

