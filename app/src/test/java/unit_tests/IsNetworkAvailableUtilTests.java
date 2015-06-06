package unit_tests;

import android.app.Activity;

import com.stepienk.libraryapp.utils.NetworkAvailable;
import com.stepienk.libraryapp.view.main.MainActivity;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;


/**
 * Created by Krzysiek on 2015-04-21.
 */
public class IsNetworkAvailableUtilTests {
    private NetworkAvailable validateNetwork;
    private Activity activity;

    @Before
    public void setUp() {
        validateNetwork = new NetworkAvailable();
        activity = mock(MainActivity.class);
    }

    @Test
    public void shouldReturnNetworkIsNotAvailable() {
        Assert.assertFalse("Should be offline", validateNetwork.isNetworkAvailable(activity));
    }

}
