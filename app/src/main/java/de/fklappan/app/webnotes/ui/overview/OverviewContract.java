package de.fklappan.app.webnotes.ui.overview;

import java.util.List;

import de.fklappan.app.webnotes.common.mvx.MvxPresenter;
import de.fklappan.app.webnotes.common.mvx.ObservableMvxView;
import de.fklappan.app.webnotes.model.Note;

public interface OverviewContract {

    interface View extends ObservableMvxView<Listener> {

        void onLoading();

        void onData(List<Note> noteList);

        void onError(Throwable error);
    }

    interface Listener {
        void onNoteClicked(Note note);

        void onAddClicked();

        void onRefresh();
    }

    interface Presenter extends MvxPresenter {
    }
}
