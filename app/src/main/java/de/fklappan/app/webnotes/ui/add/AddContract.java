package de.fklappan.app.webnotes.ui.add;

import de.fklappan.app.webnotes.ui.edit.EditContract;

public interface AddContract {

    interface View extends EditContract.View {
        void setPresenter(Presenter presenter);
    }

    interface Presenter extends EditContract.Presenter {
    }
}
