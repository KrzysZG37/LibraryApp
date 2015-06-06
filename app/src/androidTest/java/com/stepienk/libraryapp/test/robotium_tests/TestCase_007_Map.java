package com.stepienk.libraryapp.test.robotium_tests;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;
import com.stepienk.libraryapp.view.main.MainActivity;

import info.androidhive.LibraryApp.R;

/**
 * Created by stepienk on 2015-04-23.
 */
public class TestCase_007_Map extends ActivityInstrumentationTestCase2<MainActivity> {
    private String scenarioName = "Show Map";
    private Solo solo;

    public TestCase_007_Map() {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
    }

    public void testOpenMap() {
        solo.waitForText("News");
        solo.sleep(1000);

        dragToHamburger();
        solo.clickOnText("Map");
        assertTrue(solo.waitForText("Map"));
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