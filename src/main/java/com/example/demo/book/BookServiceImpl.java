package com.example.demo.book;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRepository;
import com.example.demo.ground.Ground;
import com.example.demo.ground.GroundRepo;
import com.example.demo.mailSender.MailService;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
@ToString
public class BookServiceImpl implements BookService {

    private final BookRepo bookRepo;
    private final MailService mailService;
    private final AppUserRepository appUserRepository;
    private final GroundRepo groundRepo;

    @Override
    public Map<String, String> makeBooking(BookRequest request) {
        Book ispresent=bookRepo.booknotAvailable(request.getBookedDate(),request.getBookedTime());
        if(ispresent==null){
            Book book=new Book(request.getGround_id(),request.getUser_id(),request.getBookedDate(),request.getBookedTime());
            bookRepo.save(book);
            Map<String,String> frs=new HashMap<>();
            frs.put("message","booking successful");
            Optional <AppUser> list=appUserRepository.findById(request.getUser_id());
            AppUser appUser=list.get();
            String to=appUser.getEmail();
            //String to="bijaya.shrestha@student.trinity.edu.np";
            String subject="Booking a Arena";
            int bookedtimestart=Integer.parseInt(request.getBookedTime());
            if(bookedtimestart>12){
                bookedtimestart=bookedtimestart-12;
            }
            int bookedtimeend=bookedtimestart+1;
            if(bookedtimeend>12){
                bookedtimeend=bookedtimeend-12;
            }

            String text="Booking for "+request.getBookedDate()+"  "+bookedtimestart+":00 to "+bookedtimeend+":00 is Successful, Thank You";
            mailService.sendSimpleMessage(to,subject,text);
            return frs;
        }
        else{
            Map<String,String> frs=new HashMap<>();
            frs.put("message","Booking is packed at the given time and date try on different date");
            return frs;
        }


    }

    @Override
    public Map<String, String> cancleBooking(Long id) {
        return null;
    }

    @Override
    public List<Book> listAllBooking() {
        return bookRepo.findAll();
    }

    @Override
    public List<Book> bookingonfutsal(Long id) {
        List<Ground> frs=groundRepo.findbyFutsal(id);
        Long[] b=new Long[10];
        for(int i=0;i<frs.size();i++){
            b[i]=frs.get(i).getGround_id();
            System.out.println("\\\\\\");
            System.out.println(b[i]);
            System.out.println("\\\\\\");
        }
        List<Book> book=new LinkedList<>();
        //for(int i=0;i<b.length;i++) {
             List<Book> book1= bookRepo.findbyground(b[0]);
             List<Book> book2 =bookRepo.findbyground(b[1]);
        //}

        book.addAll(book1);
        book.addAll(book2);
        return book;

    }

    @Override
    public List<Book> bookingonground(Long id) {
        return bookRepo.findbyground(id);
    }

    @Override
    public List<Book> bookbyuser(Long id) {
        return bookRepo.findbyuser(id);
    }
}
