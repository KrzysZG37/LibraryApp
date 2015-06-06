package com.stepienk.libraryapp.test.robotium_tests;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.EditText;

import com.robotium.solo.Solo;
import com.stepienk.libraryapp.view.login.LoginActivity;

import info.androidhive.LibraryApp.R;

/**
 * Created by Krzysiek on 2015-04-19.
 */
public class TestCase_002_Login extends ActivityInstrumentationTestCase2<LoginActivity> {
    private String scenarioName = "Show Login screen";
    private Solo solo;

    public TestCase_002_Login() {
        super(LoginActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
    }

    public void testLogin() {
        if (solo.searchText("Welcome")) {
            View btnLogout = solo.getView(R.id.btnLogout);
            solo.clickOnView(btnLogout);
        }

        EditText usernameTextField = (EditText) solo.getView(R.id.email);
        EditText passwordTextField = (EditText) solo.getView(R.id.password);

        solo.enterText(usernameTextField, "aaa");
        solo.enterText(passwordTextField, "aaa");

        View btnLogin = solo.getView(R.id.btnLogin);
        solo.clickOnView(btnLogin);

        solo.sleep(1000);
        assertTrue(solo.waitForText("Welcome"));
        View btnNews = solo.getView(R.id.btnNews);
        solo.clickOnView(btnNews);
        assertTrue(solo.waitForText("News"));
    }

    public String getScenarioName() {
        return scenarioName;
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities(); // finishes all opened activities
    }

}