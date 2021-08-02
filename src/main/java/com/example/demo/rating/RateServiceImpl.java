package com.example.demo.rating;

import com.example.demo.book.Book;
import com.example.demo.book.BookRepo;
import com.example.demo.ground.Ground;
import com.example.demo.ground.GroundRepo;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
    public List<Rate> getFutsalRating(Long futsalId) {
        List<Ground> ground=groundRepo.findbyFutsal(futsalId);
        List<Rate> rate1=new LinkedList<>();
        for (int i=0;i<ground.size();i++) {
            Ground ground1=ground.get(i);
            Long groundId=ground1.getGround_id();
            List<Book> book=bookRepo.findbyground(groundId);
            for(int j=0;j<book.size();j++){
                Long bookId=book.get(j).getId();
                Rate rate=rateRepo.getRating(bookId);
                rate1.add(rate);
            }

        }
        return rate1;
    }

    @Override
    public double averageRating(Long id) {
        List<Ground> ground=groundRepo.findbyFutsal(id);
        Double value=0.0;
        Double totalrating=0.0;
        for(int i=0;i<ground.size();i++){
            Long groundId=ground.get(i).getGround_id();
            List <Book> book=bookRepo.findbyground(groundId);
            for(int j=0;j<book.size();j++){
                Long bookId=book.get(j).getId();
                Rate rate=rateRepo.getRating(bookId);
                int temp=rate.getRatingValue();
                System.out.println(temp);
                Double temp1=(double) temp;
                value+=temp1;
                totalrating++;



            }
        }
        Double average=value/totalrating;
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
