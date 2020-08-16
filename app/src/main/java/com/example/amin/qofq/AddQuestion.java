package com.example.amin.qofq;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import static com.example.amin.qofq.G.database;

public class AddQuestion extends AppCompatActivity {

    public EditText Qtype;
    public EditText Question;
    public EditText Ch1;
    public EditText Ch2;
    public EditText Ch3;
    public EditText Ch4;
    public RadioButton A1;
    public RadioButton A2;
    public RadioButton A3;
    public RadioButton A4;
    public Button Add;
    public Button Cansel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        Qtype = (EditText) findViewById(R.id.QType);
        Question = (EditText) findViewById(R.id.Question);
        Ch1 = (EditText) findViewById(R.id.Ch1);
        Ch2 = (EditText) findViewById(R.id.Ch2);
        Ch3 = (EditText) findViewById(R.id.Ch3);
        Ch4 = (EditText) findViewById(R.id.Ch4);
        A1 = (RadioButton) findViewById(R.id.A1);
        A2 = (RadioButton) findViewById(R.id.A2);
        A3 = (RadioButton) findViewById(R.id.A3);
        A4 = (RadioButton) findViewById(R.id.A4);
        Add = (Button) findViewById(R.id.add);
        Cansel = (Button) findViewById(R.id.cansel);

        A1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                A2.setChecked(false);
                A3.setChecked(false);
                A4.setChecked(false);
            }
        });
        A2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                A1.setChecked(false);
                A3.setChecked(false);
                A4.setChecked(false);
            }
        });
        A3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                A2.setChecked(false);
                A1.setChecked(false);
                A4.setChecked(false);
            }
        });
        A4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                A2.setChecked(false);
                A3.setChecked(false);
                A1.setChecked(false);
            }
        });

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int Type = Integer.parseInt(Qtype.getText().toString());
//                int Type = 1;
                if (Type < 10 && Type > 0 && (A1.isChecked() || A2.isChecked() || A3.isChecked() || A4.isChecked()))
                {
                    String question = "";
                    String[] arr = Question.getText().toString().split("'");
                    for (int i = 0; i < arr.length - 1; i++)
                    {
                        question += arr[i] + "::i::";
                    }
                    question += arr[arr.length - 1];
                    String ch1 = Ch1.getText().toString();
                    String ch2 = Ch2.getText().toString();
                    String ch3 = Ch3.getText().toString();
                    String ch4 = Ch4.getText().toString();

                    int A = A1.isChecked() ? 1 : A2.isChecked() ? 2 : A3.isChecked() ? 3 : 4;

                    database.execSQL("INSERT INTO QType" + Type +
                            " (Question,Ch1,Ch2,Ch3,Ch4,Answer) " +
                            "VALUES ('" + question + "'," +
                            "'" + ch1 + "'," +
                            "'" + ch2 + "'," +
                            "'" + ch3 + "'," +
                            "'" + ch4 + "'," +
                            "'" + A + "')");
                    finish();
                }
                else
                    Toast.makeText(AddQuestion.this, "Type had be 1-9, chose answer", Toast.LENGTH_LONG).show();
            }
        });

        Cansel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
