package de.fklappan.app.webnotes.ui.overview;

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

public class OverviewFragment extends BaseFragment {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // member variables

    private static final String LOG_TAG = OverviewFragment.class.getSimpleName();
    private OverviewContract.View overviewView;
    private OverviewContract.Presenter presenter;

    @Inject
    NoteService noteRepository;
    @Inject
    Logger logger;
    @Inject
    SchedulerProvider schedulers;
    @Inject
    NoteFlowCoordinator noteFlowCoordinator;

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // android overrides

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getInjector().inject(this);

        Log.d(LOG_TAG, "onCreateView");
        super.onCreateView(inflater, container, savedInstanceState);

        overviewView = new OverviewView(getLayoutInflater(), null);
        presenter = new OverviewPresenter(overviewView, noteRepository, schedulers, logger, noteFlowCoordinator);

        return overviewView.getRootView();
    }

    @Override
    public void onDestroyView() {
        Log.d(LOG_TAG, "onDestroyView");
        super.onDestroyView();
        presenter.cleanup();
        overviewView.cleanup();
    }
}
