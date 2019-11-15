package de.fklappan.app.webnotes.di;

import javax.inject.Singleton;

import dagger.Component;
import de.fklappan.app.webnotes.MainActivity;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    ControllerComponent newPresenterComponent(ControllerModule module);

    void inject(MainActivity activity);
}
