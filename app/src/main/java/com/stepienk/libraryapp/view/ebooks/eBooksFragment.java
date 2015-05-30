package com.stepienk.libraryapp.view.ebooks;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.stepienk.libraryapp.adapter.dropbox_ebooks.EBooksRowAdapter;
import com.stepienk.libraryapp.model.dropbox_ebooks.EBookNamesParser;
import com.stepienk.libraryapp.model.dropbox_ebooks.EBook;
import com.stepienk.libraryapp.model.fragments.FragmentFactory;
import com.stepienk.libraryapp.model.interfaces.dropBoxNamesParserInterface;
import com.stepienk.libraryapp.model.network.AppConfig;
import com.stepienk.libraryapp.utils.networkAvailableUtil;
import com.stepienk.libraryapp.view.fragments_decorator.FragmentDecorator;
import com.stepienk.libraryapp.view.fragments_decorator.FragmentInterface;

import java.util.List;

import info.androidhive.LibraryApp.R;

/**
 * Created by Krzysiek on 2015-04-06.
 */
public class eBooksFragment extends FragmentDecorator implements AdapterView.OnItemClickListener {

    private static final String rssFeed = AppConfig.URL_E_BOOKS_RDD_FEED;
    private List<EBook> arrayOfList;
    private ListView listView;
    private ProgressDialog pDialog;
    private dropBoxNamesParserInterface dropBoxNamesParserInterface = new EBookNamesParser();

    public eBooksFragment(FragmentInterface eBooksFragment) {
        fi = eBooksFragment;
    }

    public eBooksFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.ebooks_main, container, false);

        listView = (ListView) v.findViewById(R.id.listview_ebooks);
        setProperClickListener(listView);

        if (networkAvailableUtil.isNetworkAvailable(getActivity())) {
            new MyTask().execute(rssFeed);
        } else {
            showToast("No Network Connection!!!");
        }
        return v;
    }

    private void setProperClickListener(ListView listView) {
        listView.setOnItemClickListener(this);

        if (FragmentFactory.fragmentWithOnTouchListener == 1) {
            listView.setOnTouchListener(new View.OnTouchListener() {
                // Setting on Touch Listener for handling the touch inside ScrollView
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    return false;
                }
            });
        }
    }


    class MyTask extends AsyncTask<String, Void, Void> {
        /**
         * Before starting background thread
         * Show Progress Bar Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Loading...");
            pDialog.show();
        }

        /**
         * Downloading data and putting into list of eBook objects
         *
         * @param params - eBook data
         * @return
         */
        @Override
        protected Void doInBackground(String... params) {
            arrayOfList = dropBoxNamesParserInterface.getData(params[0]);
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

            if (null != pDialog && pDialog.isShowing()) {
                pDialog.dismiss();
                showToast("E-books are updated");
            }

            if (null == arrayOfList || arrayOfList.size() == 0) {
                showToast("No data found from web!!!");
                getActivity().finish();
            } else {
                setAdapterToListView();
            }

        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if ((pDialog != null) && pDialog.isShowing())
            pDialog.dismiss();
        pDialog = null;
    }


    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        EBook item = arrayOfList.get(position);
        Intent intent = new Intent(getActivity(), eBookDetailActivity.class);
        intent.putExtra("url", item.getLink());
        intent.putExtra("title", item.getTitle());
        intent.putExtra("desc", item.getDesc());
        intent.putExtra("pdflink", item.getPdfLink());
        startActivity(intent);
        getActivity().overridePendingTransition(R.animator.left_in, R.animator.left_out);
    }

    void setAdapterToListView() {
        EBooksRowAdapter objAdapter = new EBooksRowAdapter(getActivity(), R.layout.ebooks_row,
                arrayOfList);
        listView.setAdapter(objAdapter);
    }

    void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }


}
