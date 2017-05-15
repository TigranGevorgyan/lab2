package com.example.andranikh.lab2.utils.note;

import com.example.andranikh.lab2.pojos.Note;

import java.util.List;

/**
 * Created by Taron on 05/06/17.
 */

public abstract class NoteStorage {

    public abstract void createNote(Note note, NoteListener noteListener);

    public abstract void findNote(long id, NoteListener noteListener);

    public abstract void findAllNotes(NotesFoundListener notesFoundListener);

    public abstract void updateNote(Note note, NoteListener noteListener);

    public abstract void deleteNote(Note note, NoteDeleteListener noteDeleteListener);

    protected void notifyNoteFound(Note note, NoteListener onNoteListener){
        if (onNoteListener != null)
            onNoteListener.onNote(note);
    }

    protected void notifyNotesFound(List<Note> notes,NotesFoundListener notesFoundListener){
        if (notesFoundListener != null){
            notesFoundListener.onNotesFound(notes);
        }
    }

    protected void notifuNoteRemove(boolean isDeleted, NoteDeleteListener noteDeleteListener){
        if (noteDeleteListener != null){
            noteDeleteListener.onNoteDeleted(isDeleted);
        }
    }

    public interface NoteDeleteListener{
        void onNoteDeleted(boolean successful);
    }


    public interface NotesFoundListener{
        void onNotesFound(List<Note> notes);
    }

    public interface NoteListener{
        void onNote(Note note);
    }

}
