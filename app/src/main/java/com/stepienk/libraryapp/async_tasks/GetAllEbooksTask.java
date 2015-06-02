package com.stepienk.libraryapp.async_tasks;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.stepienk.libraryapp.view.ebooks.eBooksFragment;

/**
 * Created by Krzysiek on 2015-06-02.
 */
public class GetAllEbooksTask extends AsyncTask<String, Void, Void> {
    private com.stepienk.libraryapp.view.ebooks.eBooksFragment eBooksFragment;

    public GetAllEbooksTask(com.stepienk.libraryapp.view.ebooks.eBooksFragment eBooksFragment) {
        this.eBooksFragment = eBooksFragment;
    }

    /**
     * Before starting background thread
     * Show Progress Bar Dialog
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        eBooksFragment.setpDialog(new ProgressDialog(eBooksFragment.getActivity()));
        eBooksFragment.getpDialog().setMessage("Loading...");
        eBooksFragment.getpDialog().show();
    }

    /**
     * Downloading data and putting into list of eBook objects
     *
     * @param params - eBook data
     * @return
     */
    @Override
    protected Void doInBackground(String... params) {
        eBooksFragment.setArrayOfList(eBooksFragment.dropBoxNamesParserInterface.getData(params[0]));
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

        if (null != eBooksFragment.getpDialog() && eBooksFragment.getpDialog().isShowing()) {
            eBooksFragment.getpDialog().dismiss();
            eBooksFragment.showToast("E-books are updated");
        }

        if (null == eBooksFragment.getArrayOfList() || eBooksFragment.getArrayOfList().size() == 0) {
            eBooksFragment.showToast("No data found from web!!!");
            eBooksFragment.getActivity().finish();
        } else {
            eBooksFragment.setAdapterToListView();
        }

    }
}
