package com.example.amin.qofq;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.amin.qofq.G.database;

public class QuestionAdapter extends ArrayAdapter<QustionNode> {

    public QuestionAdapter(ArrayList<QustionNode> array)
    {
        super(G.context, R.layout.activity_question_adapter, array);
    }

    public static class ViewHolder
    {
        public LinearLayout layoutRoot;
        public TextView Question;

        public ViewHolder(View view) {

            layoutRoot = (LinearLayout) view.findViewById(R.id.layoutRoot);
            Question = (TextView) view.findViewById(R.id.qq);

        }

        public void fill(final ArrayAdapter<QustionNode> adapter, final QustionNode item, final int position)
        {

            //Question.setText(item.Question);
            Question.setText(item.Question);

            layoutRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(G.currentActivity, EditQ.class);
                    intent.putExtra("POSITION", position);
                    G.currentActivity.startActivity(intent);
                }
            });

            layoutRoot.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    database.execSQL("DELETE FROM QType1" + " WHERE Id = '" + item.Id + "'");
                    adapter.remove(item);
                    return false;
                }
            });
        }

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        ViewHolder holder;
        QustionNode item = getItem(position);
        if (convertView == null)
        {
            convertView = G.inflater.inflate(R.layout.activity_question_adapter, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.fill(this, item, position);
        return convertView;
    }

}
