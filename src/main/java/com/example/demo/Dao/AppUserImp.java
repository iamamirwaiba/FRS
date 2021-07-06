package com.example.demo.Dao;

import com.example.demo.appuser.AppUser;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppUserImp implements AppUserDao {


    public List<AppUser> getUser() {
        return null;
    }

    @Override
    public AppUser getUserByPhoneNumber(String phoneNumber) {
        return null;
    }
}
