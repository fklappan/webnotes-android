package de.fklappan.app.webnotes.di;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.fklappan.app.webnotes.common.logging.AndroidLoggerImpl;
import de.fklappan.app.webnotes.common.logging.Logger;
import de.fklappan.app.webnotes.common.rx.AppSchedulers;
import de.fklappan.app.webnotes.common.rx.SchedulerProvider;

/**
 * injection configuration for application scoped classes and properties
 */
@Module
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return application.getApplicationContext();
    }

    @Provides
    Logger provideLogger(AndroidLoggerImpl impl) {
        return impl;
    }

    @Provides
    SchedulerProvider provideSchedulers(AppSchedulers impl) {
        return impl;
    }

}
