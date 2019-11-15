package de.fklappan.app.webnotes.ui.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.leinardi.android.speeddial.SpeedDialView;

import de.fklappan.app.webnotes.R;
import de.fklappan.app.webnotes.common.mvx.BaseObservableMvxView;

public class DetailView extends BaseObservableMvxView<DetailContract.ViewListener> implements DetailContract.View {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // member variables

    private TextView textViewTitle;
    private TextView textViewContent;
    private SpeedDialView fabView;

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // construction

    public DetailView(LayoutInflater inflater, ViewGroup viewGroup) {
        rootView = inflater.inflate(R.layout.detailview, viewGroup);
        initView();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // member methods

    private void initView() {
        textViewTitle = rootView.findViewById(R.id.textViewTitle);
        textViewContent = rootView.findViewById(R.id.textViewContent);
        fabView = rootView.findViewById(R.id.fab);
        fabView.setOnChangeListener(new SpeedDialView.OnChangeListener() {
            @Override
            public boolean onMainActionSelected() {
                for (DetailContract.ViewListener listener : listeners) {
                    listener.onEditClicked();
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
    // DetailContract.View impl

    @Override
    public void setTitle(String title) {
        textViewTitle.setText(title);
    }

    @Override
    public void setContent(String content) {
        textViewContent.setText(content);
    }
}
