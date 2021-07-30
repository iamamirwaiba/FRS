package com.example.demo.rating;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface RateRepo extends JpaRepository<Rate,Long> {
    @Query(value="select c from Rate c where c.bookId=?1")
    public Rate getRating(Long book_id);
 }
