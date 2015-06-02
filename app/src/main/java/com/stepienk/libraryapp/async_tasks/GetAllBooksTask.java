package com.stepienk.libraryapp.async_tasks;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.stepienk.libraryapp.model.interfaces.booksNamesParserInterface;
import com.stepienk.libraryapp.view.books.AvailableBooksFragment;

import org.json.JSONArray;

/**
 * Created by Krzysiek on 2015-06-02.
 */
public class GetAllBooksTask extends AsyncTask<booksNamesParserInterface, Long, JSONArray> {
    private AvailableBooksFragment availableBooksFragment;

    public GetAllBooksTask(AvailableBooksFragment availableBooksFragment) {
        this.availableBooksFragment = availableBooksFragment;
    }

    /**
     * Before starting background thread
     * Show Progress Bar Dialog
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        availableBooksFragment.setpDialog(new ProgressDialog(availableBooksFragment.getActivity()));
        availableBooksFragment.getpDialog().setMessage("Loading...");
        availableBooksFragment.getpDialog().show();
    }

    /**
     * Downloading data and putting into list of book objects
     *
     * @param params - book data
     * @return
     */
    @Override
    protected JSONArray doInBackground(booksNamesParserInterface... params) {
        availableBooksFragment.setJsonArray(params[0].getAllBooks());
        return params[0].getAllBooks();
    }

    /**
     * After completing background task
     * Dismiss the progress dialog
     * *
     */
    @Override
    protected void onPostExecute(JSONArray jsonArray) {
        if (null != availableBooksFragment.getpDialog() && availableBooksFragment.getpDialog().isShowing()) {
            availableBooksFragment.getpDialog().dismiss();
            availableBooksFragment.showToast("Books are updated");
        }

        if (null == jsonArray) {
            availableBooksFragment.showToast("No data found from web!!!");
            availableBooksFragment.getActivity().finish();
        } else {
            availableBooksFragment.setListAdapter(jsonArray);
        }
    }
}
