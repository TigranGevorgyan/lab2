package com.example.andranikh.lab2.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.andranikh.lab2.R;
import com.example.andranikh.lab2.pojos.Note;
import com.example.andranikh.lab2.utils.note.FileNoteStorage;
import com.example.andranikh.lab2.utils.note.NoteStorage;

import petrov.kristiyan.colorpicker.ColorPicker;

public class CreateNoteActivity extends AppCompatActivity implements View.OnClickListener, ColorPicker.OnChooseColorListener {

    Button saveButton;
    Button cancelButton;

    TextView chooseColor;

    EditText title;
    EditText description;
    CheckBox markAsImportant;

    String getTitle;
    String getDescription;
    boolean isImportant;

    private int selectedColor;

    ColorPicker colorPicker;

    FileNoteStorage fileNoteStorage;
    Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        colorPicker = new ColorPicker(this);
        colorPicker.setOnChooseColorListener(this);

        title = (EditText)findViewById(R.id.create_title_edittext);
        description = (EditText)findViewById(R.id.create_description_edittext);
        markAsImportant = (CheckBox)findViewById(R.id.create_mark_as_important_checkbox);
        chooseColor = (TextView)findViewById(R.id.activity_main_color_picker_textview);

        selectedColor = Color.BLACK;

        chooseColor.setOnClickListener(this);

        fileNoteStorage = new FileNoteStorage();

        saveButton = (Button)findViewById(R.id.create_save_button);
        cancelButton = (Button)findViewById(R.id.create_cancel_button);
        saveButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final Intent intent = new Intent();
        switch (v.getId()){
            case R.id.create_save_button:
                Log.d("testt", " CASE create_save_button");
                getTitle = title.getText().toString();
                getDescription = description.getText().toString();
                isImportant = markAsImportant.isChecked();
                note = new Note(getTitle,getDescription, selectedColor,isImportant);
                Log.d("ccolor","savei mej");
                fileNoteStorage.createNote(note, new NoteStorage.NoteListener() {
                    @Override
                    public void onNote(Note note) {
                        intent.putExtra("note",note);
                        setResult(RESULT_OK,intent);
                        finish();
                    }
                });
                break;

            case R.id.create_cancel_button:
                setResult(RESULT_CANCELED,intent);
                finish();
                break;

            case R.id.activity_main_color_picker_textview:
                Log.d("testt", " CASE activity_main_color_picker_textview");
                colorPicker.show();
                break;
        }


    }

    @Override
    public void onChooseColor(int position, int color) {
        Log.d("ccolor","inside onChoose" + " pos: " + position);
        selectedColor = color;
        Log.d("ccolor","" + selectedColor);

    }

    @Override
    public void onCancel()
    {
        Log.d("ccolor","inside onCancel");
        selectedColor = Color.WHITE;
    }
}
