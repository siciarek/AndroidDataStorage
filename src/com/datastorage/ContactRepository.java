package com.datastorage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ContactRepository extends AbstractRepository {

    private static final String FIELD_NAME = "name";
    private static final String FIELD_PHONE_NUMBER = "phone_number";
    
    static final String[][] FIELDS = {
        { ID_FIELD_NAME,      FIELD_TYPE_ID },
        { FIELD_NAME,         FIELD_TYPE_TEXT },
        { FIELD_PHONE_NUMBER, FIELD_TYPE_TEXT }
    };

    public ContactRepository(Context context) {
        super(context);
        TABLE_NAME = "contact";
    }
    
    @Override
    public void onCreate(SQLiteDatabase db) {
        super.onCreate(db);
        
        String fieldsdef = "";
        
        for(int i = 0; i < FIELDS.length; i++) {
            fieldsdef += FIELDS[i][0] + " " + FIELDS[i][1];
            fieldsdef += i < FIELDS.length - 1 ? "," : "";
        }
        
        String query = "CREATE TABLE " + TABLE_NAME + "(" + fieldsdef + ")";
        db.execSQL(query);
    }

    public Entity getEntity(Cursor cursor) {
        Contact contact = new Contact();

        contact.setID(Integer.parseInt(cursor.getString(0)));
        contact.setName(cursor.getString(1));
        contact.setPhoneNumber(cursor.getString(2));

        return contact;
    }
    
    public ContentValues getValues(Entity entity) {
        Contact contact = (Contact) entity;
        ContentValues values = new ContentValues();

        values.put(FIELD_NAME, contact.getName());
        values.put(FIELD_PHONE_NUMBER, contact.getPhoneNumber());

        return values;
    }
}