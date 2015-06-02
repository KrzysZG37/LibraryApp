package com.stepienk.libraryapp.view.news;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.stepienk.libraryapp.adapter.dropbox_news.NewsRowAdapter;
import com.stepienk.libraryapp.async_tasks.GetAllNewsTask;
import com.stepienk.libraryapp.model.dropbox_news.News;
import com.stepienk.libraryapp.model.dropbox_news.NewsNamesParser;
import com.stepienk.libraryapp.model.fragments.FragmentFactory;
import com.stepienk.libraryapp.model.interfaces.dropBoxNamesParserInterface;
import com.stepienk.libraryapp.model.network.AppConfig;
import com.stepienk.libraryapp.utils.NetworkAvailable;
import com.stepienk.libraryapp.view.fragments_decorator.FragmentDecorator;
import com.stepienk.libraryapp.view.fragments_decorator.FragmentInterface;

import java.util.List;

import info.androidhive.LibraryApp.R;

public class NewsFragment extends FragmentDecorator implements OnItemClickListener {

    private static final String rssFeed = AppConfig.URL_NEWS_RSS_FEED;
    private List<News> arrayOfList;
    private ListView listView;
    private ProgressDialog pDialog;
    public dropBoxNamesParserInterface dropBoxNamesParserInterface = new NewsNamesParser();

    public NewsFragment(FragmentInterface newsFragment) {
        fi = newsFragment;
    }

    public NewsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.news_main, container, false);
        listView = (ListView) v.findViewById(R.id.listView);
        setProperClickListener(listView);


        if (NetworkAvailable.isNetworkAvailable(getActivity())) {
            new GetAllNewsTask(this).execute(rssFeed);
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


    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        News news = arrayOfList.get(position);
        Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
        intent.putExtra("url", news.getLink());
        intent.putExtra("title", news.getTitle());
        intent.putExtra("desc", news.getDesc());
        startActivity(intent);
        getActivity().overridePendingTransition(R.animator.left_in, R.animator.left_out);
    }

    public void setAdapterToListView() {
        NewsRowAdapter objAdapter = new NewsRowAdapter(getActivity(),
                R.layout.news_row, arrayOfList);
        listView.setAdapter(objAdapter);
    }

    public void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPause() {
        super.onPause();
        if ((pDialog != null) && pDialog.isShowing())
            pDialog.dismiss();
        pDialog = null;
    }

    public ProgressDialog getpDialog() {
        return pDialog;
    }

    public void setpDialog(ProgressDialog pDialog) {
        this.pDialog = pDialog;
    }

    public List<News> getArrayOfList() {
        return arrayOfList;
    }

    public void setArrayOfList(List<News> list) {
        this.arrayOfList = list;
    }


}

