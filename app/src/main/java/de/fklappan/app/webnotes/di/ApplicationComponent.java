package de.fklappan.app.webnotes.di;

import dagger.Component;

@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    ControllerComponent newPresenterComponent(ControllerModule module);
}
