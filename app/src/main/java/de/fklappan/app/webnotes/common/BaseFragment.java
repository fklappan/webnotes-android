package de.fklappan.app.webnotes.common;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import de.fklappan.app.webnotes.WebnotesApplication;
import de.fklappan.app.webnotes.di.ControllerComponent;
import de.fklappan.app.webnotes.di.ControllerModule;

public class BaseFragment extends Fragment {

    private ControllerComponent controllerComponent;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if(context.getApplicationContext() instanceof WebnotesApplication) {
            controllerComponent = ((WebnotesApplication) context.getApplicationContext()).getApplicationComponent()
                    .newPresenterComponent(new ControllerModule(context));
        } else {
            throw new IllegalStateException("Expected a instance of WebnotesApplication");
        }
    }

    protected ControllerComponent getInjector() {
        return controllerComponent;
    }

}
