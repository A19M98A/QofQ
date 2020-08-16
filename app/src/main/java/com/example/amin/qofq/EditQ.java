package com.example.amin.qofq;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import static com.example.amin.qofq.G.database;

public class EditQ extends AppCompatActivity {

    EditText Question, Ch1, Ch2, Ch3, Ch4;
    RadioButton A1, A2, A3, A4;
    Button Edit, Cansel;
    int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_q);

        Question = (EditText) findViewById(R.id.Question);
        Ch1 = (EditText) findViewById(R.id.Ch1);
        Ch2 = (EditText) findViewById(R.id.Ch2);
        Ch3 = (EditText) findViewById(R.id.Ch3);
        Ch4 = (EditText) findViewById(R.id.Ch4);

        A1 = (RadioButton) findViewById(R.id.A1);
        A2 = (RadioButton) findViewById(R.id.A2);
        A3 = (RadioButton) findViewById(R.id.A3);
        A4 = (RadioButton) findViewById(R.id.A4);

        Edit = (Button) findViewById(R.id.edit);
        Cansel = (Button) findViewById(R.id.cansel);

        Bundle extras = getIntent().getExtras();
        int position = 0;

        if (extras != null)
        {
            position = extras.getInt("POSITION");
        }

        final QustionNode note = G.qnodes.get(position);

        id = note.Id;

        Cursor cursor = HelperSQL.SerchQuestion("1", String.valueOf(id));

        while (cursor.moveToNext())
        {
            int Id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("Id")));
            String question = "";
            String[] arr = cursor.getString(cursor.getColumnIndex("Question")).split("::i::");
            for (int i = 0; i < arr.length - 1; i++)
            {
                question += arr[i] + "'";
            }
            question += arr[arr.length - 1];
            String ch1 = cursor.getString(cursor.getColumnIndex("Ch1"));
            String ch2 = cursor.getString(cursor.getColumnIndex("Ch2"));
            String ch3 = cursor.getString(cursor.getColumnIndex("Ch3"));
            String ch4 = cursor.getString(cursor.getColumnIndex("Ch4"));
            int Answer = Integer.parseInt(cursor.getString(cursor.getColumnIndex("Answer")));
            switch (Answer)
            {
                case 1:
                    A1.setChecked(true);
                    break;
                case 2:
                    A2.setChecked(true);
                    break;
                case 3:
                    A3.setChecked(true);
                    break;
                default:
                    A4.setChecked(true);
                    break;
            }
            Question.setText(question);
            Ch1.setText(ch1);
            Ch2.setText(ch2);
            Ch3.setText(ch3);
            Ch4.setText(ch4);
        }
        cursor.close();

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

        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int Answer = 0;
                if (A1.isChecked())
                    Answer = 1;
                else if (A2.isChecked())
                    Answer = 2;
                else if (A3.isChecked())
                    Answer = 3;
                else if (A4.isChecked())
                    Answer = 4;
                else
                    Toast.makeText(EditQ.this, "chose answer", Toast.LENGTH_LONG).show();

                String question = "";
                String[] arr = Question.getText().toString().split("'");
                for (int i = 0; i < arr.length - 1; i++)
                {
                    question += arr[i] + "::i::";
                }
                question += arr[arr.length - 1];
                database.execSQL("UPDATE QType1 SET " +
                        "Question = '"+ question +"', " +
                        "Ch1 = '"+ Ch1.getText() +"', " +
                        "Ch2 = '"+ Ch2.getText() +"', " +
                        "Ch3 = '"+ Ch3.getText() +"', " +
                        "Ch4 = '"+ Ch4.getText() +"', " +
                        "Answer = '"+ Answer +"' " +
                        "WHERE  Id = '" + id +"'" +
                        "");
                finish();
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
