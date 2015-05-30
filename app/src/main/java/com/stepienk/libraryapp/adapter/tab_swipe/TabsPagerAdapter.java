package com.stepienk.libraryapp.adapter.tab_swipe;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentPagerAdapter;

import com.stepienk.libraryapp.view.books.AvailableBooksFragment;
import com.stepienk.libraryapp.view.books.ReservedBooksFragment;

/**
 * Created by stepienk on 2015-04-07.
 */
public class TabsPagerAdapter extends FragmentPagerAdapter {

    CharSequence Titles[];

    public TabsPagerAdapter(FragmentManager fm, CharSequence mTitles[]) {
        super(fm);
        this.Titles = mTitles;
    }

    /**
     * Return fragment with given content
     * case 0 - available books fragment activity
     * case 1 - reserved books fragment activity
     *
     * @param index - index of given fragment
     * @return
     */
    @Override
    public Fragment getItem(int index) {
        switch (index) {
            case 0:
                return new AvailableBooksFragment();
            case 1:
                return new ReservedBooksFragment();
        }

        return null;
    }

    /**
     * Get item count - equal to number of tabs
     *
     * @return
     */
    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

}