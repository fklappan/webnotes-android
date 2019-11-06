package de.fklappan.app.webnotes.ui.overview;

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
import de.fklappan.app.webnotes.common.rx.SchedulerProvider;
import de.fklappan.app.webnotes.model.Note;
import de.fklappan.app.webnotes.service.NoteService;

import static de.fklappan.app.webnotes.common.Parameters.EXTRA_NOTE_ID;

public class OverviewFragment extends BaseFragment implements OverviewContract.Listener {

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

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // android overrides

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getInjector().inject(this);

        Log.d(LOG_TAG, "onCreateView");
        super.onCreateView(inflater, container, savedInstanceState);

        overviewView = new OverviewView(getLayoutInflater(), null);
        overviewView.registerListener(this);

        presenter = new OverviewPresenter(overviewView, noteRepository, schedulers, logger);
        presenter.setView(overviewView);

        return overviewView.getRootView();
    }

    @Override
    public void onDestroyView() {
        Log.d(LOG_TAG, "onDestroyView");
        super.onDestroyView();
        presenter.cleanup();
        overviewView.unregisterListener(this);
        overviewView.cleanup();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // OverviewContract.Listener impl

    @Override
    public void onNoteClicked(Note note) {
        Log.d(LOG_TAG, "onNoteClicked: " + note.getTitle());
        Bundle bundle = new Bundle();
        bundle.putLong(EXTRA_NOTE_ID, note.getId());
        Navigation.findNavController(getView()).navigate(R.id.action_overviewFragment_to_detailFragment, bundle);
    }

    @Override
    public void onAddClicked() {
        Log.d(LOG_TAG, "onAddClicked");
        Navigation.findNavController(getView()).navigate(R.id.action_overviewFragment_to_addFragment);
    }

    @Override
    public void onRefresh() {
        Log.d(LOG_TAG, "onRefresh");
        presenter.onLoadRequest();
    }
}
