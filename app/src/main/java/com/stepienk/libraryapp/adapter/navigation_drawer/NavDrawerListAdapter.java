package com.stepienk.libraryapp.adapter.navigation_drawer;

import info.androidhive.LibraryApp.R;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.stepienk.libraryapp.model.navigation_drawer.NavDrawerItem;

/**
 * Created by Krzysiek on 2015-04-14.
 */
public class NavDrawerListAdapter extends BaseAdapter {

    private final Context context;
    private final ArrayList<NavDrawerItem> navDrawerItems;
    private ImageView imgIcon;
    private TextView txtTitle;

    public NavDrawerListAdapter(Context context, ArrayList<NavDrawerItem> navDrawerItems) {
        this.context = context;
        this.navDrawerItems = navDrawerItems;
    }

    @Override
    public int getCount() {
        return navDrawerItems.size();
    }

    @Override
    public Object getItem(int position) {
        return navDrawerItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.drawer_item, null);
        }

        assignLayoutFofNavigationDrawer(convertView);
        setDataInNavigationDrawer(position);

        return convertView;
    }

    private void assignLayoutFofNavigationDrawer(View view) {
        imgIcon = (ImageView) view.findViewById(R.id.icon);
        txtTitle = (TextView) view.findViewById(R.id.title);
    }

    private void setDataInNavigationDrawer(int position) {
        imgIcon.setImageResource(navDrawerItems.get(position).getIcon());
        txtTitle.setText(navDrawerItems.get(position).getTitle());
    }

}
