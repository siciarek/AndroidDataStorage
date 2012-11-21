package com.datastorage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class ContactRepository extends EntityRepository {

    protected static final String NAME = "name";
    protected static final String PHONE_NUMBER = "phone_number";

    /**
     * Class constructor
     * 
     * @param context
     */
    public ContactRepository(Context context) {
        super(context);
        TABLE_NAME = "contact";
        FIELDS = new String[][] {
            new String[] { ID_FIELD_NAME, FIELD_TYPE_ID },
            new String[] { NAME,          FIELD_TYPE_TEXT },
            new String[] { PHONE_NUMBER,  FIELD_TYPE_TEXT }
        };
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

        values.put(NAME, contact.getName());
        values.put(PHONE_NUMBER, contact.getPhoneNumber());

        return values;
    }
}