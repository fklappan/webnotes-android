package de.fklappan.app.webnotes;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

import de.fklappan.app.webnotes.common.TestLogger;
import de.fklappan.app.webnotes.common.TestSchedulers;
import de.fklappan.app.webnotes.model.Note;
import de.fklappan.app.webnotes.service.NoteRepository;
import de.fklappan.app.webnotes.ui.overview.OverviewContract;
import de.fklappan.app.webnotes.ui.overview.OverviewPresenter;
import de.fklappan.app.webnotes.ui.overview.OverviewView;
import io.reactivex.Single;

public class OverviewPresenterTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    OverviewView view;

    @Test
    public void testPresenterSetup() {

        NoteRepository testRepository = new NoteRepository() {
            @Override
            public Single<List<Note>> getAllNotes() {
                List<Note> noteList = new ArrayList<>();
                Note note = new Note();
                note.setId(1L);
                note.setTitle("title1");
                note.setContent("content1");
                noteList.add(note);
                return Single.just(noteList);
            }

            @Override
            public Single<Note> getNote(long id) {
                return null;
            }

            @Override
            public Single<Boolean> saveNote(String title, String content) {
                return null;
            }

            @Override
            public Single<Boolean> updateNote(Long id, String title, String content) {
                return null;
            }

            @Override
            public Single<Boolean> saveOrUpdateNote(Note note) {
                return null;
            }
        };

        OverviewContract.Presenter uut = new OverviewPresenter(view, testRepository, new TestSchedulers(), new TestLogger());
        Mockito.verify(view, Mockito.atLeastOnce()).onLoading();
        ArgumentCaptor<List> captor = ArgumentCaptor.forClass(List.class);
        Mockito.verify(view, Mockito.times(1)).onData(captor.capture());
        Assert.assertEquals(1, captor.getValue().size());
    }

}
