package com.stepienk.libraryapp.adapter.dropbox_ebooks;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.util.Log;
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
import com.stepienk.libraryapp.model.dropbox_ebooks.EBook;

import java.util.List;

import info.androidhive.LibraryApp.R;

/**
 * Created by Krzysiek on 2015-04-14.
 * eBook adapter to manage eBook data
 */
public class EBooksRowAdapter extends ArrayAdapter<EBook> {

    private final Activity activity;
    private final List<EBook> items;
    private EBook objBean;
    private final int row;
    private final DisplayImageOptions options;
    private final ImageLoader imageLoader;

    /**
     * Constructor for eBook adapter
     *
     * @param act       - current activity which we operate on
     * @param resource  - specific layout
     * @param arrayList - arrayList of items
     */
    public EBooksRowAdapter(Activity act, int resource, List<EBook> arrayList) {
        super(act, resource, arrayList);
        this.activity = act;
        this.row = R.layout.ebooks_row;
        this.items = arrayList;

        options = new DisplayImageOptions.Builder()
                .showStubImage(R.drawable.no_photo)
                .showImageForEmptyUrl(R.drawable.no_photo).cacheInMemory()
                .cacheOnDisc().build();
        imageLoader = ImageLoader.getInstance();

    }

    /**
     * Function for returning eBook adapter with all data
     *
     * @param position    position of the book
     * @param convertView - returned view with all data
     * @param parent      - overridden argument
     * @return
     */
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        EBooksViewHolder holder;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(row, null);

            holder = new EBooksViewHolder();
            view.setTag(holder);
        } else {
            holder = (EBooksViewHolder) view.getTag();
        }

        if ((items == null) || ((position + 1) > items.size()))
            return view;

        objBean = items.get(position);
        assignLayoutForViewHolder(holder, view);
        setDataInEBookFragment(holder);

        return view;
    }

    /**
     * Assigning layout in listView for eBook list
     *
     * @param holder - eBook holder to keep title, description, date and image url
     * @param view   - specific layout
     */
    private void assignLayoutForViewHolder(EBooksViewHolder holder, View view) {
        holder.eBookTitle = (TextView) view.findViewById(R.id.eBookTitle);
        holder.eBookDescription = (TextView) view.findViewById(R.id.eBookDescription);
        holder.eBookDate = (TextView) view.findViewById(R.id.eBookDate);
        holder.eBookImage = (ImageView) view.findViewById(R.id.eBookImage);
        holder.pBar = (ProgressBar) view.findViewById(R.id.pBar);
    }

    /**
     * Setting data in each eBook row
     *
     * @param holder- eBook holder to keep title, description, date and image url
     */
    public void setDataInEBookFragment(EBooksViewHolder holder) {
        setTitleInEBookViewHolder(holder);
        setDescriptionInEBookViewHolder(holder);
        setDateInEBookViewHolder(holder);
        setImageInEBookViewHolder(holder);
    }

    private void setTitleInEBookViewHolder(EBooksViewHolder holder) {
        if (holder.eBookTitle != null && null != objBean.getTitle()
                && objBean.getTitle().trim().length() > 0) {
            holder.eBookTitle.setText(Html.fromHtml(objBean.getTitle()));
        }
    }

    private void setDescriptionInEBookViewHolder(EBooksViewHolder holder) {
        if (holder.eBookDescription != null && null != objBean.getDesc()
                && objBean.getDesc().trim().length() > 0) {
            holder.eBookDescription.setText(Html.fromHtml(objBean.getDesc()));
        }
    }

    private void setDateInEBookViewHolder(EBooksViewHolder holder) {
        if (holder.eBookDate != null && null != objBean.getPubDate()
                && objBean.getPubDate().trim().length() > 0) {
            holder.eBookDate.setText(Html.fromHtml(objBean.getPubDate()));
        }
    }

    private void setImageInEBookViewHolder(EBooksViewHolder holder) {
        if (holder.eBookImage != null) {
            if (null != objBean.getLink()
                    && objBean.getLink().trim().length() > 0) {
                final ProgressBar pBar = holder.pBar;

                imageLoader.init(ImageLoaderConfiguration
                        .createDefault(activity));
                imageLoader.displayImage(objBean.getLink(), holder.eBookImage,
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
                Log.d("setImageInEBooks", "download image");
            } else {
                holder.eBookImage.setImageResource(R.drawable.ic_home);
                Log.d("setImageInEBooks", "set default image");
            }
        }
    }

    public EBook getObjBean() {
        return objBean;
    }

    public void setObjBean(EBook objBean) {
        this.objBean = objBean;
    }


}