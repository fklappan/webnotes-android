package de.fklappan.app.webnotes.common.mvx;

import android.view.View;

public abstract class BaseMvxView implements MvxView {

    protected View rootView;

    @Override
    public View getRootView() {
        return rootView;
    }
}
