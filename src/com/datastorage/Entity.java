package com.datastorage;

abstract public class Entity {

    protected int id;

    public int getID() {
        return this.id;
    }

    public void setID(int id) {
        this.id = id;
    }
}
