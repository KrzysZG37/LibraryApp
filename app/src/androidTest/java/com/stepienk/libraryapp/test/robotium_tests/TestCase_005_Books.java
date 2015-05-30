package com.stepienk.libraryapp.test.robotium_tests;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;
import com.stepienk.libraryapp.view.main.MainActivity;

/**
 * Created by Krzysiek on 2015-04-19.
 */
public class TestCase_005_Books extends ActivityInstrumentationTestCase2<MainActivity> {
    private String scenarioName = "Show Books";
    private Solo solo;

    public TestCase_005_Books() {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
    }

    public void testOpenBooks() {
        solo.waitForText("News");
        solo.sleep(1000);

        dragToHamburger();
        solo.clickOnText("Books");
        assertTrue(solo.waitForText("Books"));
        solo.clickInList(0);
        assertTrue(solo.waitForText("Details"));
        refreshBooks();
    }

    public String getScenarioName() {
        return scenarioName;
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities(); // finishes all opened activities
    }

    public void refreshBooks() {
        solo.sleep(6000);
        int screenWidth = getActivity().getWindowManager().getDefaultDisplay().getWidth();
        int screenHeight = getActivity().getWindowManager().getDefaultDisplay().getHeight();
        int fromX, toX, fromY, toY = 0;

        fromX = screenWidth / 2;
        toX = screenWidth / 2;
        fromY = (screenHeight / 2) + (screenHeight / 3);
        toY = (screenHeight / 2) - (screenHeight / 3);

        // Drag UP
        solo.drag(fromX, toX, fromY, toY, 10);
        // here default origin (x,y = 0,0) is left upper corner
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