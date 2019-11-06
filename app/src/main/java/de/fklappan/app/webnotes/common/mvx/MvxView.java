package de.fklappan.app.webnotes.common.mvx;

import android.os.Bundle;
import android.view.View;

public interface MvxView {

    View getRootView();

    Bundle getViewState();

    void cleanup();
}
