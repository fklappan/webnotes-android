package de.fklappan.app.webnotes.common;

import java.util.List;

import de.fklappan.app.webnotes.model.Note;
import de.fklappan.app.webnotes.service.NoteRepository;
import io.reactivex.Observable;

public class NoteTestService implements NoteRepository {

    @Override
    public Observable<List<Note>> getAllNotes() {
        return null;
    }

    @Override
    public Observable<Note> getNote(long id) {
        return null;
    }

    @Override
    public Observable<Boolean> saveNote(String title, String content) {
        return null;
    }

    @Override
    public Observable<Boolean> updateNote(Long id, String title, String content) {
        return null;
    }

    @Override
    public Observable<Boolean> saveOrUpdateNote(Note note) {
        return null;
    }
}
