package de.fklappan.app.webnotes.ui.add;

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
import de.fklappan.app.webnotes.ui.edit.EditContract;
import de.fklappan.app.webnotes.ui.edit.EditView;

public class AddFragment extends BaseFragment {

    private static final String LOG_TAG = AddFragment.class.getSimpleName();

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // member variables

    private AddContract.View view;
    private AddContract.Presenter presenter;
    private SnackbarProvider snackbarProvider;
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

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof SnackbarProvider) {
            snackbarProvider = (SnackbarProvider)context;
        }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getInjector().inject(this);
        Log.d(LOG_TAG, "onCreateView");
        super.onCreateView(inflater, container, savedInstanceState);

        view = new AddView(inflater, null);
        presenter = new AddPresenter(view, noteRepository, schedulers, logger, snackbarProvider, noteFlowCoordinator);

        return view.getRootView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.cleanup();
        view.cleanup();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        snackbarProvider = null;
    }
}
