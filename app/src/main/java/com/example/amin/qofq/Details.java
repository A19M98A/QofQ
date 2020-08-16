package com.example.amin.qofq;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.amin.qofq.G.database;
import static com.example.amin.qofq.G.inflater;

public class Details extends AppCompatActivity {

    TextView[] textViews = new TextView[12];
    Button OK;
    TextView Type1, Type2, Type3, Type4;

    int t1q1, t1q2, t1q3, t2q1, t2q2, t2q3, t3q1, t3q2, t3q3, t4q1, t4q2, t4q3;
    int type1, type2, type3, type4;

    public static int Q ;
    public static int T ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Bundle extras = getIntent().getExtras();
        int position = extras.getInt("POSITION");
        final StructNode note = G.nodes.get(position);

        Type1 = (TextView) findViewById(R.id.type1);
        Type2 = (TextView) findViewById(R.id.type2);
        Type3 = (TextView) findViewById(R.id.type3);
        Type4 = (TextView) findViewById(R.id.type4);
        textViews[0] = (TextView) findViewById(R.id.t1q1);
        textViews[1] = (TextView) findViewById(R.id.t1q2);
        textViews[2] = (TextView) findViewById(R.id.t1q3);
        textViews[3] = (TextView) findViewById(R.id.t2q1);
        textViews[4] = (TextView) findViewById(R.id.t2q2);
        textViews[5] = (TextView) findViewById(R.id.t2q3);
        textViews[6] = (TextView) findViewById(R.id.t3q1);
        textViews[7] = (TextView) findViewById(R.id.t3q2);
        textViews[8] = (TextView) findViewById(R.id.t3q3);
        textViews[9] = (TextView) findViewById(R.id.t4q1);
        textViews[10] = (TextView) findViewById(R.id.t4q2);
        textViews[11] = (TextView) findViewById(R.id.t4q3);

        for (int i = 0; i < 12; i++)
        {
            final int finalI = i;
            textViews[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Q = Math.abs(note.onePlay.Qs[finalI] % 100000);
                    T = type1;
                    Answer();
                }
            });
        }


        OK = (Button) findViewById(R.id.ok);

        OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        type1 = Math.abs(note.onePlay.Qs[1]/100000);
        Type1.setText("Type : " + type1);
        type2 = Math.abs(note.onePlay.Qs[4]/100000);
        Type2.setText("Type : " + type2);
        type3 = Math.abs(note.onePlay.Qs[7]/100000);
        Type3.setText("Type : " + type3);
        type4 = Math.abs(note.onePlay.Qs[10]/100000);
        Type4.setText("Type : " + type4);


        for (int i = 0; i < 12; i++)
        {
            Cursor cursor = HelperSQL.SerchQuestion(String.valueOf(Math.abs(note.onePlay.Qs[i]/100000)), String.valueOf(Math.abs(note.onePlay.Qs[i] % 100000)));
            Fill(cursor, textViews[i], note.onePlay.Qs[i]);
        }

    }

    private void Fill(Cursor cursor, TextView TV, int Qi)
    {
        while (cursor.moveToNext())
        {
            String question = "";
            String[] arr = cursor.getString(cursor.getColumnIndex("Question")).split("::i::");
            for (int i = 0; i < arr.length - 1; i++)
            {
                question += arr[i] + "'";
            }
            question += arr[arr.length - 1];
            TV.setText(question);
            if (Qi > 0)
                TV.setTextColor(Color.GREEN);
            else
                TV.setTextColor(Color.RED);
            break;
        }
        cursor.close();
    }

    void Answer()
    {
        Intent intent= new Intent(this, Answer.class);
        startActivity(intent);
    }

}
