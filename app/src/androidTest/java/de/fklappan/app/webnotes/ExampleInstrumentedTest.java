package de.fklappan.app.webnotes;

import android.content.Context;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.InstrumentationRegistry;
import androidx.test.espresso.Espresso;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import de.fklappan.app.webnotes.common.Injector;
import de.fklappan.app.webnotes.service.NoteRepository;
import de.fklappan.app.webnotes.ui.overview.OverviewFragment;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.

        NoteRepository noteRepository = Injector.getNoteRepository();

        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("de.fklappan.app.webnotes", appContext.getPackageName());
    }

    @Test
    public void testFragment() {
        FragmentScenario.launchInContainer(OverviewFragment.class);
    }
}
