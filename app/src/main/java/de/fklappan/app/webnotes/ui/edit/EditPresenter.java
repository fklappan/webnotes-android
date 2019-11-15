package de.fklappan.app.webnotes.ui.edit;

import de.fklappan.app.webnotes.R;
import de.fklappan.app.webnotes.common.StringUtil;
import de.fklappan.app.webnotes.common.logging.Logger;
import de.fklappan.app.webnotes.common.mvx.SnackbarProvider;
import de.fklappan.app.webnotes.common.navigation.NoteFlowCoordinator;
import de.fklappan.app.webnotes.common.rx.SchedulerProvider;
import de.fklappan.app.webnotes.model.Note;
import de.fklappan.app.webnotes.service.NoteService;
import io.reactivex.disposables.CompositeDisposable;

public class EditPresenter implements EditContract.Presenter, EditContract.ViewListener {

    private static final String LOG_TAG = EditPresenter.class.getSimpleName();
    private EditContract.View view;
    private Note note;
    private CompositeDisposable disposables = new CompositeDisposable();
    private NoteService noteRepository;
    private SchedulerProvider schedulers;
    private Logger logger;
    private SnackbarProvider snackbarProvider;
    private NoteFlowCoordinator noteFlowCoordinator;

    public EditPresenter(EditContract.View view, Long noteId, NoteService noteRepository,
                         SchedulerProvider schedulers, Logger logger,
                         SnackbarProvider snackbarProvider, NoteFlowCoordinator noteFlowCoordinator) {
        logger.d(LOG_TAG, "Creating EditPresenter for note " + noteId);
        this.view = view;
        this.logger = logger;
        this.noteRepository = noteRepository;
        this.schedulers = schedulers;
        this.noteFlowCoordinator = noteFlowCoordinator;
        this.snackbarProvider = snackbarProvider;
        view.registerListener(this);
        loadNote(noteId);
    }

    private void loadNote(Long noteId) {
        logger.d(LOG_TAG, "loadNote: " + noteId);
        disposables.add(noteRepository.getNote(noteId)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .subscribe(
                        note -> {
                            logger.d(LOG_TAG, "note loaded: " + note.getTitle());
                            this.note = note;
                            view.onData(note);
                        },
                        err -> {
                            logger.e(LOG_TAG, "error loading note", err);
                        }));
    }

    private void saveNote(Note note) {
        logger.d(LOG_TAG, "saveNote: " + note.getTitle());
        disposables.add(noteRepository.saveOrUpdateNote(note)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .subscribe(
                        success -> {
                            logger.d(LOG_TAG, "note updated: " + success);

                            noteFlowCoordinator.showOverview();
                            snackbarProvider.showSnackbar(R.string.saved);
                        },
                        err -> {
                            logger.e(LOG_TAG, "error saving note", err);
                        }
                ));
    }

    @Override
    public void cleanup() {
        view.unregisterListener(this);
        disposables.dispose();
        view = null;
    }

    @Override
    public void onSaveRequested() {
        logger.d(LOG_TAG, "onSaveRequested");
        if (!checkInput()) {
            logger.w(LOG_TAG, "invalid input");
            return;
        }

        if (note == null) {
            note = new Note();
        }

        note.setTitle(view.getTitle());
        note.setContent(view.getContent());
        saveNote(note);
    }

    private boolean checkInput() {
        if (StringUtil.nullOrEmpty(view.getTitle())) {
            logger.e(LOG_TAG, "no title");
            return false;
        }

        if (StringUtil.nullOrEmpty(view.getContent())) {
            logger.e(LOG_TAG, "no content");
            return false;
        }
        return true;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // EditContract.ViewListener impl

    @Override
    public void onSaveClicked() {
        logger.d(LOG_TAG, "onSaveClicked");
        onSaveRequested();
    }
}
