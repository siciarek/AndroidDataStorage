package com.siciarek.android.datastorage;

import java.util.List;

import com.siciarek.android.datastorage.R;
import com.siciarek.android.datastorage.model.Contact;
import com.siciarek.android.datastorage.model.ContactRepository;
import com.siciarek.android.datastorage.orm.Entity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Contact c;
        ContactRepository cr;
        
        cr = new ContactRepository(this);
        
        Log.i("", "------------------------------------------");
        Log.d("Table name: ", cr.getTableName());
        Log.i("", "------------------------------------------");
        Log.d("Count: ", "" + cr.getCount());
        Log.i("", "------------------------------------------");

        Log.d("Insert: ", "Inserting...");
        
        c = new Contact();
        c.setName("Czesław Olak");
        c.setPhoneNumber("+48555333222");
        cr.add(c);

        c = new Contact();
        c.setName("Marian Woronin");
        c.setPhoneNumber("+48111222333");
        cr.add(c);

        c = new Contact();
        c.setName("Jacek Siciarek");
        c.setPhoneNumber("+48603173114");
        cr.add(c);

        c = new Contact();
        c.setName("Wojciech Górecki");
        c.setPhoneNumber("+48522173114");
        cr.add(c);

        Log.d("Reading: ", "Reading all contacts...");
        List<Entity> contacts;

        contacts = cr.get();
        displayCollection(contacts);

        Log.d("Deleting: ", "Deleting first record...");

        cr.delete(contacts.get(0));
        contacts = cr.get();
        displayCollection(contacts);
        
        Log.i("", "------------------------------------------");
        Log.d("Count: ", "" + cr.getCount());
    }
    
    private void displayRecord(Contact cn) {
        String log = "Id: " + cn.getID() + ", Name: " + cn.getName() + ", Phone: " + cn.getPhoneNumber();
        Log.d("Record:", log);
    }
    
    private void displayCollection(List<Entity> list) {
        Log.i("", "------------------------------------------");
        for (Entity cn : list) {
            displayRecord((Contact) cn);
        }
    }
}