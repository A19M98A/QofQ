package com.example.amin.qofq;

import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import static com.example.amin.qofq.G.database;

public class Play extends AppCompatActivity {

    public static int QNamber, C;
    public TextView Question, Ch1, Ch2, Ch3, Ch4;
    public String question, ch1, ch2, ch3, ch4;
    int Answer;
    int A;
    boolean Choose = false;
    public Button Next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        Question = (TextView) findViewById(R.id.Question);
        Ch1 = (TextView) findViewById(R.id.Ch1);
        Ch2 = (TextView) findViewById(R.id.Ch2);
        Ch3 = (TextView) findViewById(R.id.Ch3);
        Ch4 = (TextView) findViewById(R.id.Ch4);
        Next = (Button) findViewById(R.id.next);

        QNamber = 0;
        C = 0;

        Ch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Answer != 1)
                    Ch1.setBackgroundColor(Color.RED);
                Pinc();
                A = 1;
                C++;
                Choose = true;
            }
        });
        Ch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Answer != 2)
                    Ch2.setBackgroundColor(Color.RED);
                Pinc();
                A = 2;
                C++;
                Choose = true;
            }
        });
        Ch3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Answer != 3)
                    Ch3.setBackgroundColor(Color.RED);
                Pinc();
                A = 3;
                C++;
                Choose = true;
            }
        });
        Ch4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Answer != 4)
                    Ch4.setBackgroundColor(Color.RED);
                Pinc();
                A = 4;
                C++;
                Choose = true;
            }
        });

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Choose)
                {
                    if (A == Answer)
                        ChooseType.onePlay.Add(ChooseType.Type * 100000 + QNamber);
                    else
                        ChooseType.onePlay.Add(ChooseType.Type * -100000 - QNamber);
                    if (C < 3)
                        Fill();
                    else
                        finish();
                }
            }
        });

        Fill();
    }

    void Pinc()
    {
        Ch1.setEnabled(false);
        Ch2.setEnabled(false);
        Ch3.setEnabled(false);
        Ch4.setEnabled(false);
        switch (Answer)
        {
            case 1:
                Ch1.setBackgroundColor(Color.GREEN);
                break;
            case 2:
                Ch2.setBackgroundColor(Color.GREEN);
                break;
            case 3:
                Ch3.setBackgroundColor(Color.GREEN);
                break;
            case 4:
                Ch4.setBackgroundColor(Color.GREEN);
                break;
        }
    }

    public void Fill()
    {
        Ch1.setEnabled(true);
        Ch2.setEnabled(true);
        Ch3.setEnabled(true);
        Ch4.setEnabled(true);
        boolean F = true;
        Choose = false;
        while (F)
        {
            Random random = new Random();
            int X  = random.nextInt(1000);
            Cursor cursor = database.rawQuery("SELECT * FROM QType" + ChooseType.Type + " WHERE Id = '" + X +"'",null);

            while (cursor.moveToNext())
            {
                QNamber = X;
                String question = "";
                String[] arr = cursor.getString(cursor.getColumnIndex("Question")).split("::i::");
                for (int i = 0; i < arr.length - 1; i++)
                {
                    question += arr[i] + "'";
                }
                question += arr[arr.length - 1];
                Question.setText(question);
                Ch1.setText(cursor.getString(cursor.getColumnIndex("Ch1")));
                Ch2.setText(cursor.getString(cursor.getColumnIndex("Ch2")));
                Ch3.setText(cursor.getString(cursor.getColumnIndex("Ch3")));
                Ch4.setText(cursor.getString(cursor.getColumnIndex("Ch4")));
                Ch1.setTextColor(Color.GRAY);
                Ch2.setTextColor(Color.GRAY);
                Ch3.setTextColor(Color.GRAY);
                Ch4.setTextColor(Color.GRAY);
                Ch1.setBackgroundColor(Color.BLUE);
                Ch2.setBackgroundColor(Color.BLUE);
                Ch3.setBackgroundColor(Color.BLUE);
                Ch4.setBackgroundColor(Color.BLUE);
                Answer = Integer.parseInt(cursor.getString(cursor.getColumnIndex("Answer")));
                F = false;
                break;
            }
            cursor.close();
        }
    }
}
