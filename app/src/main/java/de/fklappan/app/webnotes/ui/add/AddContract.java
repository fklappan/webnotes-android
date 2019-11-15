package de.fklappan.app.webnotes.ui.add;

import de.fklappan.app.webnotes.common.mvx.MvxPresenter;
import de.fklappan.app.webnotes.common.mvx.ObservableMvxView;

public interface AddContract {

    interface View extends ObservableMvxView<ViewListener> {
        String getTitle();

        String getContent();
    }

    interface Presenter extends MvxPresenter {
    }

    interface ViewListener {
        void onSaveClicked();
    }
}
