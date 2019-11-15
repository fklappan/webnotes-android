package de.fklappan.app.webnotes.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import de.fklappan.app.webnotes.common.BaseFragment
import de.fklappan.app.webnotes.common.Parameters.EXTRA_NOTE_ID
import de.fklappan.app.webnotes.common.logging.Logger
import de.fklappan.app.webnotes.common.navigation.NoteFlowCoordinator
import de.fklappan.app.webnotes.common.rx.SchedulerProvider
import de.fklappan.app.webnotes.service.NoteService
import javax.inject.Inject

public class DetailFragment : BaseFragment() {

    private lateinit var detailView: DetailContract.View
    private lateinit var detailPresenter: DetailContract.Presenter
    @Inject lateinit var logger: Logger
    @Inject lateinit var noteService: NoteService
    @Inject lateinit var appSchedulers: SchedulerProvider
    @Inject lateinit var noteFlowCoordinator: NoteFlowCoordinator

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        injector.inject(this)
        super.onCreateView(inflater, container, savedInstanceState)

        detailView = DetailView(inflater, null)

        detailPresenter = DetailPresenter(detailView, arguments!!.getLong(EXTRA_NOTE_ID), noteService, appSchedulers, logger, noteFlowCoordinator)

        return detailView.rootView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        detailPresenter.cleanup()
    }

}