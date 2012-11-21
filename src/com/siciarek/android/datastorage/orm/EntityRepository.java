package com.siciarek.android.datastorage.orm;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

abstract public class EntityRepository extends SQLiteOpenHelper {

    protected static final String FIELD_TYPE_ID      = "INTEGER PRIMARY KEY";
    protected static final String FIELD_TYPE_TEXT    = "TEXT";
    protected static final String FIELD_TYPE_INTEGER = "INTEGER";
    
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "addressbook.db";

    protected static String TABLE_NAME = "table";
    protected static String[][] FIELDS;
    protected static String TABLE_ADDONS = "";
    protected static final String ID_FIELD_NAME = "id";

    public EntityRepository(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    @Override
    public void onCreate(SQLiteDatabase db) {

        String TABLE_FIELDS_DEF = "";

        for(int i = 0; i < FIELDS.length; i++) {
            TABLE_FIELDS_DEF += FIELDS[i][0] + " " + FIELDS[i][1];
            TABLE_FIELDS_DEF += i < FIELDS.length - 1 ? "," : "";
        }

        TABLE_FIELDS_DEF += TABLE_ADDONS.length() > 0 ? "," + TABLE_ADDONS : "";
        
        String TABLE_CREATE_QUERY = "CREATE TABLE " + getTableName() + "(" + TABLE_FIELDS_DEF + ")";
        db.execSQL(TABLE_CREATE_QUERY);
    }
    
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public String getTableName() {
        return TABLE_NAME;
    }
    
    private String[] getFieldNames() {
        String[] fn = new String[FIELDS.length];
        
        for(int i = 0; i < FIELDS.length; i++) {
            fn[i] = FIELDS[i][0];
        }
        
        return fn;
    }
    

    public int getCount() {
        String query = "SELECT * FROM " + TABLE_NAME;
        
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Integer count = cursor.getCount();
        cursor.close();
        db.close();

        return count;
    }

    abstract protected Entity getEntity(Cursor cursor);
    abstract protected ContentValues getValues(Entity entity);
    
    /**
     * CRUD (Create, Read, Update, Delete)
     */
    
    /**
     * Create
     * @param entity Entity Entity object
     */
    public void add(Entity entity) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME, null, getValues(entity));
        db.close();
    }

    /**
     * Read (single)
     * @param id int Object's id
     * @return Entity Entity object
     */
    public Entity get(int id) {
        
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, getFieldNames(), ID_FIELD_NAME + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        
        if (cursor != null) {
            cursor.moveToFirst();
        }
        
        Entity e = getEntity(cursor);
        
        db.close();
        
        return e;
    }

    /**
     * Read (collection)
     * @return List<Entity> Entities collection
     */
    public List<Entity> get() {
        List<Entity> list = new ArrayList<Entity>();
        
        String query = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                list.add(getEntity(cursor));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        
        return list;
    }

    /**
     * Update
     * @param entity
     * @return
     */
    public int update(Entity entity) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.update(TABLE_NAME, getValues(entity), ID_FIELD_NAME + " = ?", new String[] { String.valueOf(entity.getID()) });
        db.close();

        return result;
    }

    /**
     * Delete
     * @param entity
     */
    public void delete(Entity entity) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, ID_FIELD_NAME + " = ?", new String[] { String.valueOf(entity.getID()) });
        db.close();
    }
}