package de.fklappan.app.webnotes.common.navigation;

import androidx.navigation.NavController;

import de.fklappan.app.webnotes.R;
import de.fklappan.app.webnotes.ui.detail.DetailFragment;
import de.fklappan.app.webnotes.ui.edit.EditFragment;

public class Navigator {

    private NavController navController;

    public Navigator() {
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
        navController.navigate(R.id.detailFragment, DetailFragment.getBundle(noteId));
    }

    public void editNote(long noteId) {
        navController.navigate(R.id.editFragment, EditFragment.getBundle(noteId));
    }

    public void addNote() {
        navController.navigate(R.id.addFragment);
    }
}
