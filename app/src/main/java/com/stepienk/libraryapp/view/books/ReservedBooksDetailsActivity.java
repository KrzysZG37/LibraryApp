package com.stepienk.libraryapp.view.books;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.stepienk.libraryapp.utils.LoadImages;

import info.androidhive.LibraryApp.R;

/**
 * Created by Krzysiek on 2015-06-06.
 */
public class ReservedBooksDetailsActivity extends ActionBarActivity {
    private DisplayImageOptions options;
    private ImageLoader imageLoader;
    private ProgressBar pBar;
    private TextView bookTitle, bookDescription, bookDate;
    private ImageView bookPhoto;
    private Button btnReserve;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.books_reserved_details);
        createToolbar();

        final Bundle b = getIntent().getExtras();
        assignLayoutForBooksActivity();
        setDataInBooksActivity(b);
    }

    private void createToolbar() {
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle("Details");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void assignLayoutForBooksActivity() {
        pBar = (ProgressBar) findViewById(R.id.pbardesc);
        bookTitle = (TextView) findViewById(R.id.reservedBookTitleDetails);
        bookDescription = (TextView) findViewById(R.id.reservedBookDescriptionDetails);
        bookDate = (TextView) findViewById(R.id.reservedBookDateDetails);
        bookPhoto = (ImageView) findViewById(R.id.reservedBookPhotoDetails);
        btnReserve = (Button) findViewById(R.id.btnGiveBack);
    }

    private void setDataInBooksActivity(Bundle b) {
        final String title = b.getString("name");
        final String desc = b.getString("desc");
        final String date = b.getString("date");

        bookTitle.setText(title);
        bookDescription.setText(desc);
        bookDate.setText(date);

        String url = b.getString("image");
        LoadImages.loadImageFromURL(url, options, imageLoader, pBar, this, bookPhoto);

        btnReserve.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                btnReserve.setBackgroundResource(R.color.bg_login);
                btnReserve.setText("Returned");
            }
        });
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
