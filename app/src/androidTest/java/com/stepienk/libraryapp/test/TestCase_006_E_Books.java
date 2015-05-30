package com.stepienk.libraryapp.test;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;

import com.robotium.solo.Solo;
import com.stepienk.libraryapp.view.main.MainActivity;

import info.androidhive.LibraryApp.R;

/**
 * Created by Krzysiek on 2015-04-19.
 */
public class TestCase_006_E_Books extends ActivityInstrumentationTestCase2<MainActivity> {
    private String scenarioName = "Show E-Books";
    private Solo solo;

    public TestCase_006_E_Books() {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
    }

    public void testOpenEBooks() {
        solo.waitForText("News");
        solo.sleep(1000);

        dragToHamburger();
        solo.clickOnText("E-Books");
        assertTrue(solo.waitForText("E-Books"));
        solo.clickInList(0);
        assertTrue(solo.waitForText("Details"));
        refreshEBooks();
        View btnDownload = solo.getView(R.id.btnDownload);
        solo.clickOnView(btnDownload);
        solo.waitForText("File");
        View btnShow = solo.getView(R.id.btnShow);
        solo.clickOnView(btnShow);
    }

    public String getScenarioName() {
        return scenarioName;
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities(); // finishes all opened activities
    }

    public void refreshEBooks() {
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