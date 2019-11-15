package de.fklappan.app.webnotes.ui.overview;

import de.fklappan.app.webnotes.common.logging.Logger;
import de.fklappan.app.webnotes.common.navigation.NoteFlowCoordinator;
import de.fklappan.app.webnotes.common.rx.SchedulerProvider;
import de.fklappan.app.webnotes.model.Note;
import de.fklappan.app.webnotes.service.NoteRepository;
import io.reactivex.disposables.CompositeDisposable;

public class OverviewPresenter implements OverviewContract.Presenter, OverviewContract.Listener {

    private static final String LOG_TAG = OverviewPresenter.class.getSimpleName();

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // member variables

    private OverviewContract.View view;
    private CompositeDisposable disposables = new CompositeDisposable();
    private NoteRepository noteRepository;
    private Logger logger;
    private SchedulerProvider schedulers;
    private NoteFlowCoordinator noteFlowCoordinator;

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // construction

    public OverviewPresenter(OverviewContract.View view, NoteRepository noteRepository, SchedulerProvider schedulers, Logger logger, NoteFlowCoordinator noteFlowCoordinator) {
        this.view = view;
        this.noteRepository = noteRepository;
        this.logger = logger;
        this.schedulers = schedulers;
        this.noteFlowCoordinator = noteFlowCoordinator;

        view.registerListener(this);
        loadNotes();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // methods

    private void loadNotes() {
        view.onLoading();
        disposables.add(noteRepository.getAllNotes()
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .subscribe(noteList -> {
                            logger.d(LOG_TAG, "Notes loaded. Count: " + noteList.size());
                            view.onData(noteList);
                        },
                        error -> {
                            logger.e(LOG_TAG, "Error loading notes", error);
                            view.onError(error);
                        }));
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
    // OverviewContract.Listener impl

    @Override
    public void onNoteClicked(Note note) {
        logger.d(LOG_TAG, "onNoteClicked: " + note.getTitle());
        noteFlowCoordinator.showNote(note.getId());
    }

    @Override
    public void onAddClicked() {
        logger.d(LOG_TAG, "onAddClicked");
        noteFlowCoordinator.addNote();
    }

    @Override
    public void onRefresh() {
        logger.d(LOG_TAG, "onRefresh");
        loadNotes();
    }
}
