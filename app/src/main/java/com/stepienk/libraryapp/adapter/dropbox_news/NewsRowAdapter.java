package com.stepienk.libraryapp.adapter.dropbox_news;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.ImageLoadingListener;
import com.stepienk.libraryapp.model.dropbox_news.News;

import java.util.List;

import info.androidhive.LibraryApp.R;

/**
 * Created by Krzysiek on 2015-04-14.
 */
public class NewsRowAdapter extends ArrayAdapter<News> {

    private final Activity activity;
    private final List<News> newses;
    private News objBean;
    private final int row;
    private final DisplayImageOptions options;
    private final ImageLoader imageLoader;

    /**
     * Constructor for news adapter
     *
     * @param act       - current activity which we operate on
     * @param resource  - specific layout
     * @param arrayList - arrayList of items
     */
    public NewsRowAdapter(Activity act, int resource, List<News> arrayList) {
        super(act, resource, arrayList);
        this.activity = act;
        this.row = R.layout.news_row;
        this.newses = arrayList;

        options = new DisplayImageOptions.Builder()
                .showStubImage(R.drawable.no_photo)
                .showImageForEmptyUrl(R.drawable.no_photo).cacheInMemory()
                .cacheOnDisc().build();
        imageLoader = ImageLoader.getInstance();
    }

    /**
     * Function for returning news adapter with all data
     *
     * @param position    position of the news
     * @param convertView - returned view with all data
     * @param parent      - overridden argument
     * @return
     */
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        NewsViewHolder holder;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(row, null);

            holder = new NewsViewHolder();
            view.setTag(holder);
        } else {
            holder = (NewsViewHolder) view.getTag();
        }

        if ((newses == null) || ((position + 1) > newses.size()))
            return view;

        objBean = newses.get(position);
        assignLayoutForViewHolder(holder, view);
        setDataInNewsFragment(holder);

        return view;
    }

    /**
     * Assigning layout in listView for news list
     *
     * @param holder - news holder to keep title, description, date and image url
     * @param view   - specific layout
     */
    private void assignLayoutForViewHolder(NewsViewHolder holder, View view) {
        holder.newsTitle = (TextView) view.findViewById(R.id.newsTitle);
        holder.newsDescription = (TextView) view.findViewById(R.id.newsDescription);
        holder.newsDate = (TextView) view.findViewById(R.id.newsDate);
        holder.newsImage = (ImageView) view.findViewById(R.id.newsImage);
        holder.pBar = (ProgressBar) view.findViewById(R.id.pBar);
    }

    /**
     * Setting data in each news row
     *
     * @param holder- news holder to keep title, description, date and image url
     */
    private void setDataInNewsFragment(NewsViewHolder holder) {
        setTitleInNewsViewHolder(holder);
        setDescriptionInNewsViewHolder(holder);
        setDateInNewsViewHolder(holder);
        setImageInNewsViewHolder(holder);
    }

    private void setTitleInNewsViewHolder(NewsViewHolder holder) {
        if (holder.newsTitle != null && null != objBean.getTitle()
                && objBean.getTitle().trim().length() > 0) {
            holder.newsTitle.setText(Html.fromHtml(objBean.getTitle()));
        }
    }

    private void setDescriptionInNewsViewHolder(NewsViewHolder holder) {
        if (holder.newsDescription != null && null != objBean.getDesc()
                && objBean.getDesc().trim().length() > 0) {
            holder.newsDescription.setText(Html.fromHtml(objBean.getDesc()));
        }
    }

    private void setDateInNewsViewHolder(NewsViewHolder holder) {
        if (holder.newsDate != null && null != objBean.getPubDate()
                && objBean.getPubDate().trim().length() > 0) {
            holder.newsDate.setText(Html.fromHtml(objBean.getPubDate()));
        }
    }

    private void setImageInNewsViewHolder(NewsViewHolder holder) {
        if (holder.newsImage != null) {
            if (null != objBean.getLink()
                    && objBean.getLink().trim().length() > 0) {
                final ProgressBar pBar = holder.pBar;

                imageLoader.init(ImageLoaderConfiguration
                        .createDefault(activity));
                imageLoader.displayImage(objBean.getLink(), holder.newsImage,
                        options, new ImageLoadingListener() {
                            @Override
                            public void onLoadingComplete() {
                                pBar.setVisibility(View.INVISIBLE);
                            }

                            @Override
                            public void onLoadingFailed() {
                                pBar.setVisibility(View.INVISIBLE);
                            }

                            @Override
                            public void onLoadingStarted() {
                                pBar.setVisibility(View.VISIBLE);
                            }
                        });

            } else {
                holder.newsImage.setImageResource(R.drawable.ic_home);
            }
        }
    }


}