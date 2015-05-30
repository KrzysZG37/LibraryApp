package com.stepienk.libraryapp.model.navigation_drawer;

/**
 * Created by Krzysiek on 2015-04-20.
 * Class for managing all data included in each navigation drawer object
 */
public class NavDrawerItem {

    private String title;
    private int icon;

    public NavDrawerItem(String title, int icon) {
        this.title = title;
        this.icon = icon;
    }

    public String getTitle() {
        return this.title;
    }

    public int getIcon() {
        return this.icon;
    }
}
