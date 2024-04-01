package com.itai.justrun;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppUser implements Serializable {

    private static final AppUser ourInstance = new AppUser();

    public static AppUser sherdInstance() {
        return ourInstance;
    }


    private String Phone;


    public AppUser() {
    }

    public void resetData() {

        setPhone(null);

    }


    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        this.Phone = phone;
    }

}
