package de.fklappan.app.webnotes.ui.add;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import javax.inject.Inject;

import de.fklappan.app.webnotes.R;
import de.fklappan.app.webnotes.common.BaseFragment;
import de.fklappan.app.webnotes.common.Injector;
import de.fklappan.app.webnotes.common.logging.Logger;
import de.fklappan.app.webnotes.common.mvx.SnackbarProvider;
import de.fklappan.app.webnotes.common.rx.SchedulerProvider;
import de.fklappan.app.webnotes.service.NoteService;
import de.fklappan.app.webnotes.ui.edit.EditContract;
import de.fklappan.app.webnotes.ui.edit.EditView;

public class AddFragment extends BaseFragment implements EditContract.ViewListener, EditContract.PresenterListener {

    private static final String LOG_TAG = AddFragment.class.getSimpleName();

    private EditContract.View view;
    private AddContract.Presenter presenter;
    private SnackbarProvider snackbarProvider;
    @Inject
    NoteService noteRepository;
    @Inject
    Logger logger;
    @Inject
    SchedulerProvider schedulers;


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
        super.onCreateView(inflater, container, savedInstanceState);
        view = new EditView(inflater, null);
        view.registerListener(this);

        presenter = new AddPresenter(view, noteRepository, schedulers, logger);
        presenter.registerListener(this);

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

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // EditContract.PresenterListener impl

    @Override
    public void onSaved() {
        Log.d(LOG_TAG, "onSaved");
        snackbarProvider.showSnackbar(R.string.saved);
        Navigation.findNavController(getView()).navigateUp();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // EditContract.ViewListener impl

    @Override
    public void onSaveClicked() {
        presenter.onSaveRequested();
    }
}
