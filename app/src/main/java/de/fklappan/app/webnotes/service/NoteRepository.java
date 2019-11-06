package de.fklappan.app.webnotes.service;

import java.util.List;

import de.fklappan.app.webnotes.model.Note;
import io.reactivex.Single;

public interface NoteRepository {

    Single<List<Note>> getAllNotes();
    Single<Note> getNote(long id);
    Single<Boolean> saveNote(String title, String content);
    Single<Boolean> updateNote(Long id, String title, String content);
    Single<Boolean> saveOrUpdateNote(Note note);

}
