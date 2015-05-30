package com.stepienk.libraryapp.utils;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.ImageLoadingListener;

import info.androidhive.LibraryApp.R;

/**
 * Created by Krzysiek on 2015-05-04.
 */
public class imagesUtil {

    /**
     * Load image from url and place in specific object
     *
     * @param url         - url of the image (dropbox link)
     * @param options     - additional options for new image (set image when loading is not available)
     * @param imageLoader - ImageLoader instance
     * @param pBar        - reference to progress bar
     * @param activity    - activity we currently operate on
     * @param bookPhoto   - ImageView instance
     */
    public static void loadImageFromURL(String url, DisplayImageOptions options, ImageLoader imageLoader, final ProgressBar pBar, Activity activity, ImageView bookPhoto) {
        options = new DisplayImageOptions.Builder()
                .showStubImage(R.drawable.no_photo)
                .showImageForEmptyUrl(R.drawable.no_photo).cacheInMemory()
                .cacheOnDisc().build();

        imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(activity));
        imageLoader.displayImage(url, bookPhoto, options,
                new ImageLoadingListener() {
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
    }
}
