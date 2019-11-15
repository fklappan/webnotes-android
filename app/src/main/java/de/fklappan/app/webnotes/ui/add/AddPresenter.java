package de.fklappan.app.webnotes.ui.add;

import de.fklappan.app.webnotes.R;
import de.fklappan.app.webnotes.common.StringUtil;
import de.fklappan.app.webnotes.common.logging.Logger;
import de.fklappan.app.webnotes.common.mvx.SnackbarProvider;
import de.fklappan.app.webnotes.common.navigation.NoteFlowCoordinator;
import de.fklappan.app.webnotes.common.rx.SchedulerProvider;
import de.fklappan.app.webnotes.model.Note;
import de.fklappan.app.webnotes.service.NoteRepository;
import de.fklappan.app.webnotes.ui.edit.EditContract;
import io.reactivex.disposables.CompositeDisposable;

public class AddPresenter implements AddContract.Presenter, EditContract.ViewListener {

    private static final String LOG_TAG = AddPresenter.class.getSimpleName();
    private EditContract.View view;
    private NoteRepository noteRepository;
    private Logger logger;
    private CompositeDisposable disposables = new CompositeDisposable();
    private SchedulerProvider schedulers;
    private SnackbarProvider snackbarProvider;
    private NoteFlowCoordinator noteFlowCoordinator;

    public AddPresenter(EditContract.View view, NoteRepository noteRepository,
                        SchedulerProvider schedulers, Logger logger, SnackbarProvider snackbarProvider,
                        NoteFlowCoordinator noteFlowCoordinator) {
        this.view = view;
        this.noteRepository = noteRepository;
        this.schedulers = schedulers;
        this.logger = logger;
        this.snackbarProvider = snackbarProvider;
        this.noteFlowCoordinator = noteFlowCoordinator;
        view.registerListener(this);
    }

    private void saveNote(Note note) {
        disposables.add(noteRepository.saveNote(note.getTitle(), note.getContent())
        .subscribeOn(schedulers.io())
        .observeOn(schedulers.ui())
        .subscribe(
                success -> {
                    logger.d(LOG_TAG, "success: " + success);
                    snackbarProvider.showSnackbar(R.string.saved);
                    noteFlowCoordinator.showOverview();
                },
                err -> {
                    logger.e(LOG_TAG, "error saving note", err);
                }));
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
        Note note = new Note();
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
        onSaveRequested();
    }

}
