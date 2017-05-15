package com.example.andranikh.lab2.activities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.andranikh.lab2.R;
import com.example.andranikh.lab2.pojos.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Taron on 05/11/17.
 */

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    List<Note> noteList;
    Context context;

    public MyAdapter() {
        Log.d("bag","MyAdapter constructor");
        noteList = new ArrayList<>();
    }

    public List<Note> getNoteList() {
        return noteList;
    }

    public void setNoteList(List<Note> noteList) {
        this.noteList = noteList;
        notifyDataSetChanged();

    }

    public void addNote(Note note){
        noteList.add(note);
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("bag","onCreateViewHolder 1");
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_note,parent,false);
        Log.d("bag","onCreateViewHolder 2");
        return new MyViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bind(noteList.get(position));
        Log.d("bag","onBindViewHolder 1");

    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }
}
