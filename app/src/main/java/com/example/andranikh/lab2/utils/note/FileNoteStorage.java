package com.example.andranikh.lab2.utils.note;

import com.example.andranikh.lab2.App;
import com.example.andranikh.lab2.pojos.Note;
import com.example.andranikh.lab2.utils.PreferancesHelper;
import com.example.andranikh.lab2.utils.StorageHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Taron on 05/06/17.
 */

public class FileNoteStorage extends NoteStorage {

    private static final String NOTE_FILE_NAME = "FileNoteStorage.Notes";

    public NotesWrapper notesWrapper;

    public FileNoteStorage() {
        notesWrapper = (NotesWrapper) StorageHelper.deserialize(getFileName());

        if (notesWrapper == null){
            notesWrapper = new NotesWrapper();
        }

    }

    private String getFileName(){
        return String.format("%s_%s",
                NOTE_FILE_NAME,
                PreferancesHelper.getInstance(App.getInstance()).getUserId());
    }

    protected List<Note> getNotes(){
        return notesWrapper.getNotes();
    }

    @Override
    public void createNote(Note note, NoteListener noteListener) {
        note.setId(System.currentTimeMillis());
        note.setCreateDate(new Date());

        getNotes().add(note);
        StorageHelper.serialize(getFileName(), notesWrapper);

        notifyNoteFound(note, noteListener);
    }

    @Override
    public void findNote(long id, NoteListener noteListener) {
        for (Note note : notesWrapper.getNotes()){
            if (note.getId() == id){
                notifyNoteFound(note,noteListener);
                return;
            }
        }

        notifyNoteFound(null,noteListener);
    }

    @Override
    public void findAllNotes(NotesFoundListener notesFoundListener) {
        notifyNotesFound(notesWrapper.getNotes(), notesFoundListener);
    }

    @Override
    public void updateNote(Note note, NoteListener noteListener) {

    }

    @Override
    public void deleteNote(Note note, NoteDeleteListener noteDeleteListener) {
        boolean isDeleted = false;
        for (Note e : getNotes()){
            if (e.getId() == note.getId()){
                getNotes().remove(e);
                isDeleted = true;
            }
        }
        notifuNoteRemove(isDeleted, noteDeleteListener);
    }

    public static class NotesWrapper implements Serializable{
        static final long serialVersionUID = -1;

        private List<Note> notes;

        public NotesWrapper() {
            notes = new ArrayList<>();
        }

        public List<Note> getNotes() {
            return notes;
        }

    }
}
