package ru.rsdev.smscontroller;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Дом on 11.05.2015.
 */
public class EditBoilerBD {

    private SQLiteDatabase database;
    private static final String TABLE_NAME = "BoilerTable";
    private static final String ID = "_id";
    private static final String NAME = "Name";

    public void EditBoilerBD(Context context)
    {
        ExternalDbOpenHelper dbOpenHelper = new ExternalDbOpenHelper(context);
        database = dbOpenHelper.openDataBase();
    }

    public void AddNewBoiler(String[] str)
    {
        //String sqlQuery = "INSERT INTO " + TABLE_NAME + " (Name,Param1,Param2,Param3,Param4,SmsNumber) VALUES ('" + str[0] + "','0','0','0','0','" + str[1]+ "')";
        //Cursor cursor = database.rawQuery(sqlQuery, new  String[] {});

        ContentValues cv = new ContentValues();
        cv.put("Name", str[0]);
        cv.put("Param1", "0");
        cv.put("Param2", "0");
        cv.put("Param3", "0");
        cv.put("Param4", "0");
        cv.put("SmsNumber", str[1]);

        long rowID = database.insert(TABLE_NAME, null, cv);
    }

    public ArrayList GetBoilerList()
    {

        ArrayList<String> nameBoiler = new ArrayList<String>();
/*
        String sqlQuery = "INSERT INTO " + TABLE_NAME + " (Name,Param1,Param2,Param3,Param4,SmsNumber) VALUES ('" + str[0] + "','0','0','0','0','" + str[1]+ "')";
        Cursor cursor = database.rawQuery(sqlQuery, new  String[] {});

        if (cursor.moveToFirst()) {

            int nameColIndex = cursor.getColumnIndex(NAME);
            do {
                nameBoiler.add(cursor.getString(nameColIndex));
            } while (cursor.moveToNext());
        }
        cursor.close();
        */

        return nameBoiler;
    }


}
