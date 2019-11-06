package de.fklappan.app.webnotes.common;

import de.fklappan.app.webnotes.common.logging.Logger;

public class TestLogger implements Logger {
    @Override
    public void d(String logTag, String logMessage) {
        System.out.println(logTag + logMessage);
    }

    @Override
    public void d(String logTag, String logMessage, Throwable throwable) {
        System.out.println(logTag + logMessage);

    }

    @Override
    public void w(String logTag, String logMessage) {
        System.out.println(logTag + logMessage);

    }

    @Override
    public void w(String logTag, String logMessage, Throwable throwable) {
        System.out.println(logTag + logMessage);

    }

    @Override
    public void e(String logTag, String logMessage) {
        System.out.println(logTag + logMessage);

    }

    @Override
    public void e(String logTag, String logMessage, Throwable throwable) {
        System.out.println(logTag + logMessage);

    }
}
