package com.datastorage;

public class Contact extends Entity {

    String name;
    String phone_number;

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
}