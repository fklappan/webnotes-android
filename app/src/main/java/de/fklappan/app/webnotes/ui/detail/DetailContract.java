package de.fklappan.app.webnotes.ui.detail;

import de.fklappan.app.webnotes.common.mvx.MvxPresenter;
import de.fklappan.app.webnotes.common.mvx.ObservableMvxView;

public interface DetailContract {

    interface View extends ObservableMvxView<ViewListener> {

        void setTitle(String title);

        void setContent(String content);
    }

    interface Presenter extends MvxPresenter {
    }

    interface ViewListener {
        void onEditClicked();
    }
}
