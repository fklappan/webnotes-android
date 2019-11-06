package de.fklappan.app.webnotes.ui.edit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.leinardi.android.speeddial.SpeedDialView;

import de.fklappan.app.webnotes.R;
import de.fklappan.app.webnotes.common.mvx.BaseObservableMvxView;
import de.fklappan.app.webnotes.model.Note;

public class EditView extends BaseObservableMvxView<EditContract.ViewListener> implements EditContract.View {

    private View rootView;
    private EditText editTextTitle;
    private EditText editTextContent;
    private SpeedDialView fabView;

    public EditView(LayoutInflater inflater, ViewGroup viewGroup) {
        rootView = inflater.inflate(R.layout.editview, viewGroup);
        initView();
    }

    private void initView() {
        editTextTitle = rootView.findViewById(R.id.editTextTitle);
        editTextContent = rootView.findViewById(R.id.editTextContent);
        fabView = rootView.findViewById(R.id.fab);
        fabView.setOnChangeListener(new SpeedDialView.OnChangeListener() {
            @Override
            public boolean onMainActionSelected() {
                for (EditContract.ViewListener listener : listeners) {
                    listener.onSaveClicked();
                }
                return false;
            }

            @Override
            public void onToggleChanged(boolean isOpen) {

            }
        });
    }


    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public Bundle getViewState() {
        return null;
    }

    @Override
    public void cleanup() {

    }

    @Override
    public void onData(Note note) {
        editTextTitle.setText(note.getTitle());
        editTextContent.setText(note.getContent());
    }

    @Override
    public String getTitle() {
        return editTextTitle.getText().toString();
    }

    @Override
    public String getContent() {
        return editTextContent.getText().toString();
    }
}
