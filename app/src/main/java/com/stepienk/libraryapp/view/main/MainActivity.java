package com.stepienk.libraryapp.view.main;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import android.content.res.TypedArray;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.stepienk.libraryapp.adapter.navigation_drawer.NavDrawerListAdapter;
import com.stepienk.libraryapp.model.navigation_drawer.NavDrawerItem;
import com.stepienk.libraryapp.model.session.SessionManager;
import com.stepienk.libraryapp.model.sql_login.SQLiteHandler;
import com.stepienk.libraryapp.model.fragments.FragmentFactory;

import java.util.ArrayList;
import java.util.HashMap;

import info.androidhive.LibraryApp.R;

/**
 * Created by stepienk on 2015-04-10.
 * MainActivity include ActionBarActivity with assigned NavigationDrawer, Toolbar and FragmentFactory to manage through all fragments
 */
public class MainActivity extends ActionBarActivity {
    private ListView mDrawerList;
    private NavDrawerListAdapter adapter;
    private TextView header = null;

    private Toolbar toolbar;
    private ArrayList<NavDrawerItem> navDrawerItems;
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;
    private DrawerLayout Drawer;
    private ActionBarDrawerToggle mDrawerToggle;
    private SQLiteHandler db;
    private SessionManager session;
    private FragmentFactory fragmentFactory = new FragmentFactory();

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_main);

        createToolbar();
        assignLayoutForMainActivity();
        addItemsToDrawer();

        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());
        adapter = new NavDrawerListAdapter(getApplicationContext(), navDrawerItems);
        mDrawerList.setAdapter(adapter);
        mDrawerToggle = new ActionBarDrawerToggle(this, Drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

        };
        Drawer.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        header.setText(this.getResources().getText(R.string.fragment_title_news));

        if (savedInstanceState == null) {
            switchBetweenFragments(1);
        }
    }


    private void createToolbar() {
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle((CharSequence) header);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    private void assignLayoutForMainActivity() {
        header = (TextView) findViewById(R.id.logo_text);
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
        navDrawerItems = new ArrayList<NavDrawerItem>();

        // nav drawer icons from resources
        navMenuIcons = getResources()
                .obtainTypedArray(R.array.nav_drawer_icons);

        Drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);
        // Drawer gradient assigned to the view
        Drawer.setDrawerShadow(R.drawable.navigation_drawer_gradient, Gravity.LEFT);
        mDrawerList = (ListView) findViewById(R.id.list_menu);
    }

    private void addItemsToDrawer() {
        // adding nav drawer items to array
        // Dashboard
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
        // News
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
        // Books
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
        // E-Books
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1)));
        // Map
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
        // About
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1)));
        navMenuIcons.recycle();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            createUserInfoDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class SlideMenuClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            switchBetweenFragments(position);
        }
    }


    /**
     * Operate through fragments based on its position - use FragmentFactory
     *
     * @param position - position of fragment
     */
    private void onNavigationDrawerItemSelected(int position) {
        Fragment fragment;
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragment = fragmentFactory.newInstance(position);
        fragmentManager.beginTransaction()
                .replace(R.id.frame_container, fragment)
                .commit();
    }

    /**
     * Change fragment header based on its position
     *
     * @param id - - position of fragment
     */
    private void replaceHeader(int id) {
        switch (id) {
            case 0:
                header.setText(this.getResources().getText(
                        R.string.fragment_title_dashboard));
                break;
            case 1:
                header.setText(this.getResources().getText(
                        R.string.fragment_title_news));
                break;
            case 2:
                header.setText(this.getResources().getText(
                        R.string.fragment_title_books));
                break;
            case 3:
                header.setText(this.getResources().getText(
                        R.string.fragment_title_e_books));
                break;
            case 4:
                header.setText(this.getResources().getText(
                        R.string.fragment_title_map));
                break;
            case 5:
                header.setText(this.getResources().getText(
                        R.string.fragment_title_about));
                break;
            default:
                break;
        }
    }

    private void switchBetweenFragments(int id) {
        replaceHeader(id);
        onNavigationDrawerItemSelected(id);
        Drawer.closeDrawer(Gravity.START);
    }

    private void createUserInfoDialog() {
        db = new SQLiteHandler(this);
        session = new SessionManager(this);
        HashMap<String, String> user = db.getUserDetails();

        String name = user.get("name");
        String email = user.get("email");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("E-mail: " + email).setTitle("User: " + name);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

}