package de.fklappan.app.webnotes.ui.detail;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import javax.inject.Inject;

import de.fklappan.app.webnotes.common.BaseFragment;
import de.fklappan.app.webnotes.common.logging.Logger;
import de.fklappan.app.webnotes.common.navigation.NoteFlowCoordinator;
import de.fklappan.app.webnotes.common.rx.SchedulerProvider;
import de.fklappan.app.webnotes.service.NoteService;

import static de.fklappan.app.webnotes.common.Parameters.EXTRA_NOTE_ID;

public class DetailFragment extends BaseFragment {

    private static final String LOG_TAG = DetailFragment.class.getSimpleName();

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // member variables

    private DetailContract.View detailView;
    private DetailContract.Presenter detailPresenter;
    @Inject
    Logger logger;
    @Inject
    NoteService noteService;
    @Inject SchedulerProvider appSchedulers;
    @Inject NoteFlowCoordinator noteFlowCoordinator;

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // android overrides

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getInjector().inject(this);
        Log.d(LOG_TAG, "onCreateView");
        super.onCreateView(inflater, container, savedInstanceState);

        detailView = new DetailView(inflater, null);
        detailPresenter = new DetailPresenter(detailView, getArguments().getLong(EXTRA_NOTE_ID), noteService, appSchedulers, logger, noteFlowCoordinator);

        return detailView.getRootView();
    }

    @Override
    public void onDestroyView() {
        Log.d(LOG_TAG, "onDestroyView");

        super.onDestroyView();
        detailPresenter.cleanup();
    }
}
