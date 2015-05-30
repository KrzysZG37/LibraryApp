package com.stepienk.libraryapp.view.ebooks;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.stepienk.libraryapp.utils.imagesUtil;

import net.sf.andpdf.pdfviewer.PdfViewerActivity;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import info.androidhive.LibraryApp.R;

/**
 * Created by Krzysiek on 2015-04-06.
 */
public class eBookDetailActivity extends ActionBarActivity {

    private DisplayImageOptions options;
    private ImageLoader imageLoader;
    private ProgressBar pBar;
    private TextView eBookTitle, eBookDescription;
    private ImageView eBookPhoto;
    private Button btnDownload;
    private Button btnShowPdf;
    private String descriptionToDownload;
    private ProgressDialog pDialog;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ebooks_details);
        createToolbar();

        final Bundle b = getIntent().getExtras();
        assignLayoutForEBookActivity();
        setDataInEBooksActivity(b);
    }

    private void createToolbar() {
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle("Details");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void assignLayoutForEBookActivity() {
        pBar = (ProgressBar) findViewById(R.id.pbardesc);
        eBookTitle = (TextView) findViewById(R.id.eBookTitle);
        eBookDescription = (TextView) findViewById(R.id.eBookDescription);
        eBookPhoto = (ImageView) findViewById(R.id.eBookPhoto);
        btnDownload = (Button) findViewById(R.id.btnDownload);
        btnShowPdf = (Button) findViewById(R.id.btnShow);
    }

    private void setDataInEBooksActivity(Bundle b) {
        final String title = b.getString("title");
        final String desc = b.getString("desc");
        final String pdfLink = b.getString("pdflink");
        final String url = b.getString("url");

        descriptionToDownload = title;
        eBookTitle.setText(title);
        eBookDescription.setText(desc);
        imagesUtil.loadImageFromURL(url, options, imageLoader, pBar, this, eBookPhoto);

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DownloadFileFromURL().execute(pdfLink);
            }
        });

        btnShowPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(eBookDetailActivity.this, eBookShowPdfActivity.class);
                intent.putExtra(PdfViewerActivity.EXTRA_PDFFILENAME, Environment.getExternalStoragePublicDirectory(descriptionToDownload) + ".pdf");
                startActivity(intent);
                overridePendingTransition(R.animator.left_in, R.animator.left_out);
            }
        });
    }

    private class DownloadFileFromURL extends AsyncTask<String, String, String> {
        /**
         * Before starting background thread
         * Show Progress Bar Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = ProgressDialog.show(eBookDetailActivity.this, "Please wait ...", "File is downloading...", true);
            pDialog.setCancelable(true);
        }

        /**
         * Downloading file in background thread
         */
        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {
                URL url = new URL(f_url[0]);
                URLConnection connection = url.openConnection();
                connection.connect();

                int lengthOfFile = connection.getContentLength();
                InputStream input = new BufferedInputStream(url.openStream(), 8192);
                OutputStream output = new FileOutputStream(Environment.getExternalStoragePublicDirectory(descriptionToDownload) + ".pdf");

                byte data[] = new byte[1024];

                long total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    publishProgress("" + (int) ((total * 100) / lengthOfFile));
                    output.write(data, 0, count);
                }

                output.flush();
                output.close();
                input.close();

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }
            return null;
        }

        /**
         * Updating progress bar
         */
        protected void onProgressUpdate(String... progress) {
        }

        /**
         * After completing background task
         * Dismiss the progress dialog
         * *
         */
        @Override
        protected void onPostExecute(String file_url) {
            if (null != pDialog && pDialog.isShowing()) {
                pDialog.dismiss();
                Toast.makeText(getApplicationContext(), "File " + descriptionToDownload + " was downloaded", Toast.LENGTH_SHORT).show();
            }
        }
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