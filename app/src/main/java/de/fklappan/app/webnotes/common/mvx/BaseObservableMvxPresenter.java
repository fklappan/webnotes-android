package de.fklappan.app.webnotes.common.mvx;

import java.util.HashSet;
import java.util.Set;

public abstract class BaseObservableMvxPresenter<ListenerType>
        implements ObservableMvxPresenter<ListenerType> {

    protected Set<ListenerType> listeners = new HashSet<>();

    @Override
    public void registerListener(ListenerType listener) {
        listeners.add(listener);
    }

    @Override
    public void unregisterListener(ListenerType listener) {
        listeners.remove(listener);
    }

}
