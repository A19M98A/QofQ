package com.example.amin.qofq;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class G extends Application{

    public static Random random = new Random();

    public static Handler handler = new Handler();

    public static Context context;

    public SQLiteDatabase mydatabase = openOrCreateDatabase("Notes",MODE_PRIVATE,null);

    public static LayoutInflater inflater;
    public static Activity currentActivity;
    public static ArrayList<StructNode> nodes = new ArrayList<StructNode>();

    public static ArrayList<QustionNode> qnodes = new ArrayList<QustionNode>();

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();
        inflater = (LayoutInflater) getSystemService(context.LAYOUT_INFLATER_SERVICE);

        //database.execSQL("INSERT INTO accounts (account_name,account_password) VALUES ('AminAllah','Janipoor')");

//        Cursor cursor = database.rawQuery("SELECT * FROM accounts WHERE account_name = 'AminAllah'",null);
//
//        while (cursor.moveToNext())
//        {
//            String name = cursor.getString(cursor.getColumnIndex("account_name"));
//            String family = cursor.getString(cursor.getColumnIndex("account_password"));
//            Log.i("LOG","Record: " + name + " " + family);
//        }
//        cursor.close();
//
//        database.execSQL("DELETE FROM accounts WHERE account_name = 'AminAllah'");

    }

}
