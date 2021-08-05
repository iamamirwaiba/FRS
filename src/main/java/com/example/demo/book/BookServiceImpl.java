package com.example.demo.book;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRepository;
import com.example.demo.cancledbook.CancledBook;
import com.example.demo.cancledbook.CancledBookRepo;
import com.example.demo.futsalInfo.Futsal;
import com.example.demo.futsalInfo.FutsalRepo;
import com.example.demo.ground.Ground;
import com.example.demo.ground.GroundRepo;
import com.example.demo.mailSender.MailService;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@RequiredArgsConstructor
@Service
@ToString
public class BookServiceImpl implements BookService {

    private final BookRepo bookRepo;
    private final MailService mailService;
    private final AppUserRepository appUserRepository;
    private final GroundRepo groundRepo;
    private final CancledBookRepo cancledBookRepo;
    private final FutsalRepo futsalRepo;

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
            //  mailService.sendSimpleMessage(to,subject,text);
            return frs;
        }
        else{

            CancledBook book=new CancledBook(request.getUser_id(),request.getGround_id(),request.getBookedDate(),request.getBookedTime());
            cancledBookRepo.save(book);
            Map<String,String> frs=new HashMap<>();
            frs.put("message","Booking is Packed at that time, we'll notify you if it gets cancled");
            return frs;
        }


    }

    @Override
    public Map<String, String> cancelBooking(Long id) {
        Book book=bookRepo.findbyId(id);
        String bookedDate=book.getBookedDate();
        String bookedTime=book.getBookedTime();
        Long time=Long.parseLong(bookedTime);
        List<CancledBook> cancledBook=cancledBookRepo.findbyDateTime(bookedDate,bookedTime);
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date date = new Date();
        String localDateTime= formatter.format(date);
        String localDate=localDateTime.substring(0,10);
        String time1=localDateTime.substring(11,13);
        Long localTime=Long.parseLong(time1);
        if(bookedDate.equals(localDate)){
            if(time-localTime<=1){
                Map<String,String> frs=new HashMap<>();
                frs.put("message","Booking cannot be cancled before an hour before booked time");
                return frs;
            }
        }
        Long groundId=book.getGround_id();
        Optional<Ground> ground=groundRepo.findById(groundId);
        Long futsalId=ground.get().getFutsal_id();
        Optional<Futsal> futsal =futsalRepo.findById(futsalId);
        String futsalName=futsal.get().getName();

        if (cancledBook!=null) {
            for (int i = 0; i < cancledBook.size(); i++) {
                CancledBook cancledBook1 = cancledBook.get(i);
                Long userId = cancledBook1.getUserId();
                Optional<AppUser> appUser = appUserRepository.findById(userId);
                String to = appUser.get().getEmail();
                String Subject = "Make a book";
                Long bookedtimestart = time;
                if (bookedtimestart > 12) {
                    bookedtimestart = bookedtimestart - 12;
                }
                Long bookedtimeend = bookedtimestart + 1;
                if (bookedtimeend > 12) {
                    bookedtimeend = bookedtimeend - 12;
                }

                String text = "Booking for " + bookedDate + "  " + bookedtimestart + ":00 to " + bookedtimeend + ":00 is Open for Booking at " + futsalName;
               // mailService.sendSimpleMessage(to,Subject,text);


            }
        }

        Long userId=book.getUser_id();
        Optional<AppUser> appUser=appUserRepository.findById(userId);
        int bookcancled=appUser.get().getBookcancled();

        bookcancled++;
        if(bookcancled==5){
            appUser.get().setLocked(true);
            appUser.get().setBookcancled(0);
            bookRepo.deleteById(id);
            Map<String,String> frs=new HashMap<>();
            frs.put("message","Account locked due to repeated cancellation, contact the cutomer care support");

            return frs;
        }
        appUser.get().setBookcancled(bookcancled);
        Map<String,String> frs=new HashMap<>();
        frs.put("message","Booking Cancel Successful");
        frs.put("cancel message","Canceling booking many times lead to account lock Be sure to Cancel wisely");
        bookRepo.deleteById(id);

        return frs;

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
    public List<BookResponseBody> bookbyuser(Long id) {

        List<Book> bookList= bookRepo.findbyuser(id);
        List<BookResponseBody> frs=new LinkedList<>();
        for(int i=0;i<bookList.size();i++){
            Book book=bookList.get(i);
            Long ground_id=book.getGround_id();
            Optional<Ground> ground=groundRepo.findById(ground_id);
            Long futsal_id=ground.get().getFutsal_id();
            Futsal futsal=futsalRepo.getById(futsal_id);
            String name=futsal.getName();
            String bookedTime=book.getBookedTime();
            Long Time=Long.parseLong(bookedTime);
            Time++;
            String bookedDate=book.getBookedDate();
            String bookedday=bookedDate.substring(3,5);
            Long day=Long.parseLong(bookedday);
            String bookedmonth=bookedDate.substring(0,2);
            Long month=Long.parseLong(bookedmonth);
            String bookedyear=bookedDate.substring(6,10);
            Long year=Long.parseLong(bookedyear);
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            Date date = new Date();
            String localDateTime= formatter.format(date);
            String localDate=localDateTime.substring(0,10);

            String day1=localDate.substring(3,5);
            Long localDay=Long.parseLong(day1);
            String month1=localDate.substring(0,2);
            Long localMonth=Long.parseLong(month1);
            String year1=localDate.substring(6,10);
            Long localYear=Long.parseLong(year1);
            String Time1=localDateTime.substring(11,13);
            Long localTime=Long.parseLong(Time1);

            Boolean isAfter=isBefore(day,month,year,localDay,localMonth,localYear);

            if(isAfter){

                    book.setRatingEnabled(1);
            }
            else if(!isAfter){
                if(bookedDate.equals(localDate)){
                    if(localTime-Time>=0){
                        book.setRatingEnabled(1);
                    }
                }
            }
            else {
                book.setRatingEnabled(0);
            }
            BookResponseBody bookResponseBody=new BookResponseBody(book.getId(),book.getGround_id(),book.getUser_id(),book.getLocalDateTime(),book.getBookedDate(),book.getBookedTime(),book.getRatingEnabled(),name);

            frs.add(bookResponseBody);

        }
        bookRepo.saveAll(bookList);
        return frs;
    }

    private Boolean isBefore(Long day,Long month,Long year,Long localDay,Long localMonth,Long localYear){

        System.out.println(
        );
        System.out.println();
        System.out.println();
        System.out.println(day+""+month+""+year);
        System.out.println(localDay+""+localMonth+""+localYear);
        System.out.println();
        System.out.println();
        System.out.println();
        if(localYear-year>0){
            System.out.println(localYear-year);
            return true;
        }
        if(localYear==year&&localMonth-month>0){
            System.out.println(localMonth-month);
            return true;
        }
        if(localYear==year&&localMonth==month&&localDay-day>0){
            System.out.println(localDay-day);
            return true;
        }
        return false;
    }
}
