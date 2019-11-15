package de.fklappan.app.webnotes.di;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import de.fklappan.app.webnotes.common.navigation.Navigator;
import de.fklappan.app.webnotes.common.navigation.NoteFlowCoordinator;
import de.fklappan.app.webnotes.service.NoteRepository;
import de.fklappan.app.webnotes.service.NoteService;

/**
 * injection configuration for controller scoped classes and properties
 */
@Module
public class ControllerModule {

    private final Context context;

    public ControllerModule(Context context) {
        this.context = context;
    }

    @Provides
    Context provideContext() {
        return context;
    }

    @Provides
    @ControllerScope
    NoteRepository provideNoteRepository(NoteService impl) {
        return impl;
    }

    @Provides
    @ControllerScope
    NoteFlowCoordinator provideNoteFlowCoordinator(Navigator navigator) {
        return new NoteFlowCoordinator(navigator);
    }
}
