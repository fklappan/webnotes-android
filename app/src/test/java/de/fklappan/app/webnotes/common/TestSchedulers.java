package de.fklappan.app.webnotes.common;

import de.fklappan.app.webnotes.common.rx.SchedulerProvider;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class TestSchedulers implements SchedulerProvider {
    @Override
    public Scheduler io() {
        return Schedulers.io();
    }

    @Override
    public Scheduler compute() {
        return Schedulers.io();
    }

    @Override
    public Scheduler ui() {
        return Schedulers.io();
    }
}
