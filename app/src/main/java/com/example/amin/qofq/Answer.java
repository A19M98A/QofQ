package com.example.amin.qofq;

import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import static com.example.amin.qofq.G.database;

public class Answer extends AppCompatActivity {

    TextView Q;
    TextView A;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        Q = (TextView) findViewById(R.id.q);
        A = (TextView) findViewById(R.id.a);

        Cursor cursor = HelperSQL.SerchQuestion(String.valueOf(Details.T), String.valueOf(Details.Q));

        while (cursor.moveToNext())
        {
            String question = "";
            String[] arr = cursor.getString(cursor.getColumnIndex("Question")).split("::i::");
            for (int i = 0; i < arr.length - 1; i++)
            {
                question += arr[i] + "'";
            }
            question += arr[arr.length - 1];
            Q.setText(question);
            String An = cursor.getString(cursor.getColumnIndex("Answer"));
            A.setText(cursor.getString(cursor.getColumnIndex("Ch" + An)));
            break;
        }
        cursor.close();

    }

}
