package de.fklappan.app.webnotes.common.navigation;

import android.os.Bundle;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import de.fklappan.app.webnotes.R;

import static de.fklappan.app.webnotes.common.Parameters.EXTRA_NOTE_ID;

public class Navigator {

    private NavController navController;

    public Navigator() {
    }

    public Navigator(NavController navController) {
        setNavController(navController);
    }

    public void cleanup() {
        navController = null;
    }

    public void setNavController(NavController navController) {
        this.navController = navController;
    }

    public void showOverview() {
        navController.navigate(R.id.action_global_overview);
    }

    public void showNote(long noteId) {
        Bundle bundle = new Bundle();
        bundle.putLong(EXTRA_NOTE_ID, noteId);
        navController.navigate(R.id.detailFragment, bundle);
    }

    public void editNote(long noteId) {
        Bundle bundle = new Bundle();
        bundle.putLong(EXTRA_NOTE_ID, noteId);
        navController.navigate(R.id.editFragment, bundle);
    }

    public void addNote() {
        navController.navigate(R.id.addFragment);
    }
}
