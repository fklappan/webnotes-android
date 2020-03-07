package de.fklappan.app.webnotes.ui.edit;

import android.content.Context;
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
import de.fklappan.app.webnotes.common.mvx.SnackbarProvider;
import de.fklappan.app.webnotes.common.navigation.NoteFlowCoordinator;
import de.fklappan.app.webnotes.common.rx.SchedulerProvider;
import de.fklappan.app.webnotes.service.NoteService;

public class EditFragment extends BaseFragment {

    private static String EXTRA_NOTE_ID = "EXTRA_NOTE_ID";
    private static final String LOG_TAG = EditFragment.class.getSimpleName();

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // member variables

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

    // creation

    public static Bundle getBundle(long noteId) {
        Bundle bundle = new Bundle();
        bundle.putLong(EXTRA_NOTE_ID, noteId);
        return bundle;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // android overrides

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
        Log.d(LOG_TAG, "onCreateView");
        editView = new EditView(inflater, null);

        editPresenter = new EditPresenter(editView, getArguments().getLong(EXTRA_NOTE_ID), noteRepository, schedulers, logger, snackbarProvider, noteFlowCoordinator);
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
}
