package de.fklappan.app.webnotes.ui.overview;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import de.fklappan.app.webnotes.R;
import de.fklappan.app.webnotes.model.Note;

public class NoteOverviewAdapter extends RecyclerView.Adapter<NoteOverviewAdapter.ViewHolder> {

    public interface NoteClickListener {
        void onNoteClicked(Note note);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // member variables

    private NoteClickListener clickListener = (__) -> {
        // to nothing, avoid NPE
    };
    private List<Note> noteList = Collections.emptyList();

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // android overrides

    @NonNull
    @Override
    public NoteOverviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.overview_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NoteOverviewAdapter.ViewHolder holder, int position) {
        Note note = noteList.get(position);
        holder.bind(note);
        holder.getView().setOnClickListener(__ -> {
            clickListener.onNoteClicked(note);
        });
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // member methods

    public void setNoteList(List<Note> noteList) {
        this.noteList = noteList;
        notifyDataSetChanged();
    }

    public void setClickListener(NoteClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewCaption;
        TextView textViewContent;
        View wholeView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            wholeView = itemView;
            textViewCaption = itemView.findViewById(R.id.textViewCaption);
            textViewContent = itemView.findViewById(R.id.textViewContent);
        }

        View getView() {
            return wholeView;
        }

        void bind(Note note) {
            textViewCaption.setText(note.getTitle());
            textViewContent.setText(note.getContent());
        }
    }


}
