package com.stepienk.libraryapp.view.books;

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

import com.stepienk.libraryapp.adapter.sql_books.BooksRowAdapter;
import com.stepienk.libraryapp.model.fragments.FragmentFactory;
import com.stepienk.libraryapp.model.interfaces.booksNamesParserInterface;
import com.stepienk.libraryapp.model.session.SessionManager;
import com.stepienk.libraryapp.model.sql_books.BooksNamesParser;
import com.stepienk.libraryapp.utils.networkAvailableUtil;
import com.stepienk.libraryapp.view.fragments_decorator.FragmentDecorator;
import com.stepienk.libraryapp.view.fragments_decorator.FragmentInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import info.androidhive.LibraryApp.R;

/**
 * Created by Krzysiek on 2015-04-07.
 */
public class AvailableBooksFragment extends FragmentDecorator implements AdapterView.OnItemClickListener {

    private ListView availableBooks;
    private ProgressDialog pDialog;
    private SessionManager session;
    private JSONArray jsonArray = null;

    public AvailableBooksFragment(FragmentInterface availableBooksFragment) {
        fi = availableBooksFragment;
    }

    public AvailableBooksFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.books_available_main, container, false);
        availableBooks = (ListView) rootView.findViewById(R.id.listview_books);
        setProperClickListener();

        session = new SessionManager(getActivity().getApplicationContext());

        if (networkAvailableUtil.isNetworkAvailable(getActivity())) {
            if (session.isLoggedIn()) {
                new getAllBooksTask().execute(new BooksNamesParser());
            } else {
                showToast("No connection with database!!!");
            }
        } else {
            showToast("No Network Connection!!!");
        }

        return rootView;
    }

    private void setProperClickListener() {
        availableBooks.setOnItemClickListener(this);

        if (FragmentFactory.fragmentWithOnTouchListener == 1) {
            availableBooks.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    return false;
                }
            });
        }
    }


    public void setListAdapter(JSONArray jsonArray) {
        availableBooks.setAdapter(new BooksRowAdapter(jsonArray, getActivity()));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        JSONObject jsonObject;
        String name = null;
        String description = null;
        String date = null;
        String image = null;

        try {
            jsonObject = jsonArray.getJSONObject(position);
            name = jsonObject.getString("name");
            description = jsonObject.getString("description");
            date = jsonObject.getString("date");
            image = jsonObject.getString("image");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(getActivity(), AvailableBooksDetailsActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("desc", description);
        intent.putExtra("date", date);
        intent.putExtra("image", image);
        startActivity(intent);
        getActivity().overridePendingTransition(R.animator.left_in, R.animator.left_out);
    }

    private class getAllBooksTask extends AsyncTask<booksNamesParserInterface, Long, JSONArray> {
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
         * Downloading data and putting into list of book objects
         *
         * @param params - book data
         * @return
         */
        @Override
        protected JSONArray doInBackground(booksNamesParserInterface... params) {
            jsonArray = params[0].getAllBooks();
            return params[0].getAllBooks();
        }

        /**
         * After completing background task
         * Dismiss the progress dialog
         * *
         */
        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            if (null != pDialog && pDialog.isShowing()) {
                pDialog.dismiss();
                showToast("Books are updated");
            }

            if (null == jsonArray) {
                showToast("No data found from web!!!");
                getActivity().finish();
            } else {
                setListAdapter(jsonArray);
            }
        }
    }

    void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }
}