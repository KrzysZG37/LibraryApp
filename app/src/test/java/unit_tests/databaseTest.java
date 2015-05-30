package unit_tests;

import android.app.ProgressDialog;
import android.content.Context;

import com.stepienk.libraryapp.view.login.LoginActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

/**
 * Created by Krzysiek on 2015-05-04.
 */
public class databaseTest {
    LoginActivity loginActivity;
    private ProgressDialog pDialog;

    @Before
    public void setUp() {
        Context context = mock(Context.class);
        loginActivity = new LoginActivity();
        pDialog = new ProgressDialog(context);
        loginActivity.setProgressDialog(pDialog);
    }

    @Test
    public void shouldReturnEBooks() {
        loginActivity.checkLogin("aaa", "aaa");
    }


}
