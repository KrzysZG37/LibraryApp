package com.stepienk.libraryapp.test.robotium_tests;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.ListView;

import com.robotium.solo.Solo;
import com.stepienk.libraryapp.view.main.MainActivity;

import info.androidhive.LibraryApp.R;

/**
 * Created by Krzysiek on 2015-04-16.
 */
public class TestCase_004_News extends ActivityInstrumentationTestCase2<MainActivity> {
    private String scenarioName = "Show news";
    private Solo solo;

    public TestCase_004_News() {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
    }

    public void testOpenNews() {
        solo.waitForText("News");
        solo.sleep(1000);
        solo.clickInList(0);
        refreshNews();
        assertTrue(solo.waitForText("Details"));
    }

    public String getScenarioName() {
        return scenarioName;
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities(); // finishes all opened activities
    }

    public void refreshNews() {
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

}
