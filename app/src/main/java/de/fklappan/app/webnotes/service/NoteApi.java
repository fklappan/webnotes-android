package de.fklappan.app.webnotes.service;

import java.util.List;


import de.fklappan.app.webnotes.model.Note;
import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface NoteApi {

    @GET("service/v1/note/getall.php")
    Single<List<Note>> getAllNotes();

    @GET("service/v1/note/getnote.php")
    Single<Note> getNote(@Query("id") String id);

    @FormUrlEncoded
    @POST("service/v1/note/savenote.php")
    Single<Boolean> saveNote(@Field("title") String title, @Field("content") String content);

    @FormUrlEncoded
    @POST("service/v1/note/updatenote.php")
    Single<Boolean> updateNote(@Field("id") Long id, @Field("title") String title, @Field("content") String content);

}
