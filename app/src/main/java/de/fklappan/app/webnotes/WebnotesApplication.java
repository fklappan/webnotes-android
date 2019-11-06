package de.fklappan.app.webnotes;

import android.app.Application;

import de.fklappan.app.webnotes.di.ApplicationComponent;
import de.fklappan.app.webnotes.di.ApplicationModule;
import de.fklappan.app.webnotes.di.DaggerApplicationComponent;

public class WebnotesApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

}
