package com.example.andranikh.lab2.activities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.andranikh.lab2.R;
import com.example.andranikh.lab2.pojos.Note;

/**
 * Created by Taron on 05/11/17.
 */
public class MyViewHolder extends RecyclerView.ViewHolder {

    Context context;

    View view;
    View devideview;
    TextView titleTv;
    TextView descriptionTv;
    TextView dateTv;
    CheckBox isImportant;

    public MyViewHolder(View itemView, Context context) {
        super(itemView);
        Log.d("bag","MyViewHolder constractor 2");
        this.context = context;
        view = itemView.findViewById(R.id.item_view);
        devideview = itemView.findViewById(R.id.item_devide_view);
        titleTv = (TextView)itemView.findViewById(R.id.item_title);
        descriptionTv = (TextView)itemView.findViewById(R.id.item_description);
        dateTv = (TextView)itemView.findViewById(R.id.item_date);
        isImportant = (CheckBox)itemView.findViewById(R.id.item_important_check);

    }

    public void bind(Note note) {
        Log.d("bag","MyViewHolder bind");
        view.setBackgroundColor(note.getColor());
        devideview.setBackgroundColor(note.getColor());
        titleTv.setText(note.getTitle());
        descriptionTv.setText(note.getDescription());
        dateTv.setText(note.getCreateDate().toString());
        isImportant.setChecked(note.isImportant());
    }
}
