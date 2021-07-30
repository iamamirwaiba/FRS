package com.example.demo.Dao;

import com.example.demo.appuser.AppUser;

public interface AppUserDao {
    public AppUser getUserByPhoneNumber(String phoneNumber);

}
