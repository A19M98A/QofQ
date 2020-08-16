package com.example.amin.qofq;

import android.database.Cursor;

import static com.example.amin.qofq.G.database;

public class HelperSQL {

    public static Cursor SerchQuestion(String Type, String Id)
    {
        Cursor cursor = database.rawQuery("SELECT * FROM QType" + Type  + " WHERE Id = '" + Id + "'",null);
        return cursor;
    }

}
