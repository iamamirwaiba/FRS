package com.example.demo.rating;

import com.example.demo.book.Book;
import com.example.demo.book.BookRepo;
import com.example.demo.ground.Ground;
import com.example.demo.ground.GroundRepo;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
@Data
public class RateServiceImpl implements RateService {
    private final GroundRepo groundRepo;
    private final RateRepo rateRepo;
    private final BookRepo bookRepo;
    @Override
    public Map<String, String> saveRating(RateRequest request) {
        Rate rate=new Rate(request.getRating_value(),request.getBook_id());
        rateRepo.save(rate);
        Map<String,String> frs=new HashMap<>();
        frs.put("message","RatedSuccessfully");
        return frs;

    }

    @Override
    public Rate getRating(Long book_id){
        return rateRepo.getRating(book_id);

    }

    @Override
    public double averageRating(Long ground_id) {
       List<Book> book= bookRepo.findbyground(ground_id);
       Double average;
       int temp=0;
       for(int i=0;i<book.size();i++){
          Rate rate= rateRepo.getRating(book.get(i).getId());
          temp+=rate.getRatingValue();
       }
       average=(double) temp/book.size();
       return average;
    }


    /*@Override
    public double averageRating(Long ground_id) {
        List<Book> frs=bookRepo.findbyground(ground_id);
        Double average= 0.0;
        Book book=new Book();
        for(int i=0;i<frs.size();i++){
            book=frs.get(i);


        }
        return average;



    }*/
}
