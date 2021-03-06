package io.paper.android.utils;

import android.support.annotation.NonNull;

import rx.Scheduler;

public interface SchedulerProvider {
    @NonNull
    Scheduler computation();

    @NonNull
    Scheduler io();

    @NonNull
    Scheduler ui();
}
