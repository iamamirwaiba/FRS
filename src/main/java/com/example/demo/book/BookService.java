package com.example.demo.book;

import java.util.List;
import java.util.Map;

public interface BookService {
    public Map<String,String> makeBooking(BookRequest request);
    public Map<String,String> cancelBooking(Long id);
    public List<Book> listAllBooking();
    public List<Book> bookingonfutsal(Long id);
    public List<Book> bookingonground(Long id);
    public List<BookResponseBody> bookbyuser(Long id);
}
