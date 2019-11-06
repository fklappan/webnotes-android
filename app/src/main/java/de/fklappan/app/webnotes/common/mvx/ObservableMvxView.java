package de.fklappan.app.webnotes.common.mvx;

import android.os.Bundle;
import android.view.View;

public interface ObservableMvxView<ListenerType> extends MvxView {

    void registerListener(ListenerType listener);

    void unregisterListener(ListenerType listener);
}
