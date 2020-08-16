package com.example.amin.qofq;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

import static com.example.amin.qofq.G.database;

public class MainActivity extends AppCompatActivity {

    public static Boolean F = false;
    public static Boolean C1 = false;
    public static Boolean C2 = false;
    public static String PlayerName = "";
    ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mackDataBace();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                G.handler.post(new Runnable() {
                    @Override
                    public void run() {
                        dialog = new ProgressDialog(MainActivity.this);
                        dialog.setTitle("Loading 0%");
                        dialog.setMessage("Please Wait For Building Face Data");
                        dialog.setCancelable(false);
                        dialog.show();
                    }
                });

                Cursor cursor = database.rawQuery("SELECT * FROM QType9 WHERE Id = '99'",null);
                if (cursor.moveToNext())
                { }
                else
                {
                    int c = 0;
                    for (int i = 1; i < 10; i++)
                    {
                        for (int j = 1; j <= 100; j++)
                        {
                            c++;
                            final int finalC = c;
                            G.handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    dialog.setTitle("Loading " + String.valueOf(finalC / 9) + "%");
                                }
                            });
                            database.execSQL("INSERT INTO QType" + i + " (Question,Ch1,Ch2,Ch3,Ch4,Answer) VALUES ('" + "question " + i + "-" + j + "','" + j + "','" + j + "','" + j + "','" + j + "','" + (j%4 == 0 ? 4 : j%4) + "')");
                        }
                    }
                }
                cursor.close();

                G.handler.post(new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                    }
                });
            }
        });

        thread.start();

        final EditText UserName = (EditText) findViewById(R.id.usertxt);
        final EditText Userpass = (EditText) findViewById(R.id.passtxt);
        final EditText Userrepass = (EditText) findViewById(R.id.repasstxt);
        final Button Login = (Button) findViewById(R.id.login);
        final Button Singup = (Button) findViewById(R.id.singup);



        UserName.setVisibility(View.INVISIBLE);
        Userpass.setVisibility(View.INVISIBLE);
        Userrepass.setVisibility(View.INVISIBLE);

        Login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (C1)
                {
                    boolean ok = false;

                    String UName = UserName.getText().toString();

                    Cursor cursor = database.rawQuery("SELECT * FROM accounts WHERE account_name = '" + UName +"'",null);

                    while (cursor.moveToNext())
                    {
                        String name = cursor.getString(cursor.getColumnIndex("account_name"));
                        String password = cursor.getString(cursor.getColumnIndex("account_password"));
                        if (Userpass.getText().toString().equals(password))
                        {
                            ok = true;
                            break;
                        }
                    }
                    cursor.close();

                    if (ok)
                    {
                        PlayerName = UName;
                        Start();
                    }
                    else
                        Toast.makeText(MainActivity.this, "UsserName OR PassWoore is rong", Toast.LENGTH_LONG).show();
                }
                else if (!F)
                {
                    UserName.setVisibility(View.VISIBLE);
                    Userpass.setVisibility(View.VISIBLE);
                    //Singup.setVisibility(View.INVISIBLE);
                    Singup.setText("<");
                    Login.setText("OK");
                    F = !F;
                    C1 = true;
                    C2 = false;
                }
                else
                {
                    UserName.setVisibility(View.INVISIBLE);
                    Userpass.setVisibility(View.INVISIBLE);
                    Userrepass.setVisibility(View.INVISIBLE);
                    Login.setText("Login");
                    Singup.setText("Sing Up");
                    F = !F;
                    C1 = false;
                }
            }
        });

        Singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (C2)
                {
                    String UName = UserName.getText().toString();
                    String UPass = Userpass.getText().toString();
                    String UrPass = Userrepass.getText().toString();

                    if (!UPass.equals(UrPass))
                        Toast.makeText(MainActivity.this, "repassword wasent mach " + UPass + " " + UrPass, Toast.LENGTH_LONG).show();
                    else
                    {
                        boolean ok = false;



                        Cursor cursor = database.rawQuery("SELECT * FROM accounts WHERE account_name = '" + UName +"'",null);

                        while (cursor.moveToNext())
                        {
                            ok = true;
                            break;
                        }
                        cursor.close();

                        if (ok)
                            Toast.makeText(MainActivity.this, "UsserName is exist", Toast.LENGTH_LONG).show();
                        else
                        {
                            database.execSQL("INSERT INTO accounts (account_name,account_password) VALUES ('" + UName + "','" + UPass + "')");
                            PlayerName = UName;
                            Start();
                        }
                    }
                }
                else if (!F)
                {
                    UserName.setVisibility(View.VISIBLE);
                    Userpass.setVisibility(View.VISIBLE);
                    Userrepass.setVisibility(View.VISIBLE);
                    //Login.setVisibility(View.INVISIBLE);
                    Login.setText("<");
                    Singup.setText("OK");
                    F = !F;
                    C2 = true;
                    C1 = false;
                }
                else
                {
                    UserName.setVisibility(View.INVISIBLE);
                    Userpass.setVisibility(View.INVISIBLE);
                    Userrepass.setVisibility(View.INVISIBLE);
                    Login.setText("Login");
                    Singup.setText("Sing Up");
                    F = !F;
                    C2 = false;
                }
            }
        });
    }

    public void Start()
    {
        Intent intent= new Intent(this, MainPlay.class);
        startActivity(intent);
        finish();
    }

    public static void mackDataBace(){

        G.DIR_SDCARD  = Environment.getExternalStorageDirectory().getAbsolutePath();
        G.DIR_DATABASE = G.DIR_SDCARD + "/databace-QofQ";

        new File(G.DIR_DATABASE).mkdirs();

        database = SQLiteDatabase.openOrCreateDatabase(G.DIR_DATABASE+"/database.sqlite",null);
        database.execSQL("CREATE  TABLE IF NOT EXISTS accounts (" +
                "account_id INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE , " +
                "account_name TEXT, " +
                "account_password TEXT" +
                ")");


        for (int i = 1; i < 10; i++)
        {
            database.execSQL("CREATE  TABLE IF NOT EXISTS QType" + i + " (" +
                    "Id INTEGER PRIMARY KEY  NOT NULL  UNIQUE ," +
                    " Question TEXT check(typeof(Question) = 'text') ," +
                    " Ch1 TEXT check(typeof(Ch1) = 'text') ," +
                    " Ch2 TEXT check(typeof(Ch2) = 'text') ," +
                    " Ch3 TEXT check(typeof(Ch3) = 'text') ," +
                    " Ch4 TEXT check(typeof(Ch4) = 'text') ," +
                    " Answer INTEGER" +
                    ")");
        }

    }

}
