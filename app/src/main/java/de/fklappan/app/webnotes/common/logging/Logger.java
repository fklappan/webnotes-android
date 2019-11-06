package de.fklappan.app.webnotes.common.logging;

public interface Logger {

    void d(String logTag, String logMessage);
    void d(String logTag, String logMessage, Throwable throwable);
    void w(String logTag, String logMessage);
    void w(String logTag, String logMessage, Throwable throwable);
    void e(String logTag, String logMessage);
    void e(String logTag, String logMessage, Throwable throwable);
}
