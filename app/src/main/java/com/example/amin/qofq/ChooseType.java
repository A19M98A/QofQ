package com.example.amin.qofq;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static com.example.amin.qofq.G.database;


public class ChooseType extends AppCompatActivity {

    public Button A;
    public Button B;
    public Button C;
    int Ch = 0;
    public static int Type;
    public static  OnePlay onePlay;
    Button Cansel;


    @Override
    protected void onResume() {
        super.onResume();
        if (Ch > 0)
        {
            if (onePlay.Qs[2] == 0)
                finish();
            if (Ch > 1)
            {
                if (onePlay.Qs[5] == 0)
                    finish();
                if (Ch > 2)
                {
                    if (onePlay.Qs[8] == 0)
                        finish();
                    if (Ch > 3)
                        if (onePlay.Qs[11] == 0)
                            finish();
                }
            }
        }
        if (Ch == 4)
        {
            database.execSQL("INSERT INTO amin_History (Q1,Q2,Q3,Q4,Q5,Q6,Q7,Q8,Q9,Q10,Q11,Q12) VALUES" +
                    " ('" + onePlay.Qs[0] + "'," +
                    "'" + onePlay.Qs[1] + "'," +
                    "'" + onePlay.Qs[2] + "'," +
                    "'" + onePlay.Qs[3] + "'," +
                    "'" + onePlay.Qs[4] + "'," +
                    "'" + onePlay.Qs[5] + "'," +
                    "'" + onePlay.Qs[6] + "'," +
                    "'" + onePlay.Qs[7] + "'," +
                    "'" + onePlay.Qs[8] + "'," +
                    "'" + onePlay.Qs[9] + "'," +
                    "'" + onePlay.Qs[10] + "'," +
                    "'" + onePlay.Qs[11] + "'" +
                    ")");
            finish();
        }

        int x;
        do {
            x = G.random.nextInt(10);
        }while (x == 0);
        int y;
        do {
            y = G.random.nextInt(10);
        }while (y == 0 || y == x);
        int z;
        do {
            z = G.random.nextInt(10);
        }while (z == 0 || y == z || z == x);

//        x = 1;
//        y = 1;
//        z = 1;

        A.setText("" + x + "");
        B.setText("" + y + "");
        C.setText("" + z + "");

        final int finalX = x;
        A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Type = finalX;
                Ch++;
                Play();
            }
        });
        final int finalY = y;
        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Type = finalY;
                Ch++;
                Play();
            }
        });
        final int finalZ = z;
        C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Type = finalZ;
                Ch++;
                Play();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_type);

        Ch = 0;
        onePlay = new OnePlay();

        A = (Button) findViewById(R.id.a);
        B = (Button) findViewById(R.id.b);
        C = (Button) findViewById(R.id.c);
        Cansel = (Button) findViewById(R.id.cansel);

        Cansel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        int x;
        do {
            x = G.random.nextInt(10);
        }while (x == 0);
        int y;
        do {
            y = G.random.nextInt(10);
        }while (y == 0 || y == x);
        int z;
        do {
            z = G.random.nextInt(10);
        }while (z == 0 || y == z || z == x);


        A.setText("" + x + "");
        B.setText("" + y + "");
        C.setText("" + z + "");

        final int finalX = x;
        A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Type = finalX;
                Ch++;
                Play();
            }
        });
        final int finalY = y;
        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Type = finalY;
                Ch++;
                Play();
            }
        });
        final int finalZ = z;
        C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Type = finalZ;
                Ch++;
                Play();
            }
        });
    }

    public void Play()
    {
        Intent intent= new Intent(this, Play.class);
        startActivity(intent);
    }

}
