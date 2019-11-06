package de.fklappan.app.webnotes.ui.overview;

import de.fklappan.app.webnotes.common.logging.Logger;
import de.fklappan.app.webnotes.common.rx.SchedulerProvider;
import de.fklappan.app.webnotes.service.NoteRepository;
import io.reactivex.disposables.CompositeDisposable;

public class OverviewPresenter implements OverviewContract.Presenter {

    private static final String LOG_TAG = OverviewPresenter.class.getSimpleName();

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // member variables

    private OverviewContract.View view;
    private CompositeDisposable disposables = new CompositeDisposable();
    private NoteRepository noteRepository;
    private Logger logger;
    private SchedulerProvider schedulers;

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // construction

    public OverviewPresenter(OverviewContract.View view, NoteRepository noteRepository, SchedulerProvider schedulers, Logger logger) {
        this.view = view;
        this.noteRepository = noteRepository;
        this.logger = logger;
        this.schedulers = schedulers;
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
        disposables.dispose();
        view = null;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // OverviewContract impl

    @Override
    public void onLoadRequest() {
        loadNotes();
    }

    @Override
    public void setView(OverviewContract.View view) {
        this.view = view;
    }
}
