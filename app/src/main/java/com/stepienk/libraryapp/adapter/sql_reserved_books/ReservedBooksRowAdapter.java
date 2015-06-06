package com.stepienk.libraryapp.adapter.sql_reserved_books;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.stepienk.libraryapp.utils.LoadImages;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import info.androidhive.LibraryApp.R;

/**
 * Created by Krzysiek on 2015-04-14.
 */
public class ReservedBooksRowAdapter extends BaseAdapter {
    private JSONArray dataArray;
    private Activity activity;
    private static LayoutInflater inflater = null;
    private DisplayImageOptions options;
    private ImageLoader imageLoader;
    private ProgressBar pBar;
    private ImageView img;

    public ReservedBooksRowAdapter(JSONArray jsonArray, Activity a) {
        dataArray = jsonArray;
        activity = a;
        inflater = (LayoutInflater) this.activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader = ImageLoader.getInstance();
    }

    @Override
    public int getCount() {
        return this.dataArray.length();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        JSONObject json = null;
        ReservedBooksViewHolder holder = null;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.books_reserved_row, null);
            holder = new ReservedBooksViewHolder();
            assignLayoutForBookAdapter(convertView, holder);
        } else {
            holder = (ReservedBooksViewHolder) convertView.getTag();
        }

        setDataInBookFragment(json, holder, position);

        return convertView;
    }

    private void assignLayoutForBookAdapter(View convertView, ReservedBooksViewHolder holder) {
        holder.bookName = (TextView) convertView.findViewById(R.id.reservedBookTitle);
        holder.bookDescription = (TextView) convertView.findViewById(R.id.reservedBookDescription);
        holder.bookDate = (TextView) convertView.findViewById(R.id.reservedBookDate);
        img = holder.image = (ImageView) convertView.findViewById(R.id.reservedBookImage);
        pBar = (ProgressBar) convertView.findViewById(R.id.reservedPBar);

        convertView.setTag(holder);
    }

    /**
     * Setting data in book fragments using JSON parses
     *
     * @param json     - json with all book data (name, description,date, image url
     * @param holder   - book holder to keep title, description, date and image url
     * @param position - concrete position of book in list
     */
    private void setDataInBookFragment(JSONObject json, ReservedBooksViewHolder holder, int position) {
        String imageUrl = null;
        try {
            json = dataArray.getJSONObject(position);
            holder.bookName.setText(json.getString("name"));
            holder.bookDescription.setText(json.getString("description"));
            holder.bookDate.setText(json.getString("date"));
            imageUrl = json.getString("image");
            LoadImages.loadImageFromURL(imageUrl, options, imageLoader, pBar, activity, img);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
