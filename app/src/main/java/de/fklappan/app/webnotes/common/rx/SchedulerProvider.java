package de.fklappan.app.webnotes.common.rx;

import io.reactivex.Scheduler;

public interface SchedulerProvider {

    Scheduler io();

    Scheduler compute();

    Scheduler ui();
}
