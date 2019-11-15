package de.fklappan.app.webnotes.ui.add;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.leinardi.android.speeddial.SpeedDialView;

import de.fklappan.app.webnotes.R;
import de.fklappan.app.webnotes.common.mvx.BaseObservableMvxView;
import de.fklappan.app.webnotes.ui.edit.EditContract;
import de.fklappan.app.webnotes.model.Note;

public class AddView extends BaseObservableMvxView<AddContract.ViewListener> implements AddContract.View {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // member variables

    private View rootView;
    private EditText editTextTitle;
    private EditText editTextContent;
    private SpeedDialView fabView;

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // construction

    public AddView(LayoutInflater inflater, ViewGroup viewGroup) {
        rootView = inflater.inflate(R.layout.editview, viewGroup);
        initView();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // member methods

    private void initView() {
        editTextTitle = rootView.findViewById(R.id.editTextTitle);
        editTextContent = rootView.findViewById(R.id.editTextContent);
        fabView = rootView.findViewById(R.id.fab);
        fabView.setOnChangeListener(new SpeedDialView.OnChangeListener() {
            @Override
            public boolean onMainActionSelected() {
                for (AddContract.ViewListener listener : listeners) {
                    listener.onSaveClicked();
                }
                return false;
            }

            @Override
            public void onToggleChanged(boolean isOpen) {

            }
        });
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

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // EditContract.View impl

    @Override
    public String getTitle() {
        return editTextTitle.getText().toString();
    }

    @Override
    public String getContent() {
        return editTextContent.getText().toString();
    }
}
