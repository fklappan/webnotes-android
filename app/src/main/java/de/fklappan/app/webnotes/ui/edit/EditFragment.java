package de.fklappan.app.webnotes.ui.edit;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;

import javax.inject.Inject;

import de.fklappan.app.webnotes.R;
import de.fklappan.app.webnotes.common.BaseFragment;
import de.fklappan.app.webnotes.common.logging.Logger;
import de.fklappan.app.webnotes.common.mvx.SnackbarProvider;
import de.fklappan.app.webnotes.common.navigation.NoteFlowCoordinator;
import de.fklappan.app.webnotes.common.rx.SchedulerProvider;
import de.fklappan.app.webnotes.service.NoteService;

import static de.fklappan.app.webnotes.common.Parameters.EXTRA_NOTE_ID;

public class EditFragment extends BaseFragment implements EditContract.PresenterListener, EditContract.ViewListener {

    private static final String LOG_TAG = EditFragment.class.getSimpleName();
    private EditContract.View editView;
    private EditContract.Presenter editPresenter;
    private SnackbarProvider snackbarProvider;

    @Inject
    NoteService noteRepository;
    @Inject
    Logger logger;
    @Inject
    SchedulerProvider schedulers;
    @Inject
    NoteFlowCoordinator noteFlowCoordinator;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof SnackbarProvider) {
            snackbarProvider = (SnackbarProvider) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getInjector().inject(this);
        editView = new EditView(inflater, null);
        editView.registerListener(this);

        editPresenter = new EditPresenter(editView, getArguments().getLong(EXTRA_NOTE_ID), noteRepository, schedulers, logger);
        editPresenter.registerListener(this);

        return editView.getRootView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        editPresenter.cleanup();
        editView.cleanup();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        snackbarProvider = null;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // EditContract.PresenterListener impl

    @Override
    public void onSaved() {
        Log.d(LOG_TAG, "onSaved");
        snackbarProvider.showSnackbar(R.string.saved);
        noteFlowCoordinator.showOverview();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // EditContract.ViewListener impl

    @Override
    public void onSaveClicked() {
        Log.d(LOG_TAG, "onSaveClicked");
        editPresenter.onSaveRequested();
    }
}
