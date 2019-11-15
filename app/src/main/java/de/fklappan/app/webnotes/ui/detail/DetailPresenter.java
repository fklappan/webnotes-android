package de.fklappan.app.webnotes.ui.detail;

import de.fklappan.app.webnotes.common.logging.Logger;
import de.fklappan.app.webnotes.common.navigation.NoteFlowCoordinator;
import de.fklappan.app.webnotes.common.rx.SchedulerProvider;
import de.fklappan.app.webnotes.model.Note;
import de.fklappan.app.webnotes.service.NoteService;
import io.reactivex.disposables.CompositeDisposable;

public class DetailPresenter implements DetailContract.Presenter, DetailContract.ViewListener {

    private static final String LOG_TAG = DetailPresenter.class.getSimpleName();

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // member variables

    private DetailContract.View view;
    private Note note;
    private Logger logger;
    private CompositeDisposable disposables = new CompositeDisposable();
    private SchedulerProvider schedulers;
    private NoteFlowCoordinator noteFlowCoordinator;
    private NoteService noteRepository;

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // construction

    public DetailPresenter(DetailContract.View view, Long noteId, NoteService noteRepository,
                           SchedulerProvider schedulers, Logger logger,
                           NoteFlowCoordinator noteFlowCoordinator) {
        logger.d(LOG_TAG, "Creating EditPresenter for note " + noteId);
        this.view = view;
        this.logger = logger;
        this.noteRepository = noteRepository;
        this.schedulers = schedulers;
        this.noteFlowCoordinator = noteFlowCoordinator;
        view.registerListener(this);
        loadNote(noteId);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // methods

    private void loadNote(Long noteId) {
        logger.d(LOG_TAG, "loadNote: " + noteId);
        disposables.add(noteRepository.getNote(noteId)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .subscribe(
                        note -> {
                            this.renderNote(note);
                            this.note = note;
                        },
                        err -> {
                            logger.e(LOG_TAG, "Error fetching note: ", err);
                        }
                ));
    }

    private void renderNote(Note note) {
        view.setTitle(note.getTitle());
        view.setContent(note.getContent());
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // MvxPresenter impl

    @Override
    public void cleanup() {
        view.unregisterListener(this);
        disposables.dispose();
        view = null;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // DetailContract.ViewListener impl

    @Override
    public void onEditClicked() {
        noteFlowCoordinator.editNote(note.getId());
    }
}
