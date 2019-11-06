package de.fklappan.app.webnotes.ui.overview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.leinardi.android.speeddial.SpeedDialView;

import java.util.List;

import de.fklappan.app.webnotes.R;
import de.fklappan.app.webnotes.common.mvx.BaseObservableMvxView;
import de.fklappan.app.webnotes.model.Note;

public class OverviewView extends BaseObservableMvxView<OverviewContract.Listener> implements OverviewContract.View {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // member variables

    private View rootView;
    private TextView textView;
    private RecyclerView recyclerView;
    private SpeedDialView fabView;

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // construction

    public OverviewView(LayoutInflater inflater, ViewGroup viewGroup) {
        rootView = inflater.inflate(R.layout.overview, viewGroup);

        initView();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // member methods

    private void initView() {
        textView = rootView.findViewById(R.id.textViewMain);

        initSwipeToRefresh();
        initRecyclerView();
        initFab();
    }

    private void initFab() {
        fabView = rootView.findViewById(R.id.fab);
        fabView.setOnChangeListener(new SpeedDialView.OnChangeListener() {
            @Override
            public boolean onMainActionSelected() {
                for (OverviewContract.Listener listener : listeners) {
                    listener.onAddClicked();
                }
                return false;
            }

            @Override
            public void onToggleChanged(boolean isOpen) {

            }
        });
    }

    private void initRecyclerView() {
        recyclerView = rootView.findViewById(R.id.recyclerViewNote);
        NoteOverviewAdapter adapter = new NoteOverviewAdapter();
        adapter.setClickListener(this::noteClicked);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(rootView.getContext(),
                DividerItemDecoration.VERTICAL));
    }

    private void initSwipeToRefresh() {
        SwipeRefreshLayout swipeRefreshLayout = rootView.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            for (OverviewContract.Listener listener : listeners) {
                listener.onRefresh();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void noteClicked(Note note) {
        for (OverviewContract.Listener listener : listeners) {
            listener.onNoteClicked(note);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // base class overrides

    @Override
    public View getRootView() {
        return rootView;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // MvxView impl

    @Override
    public Bundle getViewState() {
        return null;
    }

    @Override
    public void cleanup() {
    }

    @Override
    public void onLoading() {
        recyclerView.setVisibility(View.INVISIBLE);
        textView.setVisibility(View.VISIBLE);
        textView.setText(R.string.loading);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // OverviewContract impl

    @Override
    public void onData(List<Note> noteList) {
        textView.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
        ((NoteOverviewAdapter) recyclerView.getAdapter()).setNoteList(noteList);
    }

    @Override
    public void onError(Throwable error) {
        recyclerView.setVisibility(View.INVISIBLE);
        textView.setVisibility(View.VISIBLE);
        textView.setText(error.getMessage());
    }
}
