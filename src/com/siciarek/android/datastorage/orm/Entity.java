package com.siciarek.android.datastorage.orm;

abstract public class Entity {

    protected int id;

    public int getID() {
        return this.id;
    }

    public void setID(int id) {
        this.id = id;
    }
}
