package com.stepienk.libraryapp.async_tasks;

import android.os.AsyncTask;

import com.stepienk.libraryapp.model.interfaces.booksNamesParserInterface;
import com.stepienk.libraryapp.view.books.ReservedBooksFragment;

import org.json.JSONArray;

/**
 * Created by Krzysiek on 2015-06-02.
 */
public class GetAllReservedBooksTask extends AsyncTask<booksNamesParserInterface, Long, JSONArray> {
    private ReservedBooksFragment reservedBooksFragment;

    public GetAllReservedBooksTask(ReservedBooksFragment reservedBooksFragment) {
        this.reservedBooksFragment = reservedBooksFragment;
    }

    /**
     * Before starting background thread
     * Show Progress Bar Dialog
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
 /*       availableBooksFragment.setpDialog(new ProgressDialog(availableBooksFragment.getActivity()));
        availableBooksFragment.getpDialog().setMessage("Loading...");
        availableBooksFragment.getpDialog().show();*/
    }

    /**
     * Downloading data and putting into list of book objects
     *
     * @param params - book data
     * @return
     */
    @Override
    protected JSONArray doInBackground(booksNamesParserInterface... params) {
        reservedBooksFragment.setJsonArray(params[0].getAllBooks());
        return params[0].getAllBooks();
    }

    /**
     * After completing background task
     * Dismiss the progress dialog
     * *
     */
    @Override
    protected void onPostExecute(JSONArray jsonArray) {
        if (null != reservedBooksFragment.getpDialog() && reservedBooksFragment.getpDialog().isShowing()) {
            reservedBooksFragment.getpDialog().dismiss();
            reservedBooksFragment.showToast("Books are updated");
        }

        if (null == jsonArray) {
            reservedBooksFragment.showToast("No data found from web!!!");
        } else {
            reservedBooksFragment.setListAdapter(jsonArray);
        }
    }
}
