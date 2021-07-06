package com.example.demo.Dao;

import com.example.demo.appuser.AppUser;

import java.util.List;

public interface AppUserDao {
    public AppUser getUserByPhoneNumber(String phoneNumber);

}
