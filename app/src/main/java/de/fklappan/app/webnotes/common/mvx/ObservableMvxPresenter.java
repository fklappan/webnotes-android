package de.fklappan.app.webnotes.common.mvx;

public interface ObservableMvxPresenter<ListenerType> extends MvxPresenter {

    void registerListener(ListenerType listener);

    void unregisterListener(ListenerType listener);
}
