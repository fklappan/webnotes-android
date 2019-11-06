package de.fklappan.app.webnotes.ui.add;

import de.fklappan.app.webnotes.common.logging.Logger;
import de.fklappan.app.webnotes.common.StringUtil;
import de.fklappan.app.webnotes.common.mvx.BaseObservableMvxPresenter;
import de.fklappan.app.webnotes.common.rx.SchedulerProvider;
import de.fklappan.app.webnotes.ui.edit.EditContract;
import de.fklappan.app.webnotes.model.Note;
import de.fklappan.app.webnotes.service.NoteRepository;
import io.reactivex.disposables.CompositeDisposable;

public class AddPresenter extends BaseObservableMvxPresenter<EditContract.PresenterListener> implements AddContract.Presenter {

    private static final String LOG_TAG = AddPresenter.class.getSimpleName();
    private EditContract.View view;
    private NoteRepository noteRepository;
    private Logger logger;
    private CompositeDisposable disposables = new CompositeDisposable();
    private SchedulerProvider schedulers;

    public AddPresenter(EditContract.View view, NoteRepository noteRepository, SchedulerProvider schedulers, Logger logger) {
        this.view = view;
        this.noteRepository = noteRepository;
        this.schedulers = schedulers;
        this.logger = logger;
    }

    private void saveNote(Note note) {
        disposables.add(noteRepository.saveNote(note.getTitle(), note.getContent())
        .subscribeOn(schedulers.io())
        .observeOn(schedulers.ui())
        .subscribe(
                success -> {
                    logger.d(LOG_TAG, "success: " + success);
                    for(EditContract.PresenterListener listener : listeners) {
                        listener.onSaved();
                    }
                },
                err -> {
                    logger.e(LOG_TAG, "error saving note", err);
                }));
    }

    @Override
    public void cleanup() {
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

}
