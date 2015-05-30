package com.stepienk.libraryapp.view.books;


import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stepienk.libraryapp.adapter.tab_swipe.TabsPagerAdapter;
import com.stepienk.libraryapp.model.tab_swipe.SlidingTabLayout;
import com.stepienk.libraryapp.view.fragments_decorator.FragmentDecorator;
import com.stepienk.libraryapp.view.fragments_decorator.FragmentInterface;

import info.androidhive.LibraryApp.R;

/**
 * Created by Krzysiek on 2015-04-07.
 */
public class BooksFragment extends FragmentDecorator implements ActionBar.TabListener {

    private ViewPager viewPager;
    private TabsPagerAdapter mAdapter;
    private FragmentManager fragmentManager;
    private SlidingTabLayout slidingTabs;
    private CharSequence tabTitles[] = {"Available", "Reserved"};

    public BooksFragment(FragmentInterface booksFragment) {
        fi = booksFragment;
    }

    public BooksFragment() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View rootView = inflater.inflate(R.layout.books_main, container, false);
        viewPager = (ViewPager) rootView.findViewById(R.id.pager);
        assignSlidingTabLayoutForBooksFragment(rootView);

        return rootView;
    }

    private void assignSlidingTabLayoutForBooksFragment(View rootView) {
        fragmentManager = getChildFragmentManager();

        mAdapter = new TabsPagerAdapter(fragmentManager, tabTitles);
        viewPager.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        slidingTabs = (SlidingTabLayout) rootView.findViewById(R.id.tabs);
        slidingTabs.setDistributeEvenly(true);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        slidingTabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.counter_text_color);
            }
        });
        slidingTabs.setViewPager(viewPager);
    }


    @Override
    public void onTabSelected(ActionBar.Tab tab, android.app.FragmentTransaction fragmentTransaction) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, android.app.FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, android.app.FragmentTransaction fragmentTransaction) {
    }
}
