package de.fklappan.app.webnotes.common.navigation;

public class NoteFlowCoordinator {

    private Navigator navigator;

    public NoteFlowCoordinator(Navigator navigator) {
        this.navigator = navigator;
    }

    public void showOverview() {
        navigator.showOverview();
    }

    public void showNote(long noteId) {
        navigator.showNote(noteId);
    }

    public void editNote(long noteId) {
        navigator.editNote(noteId);
    }

    public void addNote() {
        navigator.addNote();
    }
}
