package de.fklappan.app.webnotes.ui.edit;

import de.fklappan.app.webnotes.common.mvx.MvxPresenter;
import de.fklappan.app.webnotes.common.mvx.ObservableMvxView;
import de.fklappan.app.webnotes.model.Note;

public interface EditContract {

    interface View extends ObservableMvxView<ViewListener> {

        void onData(Note note);

        String getTitle();

        String getContent();
    }

    interface Presenter extends MvxPresenter {
    }

    interface ViewListener {
        void onSaveClicked();
    }
}
