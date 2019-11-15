package de.fklappan.app.webnotes.common.mvx;

/**
 * Provides an interface to show a snackbar. Is implemented by the main activity
 */
public interface SnackbarProvider {

    void showSnackbar(String text);

    void showSnackbar(int resource);
}
