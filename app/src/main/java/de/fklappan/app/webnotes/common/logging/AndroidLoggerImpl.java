package de.fklappan.app.webnotes.common.logging;

import android.util.Log;

import javax.inject.Inject;

public class AndroidLoggerImpl implements Logger {

    @Inject
    public AndroidLoggerImpl() {

    }

    @Override
    public void d(String logTag, String logMessage) {
        Log.d(logTag, logMessage);
    }

    @Override
    public void d(String logTag, String logMessage, Throwable throwable) {
        Log.d(logTag, logMessage, throwable);
    }

    @Override
    public void w(String logTag, String logMessage) {
        Log.w(logTag, logMessage);
    }

    @Override
    public void w(String logTag, String logMessage, Throwable throwable) {
        Log.w(logTag, logMessage, throwable);
    }

    @Override
    public void e(String logTag, String logMessage) {
        Log.e(logTag, logMessage);
    }

    @Override
    public void e(String logTag, String logMessage, Throwable throwable) {
        Log.e(logTag, logMessage, throwable);
    }
}
