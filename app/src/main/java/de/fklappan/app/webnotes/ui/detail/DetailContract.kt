package de.fklappan.app.webnotes.ui.detail

import de.fklappan.app.webnotes.common.mvx.MvxPresenter
import de.fklappan.app.webnotes.common.mvx.ObservableMvxView
import de.fklappan.app.webnotes.model.Note

interface DetailContract {

    // TODO 15.06.2019 Flo i consider it bad practice to mix languages in one project,
    //  so move back to java for the webnotes project. kotlin will be used in an other project
    interface View : ObservableMvxView<ViewListener> {

        fun setTitle(title: String)

        fun setContent(content: String)

        fun startEditMode()
    }

    interface Presenter : MvxPresenter {

        fun getNote(): Note
    }

    interface ViewListener {
        fun onEditClicked()
    }
}
