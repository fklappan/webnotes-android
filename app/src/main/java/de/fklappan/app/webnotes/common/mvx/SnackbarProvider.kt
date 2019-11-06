package de.fklappan.app.webnotes.common.mvx

/**
 * Provides an interface to show a snackbar. Is implemented by the main activity
 */
interface SnackbarProvider {

    fun showSnackbar(text: String)

    fun showSnackbar(resource: Int)
}