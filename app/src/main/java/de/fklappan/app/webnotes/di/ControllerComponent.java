package de.fklappan.app.webnotes.di;

import dagger.Subcomponent;
import de.fklappan.app.webnotes.ui.add.AddFragment;
import de.fklappan.app.webnotes.ui.detail.DetailFragment;
import de.fklappan.app.webnotes.ui.edit.EditFragment;
import de.fklappan.app.webnotes.ui.overview.OverviewFragment;

@Subcomponent(modules = {ControllerModule.class})
public interface ControllerComponent {

    // add here all controller classes (activities, fragments) which should be able to be injected
    void inject(OverviewFragment fragment);

    void inject(EditFragment fragment);

    void inject(DetailFragment fragment);

    void inject(AddFragment fragment);
}
