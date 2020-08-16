package com.example.amin.qofq;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.security.acl.Group;
import java.util.ArrayList;

public class AdapterNode extends ArrayAdapter<StructNode>{

    public AdapterNode(ArrayList<StructNode> array)
    {
        super(G.context, R.layout.adapter_notes, array);
    }

    public static class ViewHolder
    {
        public LinearLayout layoutRoot;
        public ImageView P1Img;
        public TextView P1Name;
        public TextView P1XP;
        public ImageView P2Img;
        public TextView P2Name;
        public TextView P2XP;

        public ViewHolder(View view) {
            layoutRoot = (LinearLayout) view.findViewById(R.id.layoutRoot);
            P1Img = (ImageView) view.findViewById(R.id.p1Img);
            P1Name = (TextView) view.findViewById(R.id.p1Name);
            P1XP = (TextView) view.findViewById(R.id.p1XP);
            P2Img = (ImageView) view.findViewById(R.id.p2Img);
            P2Name = (TextView) view.findViewById(R.id.p2Name);
            P2XP = (TextView) view.findViewById(R.id.p2XP);

        }

        public void fill(final ArrayAdapter<StructNode> adapter, final StructNode item, final int position)
        {

            P1Name.setText(item.Player1Name);
            P1XP.setText(item.Player1XP);
            P2Name.setText(item.Player2Name);
            P2XP.setText(item.Player2XP);

//            imgDelete.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    adapter.remove(item);
//                }
//            });

            layoutRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(G.currentActivity, Details.class);
                    intent.putExtra("POSITION", position);
                    G.currentActivity.startActivity(intent);
                }
            });

            layoutRoot.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    database.execSQL("DELETE FROM " + MainActivity.PlayerName + "_History" + " WHERE HiId = '" + item.Id + "'");
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
        StructNode item = getItem(position);
        if (convertView == null)
        {
            convertView = G.inflater.inflate(R.layout.adapter_notes, parent, false);
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
