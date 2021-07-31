package com.example.demo.cancledbook;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CancledBookRepo extends JpaRepository<CancledBook,Long> {
    @Query(value = "select * from cancled_book c where c.booked_date= :date and c.booked_time=:time", nativeQuery = true)
    public List<CancledBook> findbyDateTime(@Param("date") String date,@Param("time") String time);
}
