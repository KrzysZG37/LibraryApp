package com.stepienk.libraryapp.model.fragments;

import android.support.v4.app.Fragment;

import com.stepienk.libraryapp.view.about.AboutFragment;
import com.stepienk.libraryapp.view.books.BooksFragment;
import com.stepienk.libraryapp.view.dashboard.DashboardFragment;
import com.stepienk.libraryapp.view.ebooks.eBooksFragment;
import com.stepienk.libraryapp.view.fragments_decorator.FragmentDecorator;
import com.stepienk.libraryapp.view.map.MapFragment;
import com.stepienk.libraryapp.view.news.NewsFragment;

/**
 * Created by stepienk on 2015-04-29.
 */
public class FragmentFactory extends FragmentDecorator {

    //flag variable to change onTouch listener according to opened fragment
    public static int fragmentWithOnTouchListener = 1;

    private static Fragment fragment;

    /**
     * Fragment factory for managing fragments in drawer layout
     *
     * @param position - position of specific fragment in list in navigation drawer
     */
    private static void manageFragmentsInDrawerLayout(int position) {
        switch (position) {
            default:
            case 0:
                fragment = new DashboardFragment();
                fragmentWithOnTouchListener = 1;
                break;
            case 1:
                fragment = new NewsFragment();
                fragmentWithOnTouchListener = 0;
                break;
            case 2:
                fragment = new BooksFragment();
                fragmentWithOnTouchListener = 0;
                break;
            case 3:
                fragment = new eBooksFragment();
                fragmentWithOnTouchListener = 0;
                break;
            case 4:
                fragment = new MapFragment();
                break;
            case 5:
                fragment = new AboutFragment();
                break;
        }
    }

    /**
     * Returns new instance of fragment from navigation drawer
     *
     * @param position - position of specific fragment in list in navigation drawer
     * @return
     */
    public static Fragment newInstance(int position) {
        manageFragmentsInDrawerLayout(position);
        return fragment;
    }
}
