package com.example.andranikh.lab2.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andranikh.lab2.R;
import com.example.andranikh.lab2.pojos.Note;
import com.example.andranikh.lab2.utils.PreferancesHelper;
import com.example.andranikh.lab2.utils.note.FileNoteStorage;
import com.example.andranikh.lab2.utils.note.NoteStorage;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import petrov.kristiyan.colorpicker.ColorPicker;

public class MainActivity extends Activity implements View.OnClickListener {

    public final int SAVECREATEDNOTE = 1;

    RecyclerView recyclerView;
    MyAdapter adapter;
    RecyclerView.LayoutManager layoutManager;

    List<Note> notesList;

    FileNoteStorage fileNoteStorage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notesList = new ArrayList<>();

        fileNoteStorage = new FileNoteStorage();

        recyclerView = (RecyclerView)findViewById(R.id.main_activity_recycler_view);

        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new MyAdapter();

        fileNoteStorage.findAllNotes(new NoteStorage.NotesFoundListener() {
            @Override
            public void onNotesFound(List<Note> notes) {
                notesList = notes;
            }
        });

        adapter.setNoteList(notesList);
        recyclerView.setAdapter(adapter);

        setTitle("Main Screen");

        findViewById(R.id.activity_main_logout).setOnClickListener(this);
        findViewById(R.id.activity_main_floating_action_btn).setOnClickListener(this);
    }

    private void logout(){
        PreferancesHelper.getInstance(this).resetAll();
        startActivity(new Intent(this, StartupActivity.class));
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.activity_main_logout:
                logout();
                break;

            case R.id.activity_main_floating_action_btn:
                Intent intent = new Intent(this,CreateNoteActivity.class);
                startActivityForResult(intent,SAVECREATEDNOTE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("note","reques code: " + requestCode + " result code: " + resultCode);

        if (resultCode == RESULT_OK){
            Note note = new Note();
            Log.d("ccolor","hasav");
            note = (Note) data.getExtras().getSerializable("note");
            adapter.addNote(note);
        }
        else {
            Toast.makeText(this, "You dont save note.", Toast.LENGTH_SHORT).show();
        }

        Log.d("note","arraylisttttt size: " + fileNoteStorage.notesWrapper.getNotes().size());
        }

}

