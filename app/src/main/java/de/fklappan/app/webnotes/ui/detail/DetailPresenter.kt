package de.fklappan.app.webnotes.ui.detail


import de.fklappan.app.webnotes.common.logging.Logger
import de.fklappan.app.webnotes.common.navigation.NoteFlowCoordinator
import de.fklappan.app.webnotes.common.rx.SchedulerProvider
import de.fklappan.app.webnotes.model.Note
import de.fklappan.app.webnotes.service.NoteRepository
import io.reactivex.disposables.CompositeDisposable

class DetailPresenter(private var view: DetailContract.View?, noteId: Long?,
                      private val noteRepository: NoteRepository,
                      private val schedulers: SchedulerProvider,
                      private val logger: Logger,
                      private val noteFlowCoordinator: NoteFlowCoordinator)
    : DetailContract.Presenter, DetailContract.ViewListener {

    private val disposables = CompositeDisposable()
    private lateinit var note: Note

    init {
        loadNote(noteId)
        view!!.registerListener(this)
    }

    private fun loadNote(id: Long?) {
        disposables.add(noteRepository.getNote(id!!)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .subscribe({
                    this.renderNote(it)
                    this.note = it
                }, {
                    err -> logger.e(LOG_TAG, "Error fetching note: ", err)
                }))
    }

    private fun renderNote(note: Note) {
        view!!.setTitle(note.title)
        view!!.setContent(note.content)
    }

    override fun cleanup() {
        view!!.unregisterListener(this)
        disposables.dispose()
        view = null
    }

    companion object {

        private val LOG_TAG = DetailPresenter::class.java.simpleName
    }

    override fun getNote(): Note {
        return note
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // DetailContract.ViewListener impl

    override fun onEditClicked() {
        noteFlowCoordinator.editNote(getNote().id)
    }

}
