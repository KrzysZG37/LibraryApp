package com.stepienk.libraryapp.async_tasks;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.stepienk.libraryapp.view.news.NewsFragment;

/**
 * Created by Krzysiek on 2015-06-02.
 */
public class GetAllNewsTask extends AsyncTask<String, Void, Void> {
    private NewsFragment newsFragment;

    public GetAllNewsTask(NewsFragment newsFragment) {
        this.newsFragment = newsFragment;
    }

    /**
     * Before starting background thread
     * Show Progress Bar Dialog
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        newsFragment.setpDialog(new ProgressDialog(newsFragment.getActivity()));
        newsFragment.getpDialog().setMessage("Loading...");
        newsFragment.getpDialog().show();
    }

    /**
     * Downloading data and putting into list of news objects
     *
     * @param params - news data
     * @return
     */
    @Override
    protected Void doInBackground(String... params) {
        newsFragment.setArrayOfList(newsFragment.dropBoxNamesParserInterface.getData(params[0]));
        return null;
    }

    /**
     * After completing background task
     * Dismiss the progress dialog
     * *
     */
    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);

        if (null != newsFragment.getpDialog() && newsFragment.getpDialog().isShowing()) {
            newsFragment.getpDialog().dismiss();
            newsFragment.showToast("News are updated");
        }

        if (null == newsFragment.getArrayOfList() || newsFragment.getArrayOfList().size() == 0) {
            newsFragment.showToast("No data found from web!!!");
            newsFragment.getActivity().finish();
        } else {
            newsFragment.setAdapterToListView();
        }
    }
}
