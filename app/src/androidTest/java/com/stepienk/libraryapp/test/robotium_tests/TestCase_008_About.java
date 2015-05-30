package com.stepienk.libraryapp.test.robotium_tests;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;

import com.robotium.solo.Solo;
import com.stepienk.libraryapp.view.main.MainActivity;

import info.androidhive.LibraryApp.R;

/**
 * Created by Krzysiek on 2015-04-19.
 */
public class TestCase_008_About extends ActivityInstrumentationTestCase2<MainActivity> {
    private String scenarioName = "Show About";
    private Solo solo;

    public TestCase_008_About() {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
    }

    public void testOpenAbout() {
        solo.waitForText("News");
        solo.sleep(1000);

        dragToHamburger();
        solo.clickOnText("About");
        assertTrue(solo.waitForText("About"));
        View btnLogout = solo.getView(R.id.btnLogout);
        solo.clickOnView(btnLogout);
        assertTrue(solo.waitForText("Not a member?"));
    }

    public String getScenarioName() {
        return scenarioName;
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities(); // finishes all opened activities
    }

    public void dragToHamburger() {
        solo.sleep(6000);
        int screenWidth = getActivity().getWindowManager().getDefaultDisplay().getWidth();
        int screenHeight = getActivity().getWindowManager().getDefaultDisplay().getHeight();
        int fromX, toX, fromY, toY = 0;

        fromX = 0;
        toX = screenWidth / 2;
        fromY = (screenHeight / 2);
        toY = (screenHeight / 2);

        // Drag from left to right
        solo.drag(fromX, toX, fromY, toY, 10);
    }
}