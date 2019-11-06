package de.fklappan.app.webnotes.service;

import java.util.List;

import javax.inject.Inject;

import de.fklappan.app.webnotes.BuildConfig;
import de.fklappan.app.webnotes.model.Note;
import io.reactivex.Single;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class NoteService implements NoteRepository{

    private NoteApi noteApi;

    @Inject
    public NoteService() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        // TODO: 07.05.2019 url should be provided by app settings
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.SERVICE_URL)
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

                .build();

        noteApi = retrofit.create(NoteApi.class);
    }

    @Override
    public Single<List<Note>> getAllNotes()  {
        return noteApi.getAllNotes();
    }

    @Override
    public Single<Note> getNote(long id) {
        return noteApi.getNote(Long.toString(id));
    }

    @Override
    public Single<Boolean> saveNote(String title, String content) {
        return noteApi.saveNote(title, content);
    }

    @Override
    public Single<Boolean> updateNote(Long id, String title, String content) {
        return noteApi.updateNote(id, title, content);
    }

    @Override
    public Single<Boolean> saveOrUpdateNote(Note note) {
        if (note.getId() == null) {
            return saveNote(note.getTitle(), note.getContent());
        }
        return updateNote(note.getId(), note.getTitle(), note.getContent());
    }
}
