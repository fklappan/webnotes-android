package de.fklappan.app.webnotes;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import de.fklappan.app.webnotes.common.mvx.SnackbarProvider;
import de.fklappan.app.webnotes.common.navigation.Navigator;

/**
 * Startup activity which contains the navigation host
 */
public class MainActivity extends AppCompatActivity implements SnackbarProvider {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    @Inject Navigator navigator;
    private View mainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(LOG_TAG, "onCreate");
        super.onCreate(savedInstanceState);
        ((WebnotesApplication)getApplication()).getApplicationComponent().inject(this);
        setContentView(R.layout.activity_main);
        mainView = findViewById(R.id.mainLayout);
        navigator.setNavController(Navigation.findNavController(findViewById(R.id.navHostFragment)));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        navigator.cleanup();
    }

    @Override
    public void showSnackbar(@NotNull String text) {
        Snackbar snackbar = Snackbar.make(mainView, text, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    @Override
    public void showSnackbar(int resource) {
        showSnackbar(getString(resource));
    }
}
