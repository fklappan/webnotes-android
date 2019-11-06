package de.fklappan.app.webnotes.common;

import de.fklappan.app.webnotes.common.logging.AndroidLoggerImpl;
import de.fklappan.app.webnotes.common.logging.Logger;
import de.fklappan.app.webnotes.common.rx.AppSchedulers;
import de.fklappan.app.webnotes.common.rx.SchedulerProvider;
import de.fklappan.app.webnotes.service.NoteRepository;

public class Injector {

    public static NoteRepository getNoteRepository() {
        return new NoteTestService();
    }

    public static Logger getLogger() {
        return new AndroidLoggerImpl();
    }

    public static SchedulerProvider getSchedulers() {
        return new AppSchedulers();
    }

}
