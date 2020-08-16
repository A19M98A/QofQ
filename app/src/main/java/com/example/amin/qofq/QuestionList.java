package com.example.amin.qofq;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import static com.example.amin.qofq.G.database;

public class QuestionList extends AppCompatActivity {

    public ArrayAdapter adapter;

    @Override
    protected void onResume() {

        G.currentActivity = this;
        super.onResume();
        G.qnodes.clear();

        for (int i = 1; i < 10; i++){
            Cursor cursor = database.rawQuery("SELECT * FROM QType" + String.valueOf(i) + " WHERE Id > '0'",null);

            while (cursor.moveToNext())
            {
                int Id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("Id")));
                String Question = "";
                String[] arr = cursor.getString(cursor.getColumnIndex("Question")).split("::i::");
                for (int j = 0; j < arr.length - 1; j++)
                {
                    Question += arr[i] + "'";
                }
                Question += arr[arr.length - 1];
                QustionNode note = new QustionNode();
                note.Id = Id;
                note.Question = Question;
                G.qnodes.add(note);
            }
            cursor.close();
        }
        adapter.notifyDataSetChanged();

        if (adapter != null)
            adapter.notifyDataSetChanged();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_list);

        ListView lstContent = (ListView) findViewById(R.id.qqq);

        adapter = new QuestionAdapter(G.qnodes);
        lstContent.setAdapter(adapter);

        G.currentActivity = this;
        super.onResume();
        G.qnodes.clear();

        Cursor cursor = database.rawQuery("SELECT * FROM QType1" + "" + " WHERE Id > '0'",null);

        while (cursor.moveToNext())
        {
            int Id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("Id")));
            String Question = "";
            String[] arr = cursor.getString(cursor.getColumnIndex("Question")).split("::i::");
            for (int i = 0; i < arr.length - 1; i++)
            {
                Question += arr[i] + "'";
            }
            Question += arr[arr.length - 1];
            QustionNode note = new QustionNode();
            note.Id = Id;
            note.Question = Question;

            G.qnodes.add(note);
        }
        cursor.close();
        adapter.notifyDataSetChanged();
    }
}
