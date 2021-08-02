package com.example.demo.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface BookRepo extends JpaRepository<Book,Long> {

    @Query(value = "select * from book c where c.booked_date= :bookDate and c.booked_time=:bookedTime", nativeQuery = true)
    public Book booknotAvailable(@Param("bookDate") String bookedDate, @Param("bookedTime") String bookedTime);

    @Query(value = "select c from Book c where c.ground_id=?1")
    public List<Book> findbyground(Long ground_id);

    @Query(value="select c from Book c where c.user_id=?1")
    public List<Book> findbyuser(Long user_id);

    @Query(value="select c from Book c where c.id=?1")
    public Book findbyId(Long book_id);

}
