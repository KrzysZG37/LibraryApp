package com.stepienk.libraryapp.view.news;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.stepienk.libraryapp.utils.imagesUtil;

import info.androidhive.LibraryApp.R;

public class NewsDetailActivity extends ActionBarActivity {

    private DisplayImageOptions options;
    private ImageLoader imageLoader;
    private ProgressBar pBar;
    private TextView newsTitle, newsDescription;
    private ImageView newsPhoto;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_details);
        createToolbar();

        Bundle b = getIntent().getExtras();
        assignLayoutForNewsActivity();
        setDataInNewsActivity(b);
    }


    private void createToolbar() {
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle("Details");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void assignLayoutForNewsActivity() {
        pBar = (ProgressBar) findViewById(R.id.pbardesc);
        newsTitle = (TextView) findViewById(R.id.newsTitle);
        newsDescription = (TextView) findViewById(R.id.newsDescription);
        newsPhoto = (ImageView) findViewById(R.id.newsPhoto);
    }

    private void setDataInNewsActivity(Bundle b) {
        String title = b.getString("title");
        String desc = b.getString("desc");
        String url = b.getString("url");

        newsTitle.setText(title);
        newsDescription.setText(desc);
        imagesUtil.loadImageFromURL(url, options, imageLoader, pBar, this, newsPhoto);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                overridePendingTransition(R.animator.left_in, R.animator.left_out);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        this.finish();
        overridePendingTransition(R.animator.left_in, R.animator.left_out);
    }
}
