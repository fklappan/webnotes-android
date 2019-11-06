package de.fklappan.app.webnotes.di;

import android.content.Context;


import dagger.Module;
import dagger.Provides;
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
    NoteRepository provideNoteRepository(NoteService impl) {
        return impl;
    }
}
