package com.example.demo.futsalInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface FutsalRepo extends JpaRepository<Futsal,Long> {
    /*@Query("select new com.example.demo.request.FutsalRequest(c.id,c.name, c.address, c.phoneNumber, c.groundNumbers ) from Futsal c where c.phoneNumber=?1 ")
    <Optional>FutsalRequest Go(String phoneNumber);*/

   // @Query("select new com.example.demo.futsalInfo.FutsalRequest(c.name, c.address, c.phoneNumber, c.groundNumbers,c.latitude,c.longitude,c.futsalImage ) from Futsal c where c.name=?1")
    @Query(value = "select * from futsal c where c.name= :name", nativeQuery = true)
    List<Futsal> findByName(@Param("name")String name);


   // @Query("select new com.example.demo.futsalInfo.FutsalRequest(c.name, c.address, c.phoneNumber, c.groundNumbers,c.latitude,c.longitude ) from Futsal c where c.address like %'address' ")
    @Query(value = "select * from futsal c where c.address like %:address", nativeQuery = true)
    List<Futsal> findByAddress(@Param("address") String address);

}
