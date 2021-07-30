package com.example.demo.ground;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface GroundRepo extends JpaRepository<Ground,Long> {

    @Query(value = "select c from Ground c  where c.futsal_id=?1")
    public List<Ground> findbyFutsal( Long futsal_id);

}
