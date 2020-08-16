package com.example.amin.qofq;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.Px;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.amin.qofq.G.database;

public class MainPlay extends AppCompatActivity {

    public ArrayAdapter adapter;
    public Button AddQ, ListQ;
    public Button Play;
    TextView pname, uname, uxp;

    @Override
    protected void onResume() {
        G.currentActivity = this;
        super.onResume();
        G.nodes.clear();
        Cursor cursor = database.rawQuery("SELECT * FROM " + MainActivity.PlayerName + "_History WHERE HiId > '0'",null);

        pname = (TextView) findViewById(R.id.pname);
        uname = (TextView) findViewById(R.id.uname);
        uxp = (TextView) findViewById(R.id.uxp);
        findViewById(R.id.exite).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        pname.setText(MainActivity.PlayerName);
        uname.setText(MainActivity.PlayerName);

        int S = 0;

        while (cursor.moveToNext())
        {
            int Id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("HiId")));
            int Q1 = Integer.parseInt(cursor.getString(cursor.getColumnIndex("Q1")));
            int Q2 = Integer.parseInt(cursor.getString(cursor.getColumnIndex("Q2")));
            int Q3 = Integer.parseInt(cursor.getString(cursor.getColumnIndex("Q3")));
            int Q4 = Integer.parseInt(cursor.getString(cursor.getColumnIndex("Q4")));
            int Q5 = Integer.parseInt(cursor.getString(cursor.getColumnIndex("Q5")));
            int Q6 = Integer.parseInt(cursor.getString(cursor.getColumnIndex("Q6")));
            int Q7 = Integer.parseInt(cursor.getString(cursor.getColumnIndex("Q7")));
            int Q8 = Integer.parseInt(cursor.getString(cursor.getColumnIndex("Q8")));
            int Q9 = Integer.parseInt(cursor.getString(cursor.getColumnIndex("Q9")));
            int Q10 = Integer.parseInt(cursor.getString(cursor.getColumnIndex("Q10")));
            int Q11 = Integer.parseInt(cursor.getString(cursor.getColumnIndex("Q11")));
            int Q12 = Integer.parseInt(cursor.getString(cursor.getColumnIndex("Q12")));

            int s = (Q1 > 0 ? 1 : -1) + (Q2 > 0 ? 1 : -1) + (Q3 > 0 ? 1 : -1) + (Q4 > 0 ? 1 : -1) + (Q5 > 0 ? 1 : -1) + (Q6 > 0 ? 1 : -1) + (Q7 > 0 ? 1 : -1) + (Q8 > 0 ? 1 : -1) + (Q9 > 0 ? 1 : -1) + (Q10 > 0 ? 1 : -1) + (Q11 > 0 ? 1 : -1) + (Q12 > 0 ? 1 : -1);

            StructNode note = new StructNode();
            note.Id = Id;
            note.onePlay.Qs[0] = Q1;
            note.onePlay.Qs[1] = Q2;
            note.onePlay.Qs[2] = Q3;
            note.onePlay.Qs[3] = Q4;
            note.onePlay.Qs[4] = Q5;
            note.onePlay.Qs[5] = Q6;
            note.onePlay.Qs[6] = Q7;
            note.onePlay.Qs[7] = Q8;
            note.onePlay.Qs[8] = Q9;
            note.onePlay.Qs[9] = Q10;
            note.onePlay.Qs[10] = Q11;
            note.onePlay.Qs[11] = Q12;
            note.Player1Name = MainActivity.PlayerName;
            note.Player1XP = String.valueOf(s);
            note.Player2Name = "Non";
            note.Player2XP = "-";
            G.nodes.add(note);
            S += s;
        }
        cursor.close();

        uxp.setText(String.valueOf(S));

        adapter.notifyDataSetChanged();

        if (adapter != null)
            adapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_play);

        ListQ = (Button) findViewById(R.id.ql);

        ListQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Qlist();
            }
        });

        ListView lstContent = (ListView) findViewById(R.id.lstContent);

        adapter = new AdapterNode(G.nodes);
        lstContent.setAdapter(adapter);

        database.execSQL("CREATE  TABLE IF  NOT EXISTS " +
                MainActivity.PlayerName + "_History" +
                " (" +
                "HiId INTEGER PRIMARY KEY  NOT NULL  UNIQUE ," +
                " Q1 INTEGER," +
                " Q2 INTEGER," +
                " Q3 INTEGER," +
                " Q4 INTEGER," +
                " Q5 INTEGER," +
                " Q6 INTEGER," +
                " Q7 INTEGER," +
                " Q8 INTEGER," +
                " Q9 INTEGER," +
                " Q10 INTEGER," +
                " Q11 INTEGER," +
                " Q12 INTEGER" +
                ")");

        AddQ = (Button) findViewById(R.id.add_Q);

        AddQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddQuestion();
            }
        });

        Play = (Button) findViewById(R.id.play);

        Play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Play();
            }
        });
    }
    public void AddQuestion()
    {
        Intent intent= new Intent(this, AddQuestion.class);
        startActivity(intent);
    }
    public void Play()
    {

        Intent intent= new Intent(this, ChooseType.class);
        startActivity(intent);
    }
    public void Qlist()
    {
        Intent intent= new Intent(this, QuestionList.class);
        startActivity(intent);
    }
}
