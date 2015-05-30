package com.stepienk.libraryapp.test;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.EditText;

import com.robotium.solo.Solo;
import com.stepienk.libraryapp.view.login.LoginActivity;

import info.androidhive.LibraryApp.R;

/**
 * Created by Krzysiek on 2015-04-19.
 */
public class TestCase_001_Wrong_Login extends ActivityInstrumentationTestCase2<LoginActivity> {
    private String scenarioName = "Show Wrong Login screen";
    private Solo solo;

    public TestCase_001_Wrong_Login() {
        super(LoginActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
    }

    public void testWrongLogin() {
        if (solo.searchText("Welcome")) {
            View btnLogout = solo.getView(R.id.btnLogout);
            solo.clickOnView(btnLogout);
        }

        EditText usernameTextField = (EditText) solo.getView(R.id.email);
        EditText passwordTextField = (EditText) solo.getView(R.id.password);

        solo.enterText(usernameTextField, "aa");
        solo.enterText(passwordTextField, "aa");

        View btnLogin = solo.getView(R.id.btnLogin);
        solo.clickOnView(btnLogin);

        assertTrue(solo.waitForText("Incorrect"));
    }

    public void testRegister() {
        if (solo.searchText("Welcome")) {
            View btnLogout = solo.getView(R.id.btnLogout);
            solo.clickOnView(btnLogout);
        }

        View btnLinkToRegisterScreen = solo.getView(R.id.btnLinkToRegisterScreen);
        solo.clickOnView(btnLinkToRegisterScreen);

        EditText usernameTextField = (EditText) solo.getView(R.id.name);
        EditText userEmailTextField = (EditText) solo.getView(R.id.email);
        EditText userPasswordTextField = (EditText) solo.getView(R.id.password);

        solo.enterText(usernameTextField, "bbb");
        solo.enterText(userEmailTextField, "bbb");
        solo.enterText(userPasswordTextField, "bbb");

        assertTrue(solo.waitForText("Already"));

        View btnLinkToLoginScreen = solo.getView(R.id.btnLinkToLoginScreen);
        solo.clickOnView(btnLinkToLoginScreen);

        solo.sleep(1000);
        assertTrue(solo.waitForText("Not"));
    }

    public String getScenarioName() {
        return scenarioName;
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities(); // finishes all opened activities
    }

}