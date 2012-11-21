package com.siciarek.android.datastorage.model;

import com.siciarek.android.datastorage.orm.Entity;

public class Contact extends Entity {

    String name;
    String phone_number;
    String email;

    public void setName(String value) {
        this.name = value;
    }

    public String getName() {
        return this.name;
    }

    public void setPhoneNumber(String value) {
        this.phone_number = value;
    }

    public String getPhoneNumber() {
        return this.phone_number;
    }

    public void setEmail(String value) {
        this.email = value;
    }

    public String getEmail() {
        return this.email;
    }
}
