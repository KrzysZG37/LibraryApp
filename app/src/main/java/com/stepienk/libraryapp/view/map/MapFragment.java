package com.stepienk.libraryapp.view.map;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.stepienk.libraryapp.utils.NetworkAvailable;
import com.stepienk.libraryapp.view.fragments_decorator.FragmentDecorator;
import com.stepienk.libraryapp.view.fragments_decorator.FragmentInterface;

import info.androidhive.LibraryApp.R;

/**
 * Created by stepienk on 2015-04-23.
 */
public class MapFragment extends FragmentDecorator {

    private WebView myWebView;
    private String mapPath = "https://www.google.pl/maps/search/zgierz+biblioteki/@51.8557112,19.3988328,14z";
    private ProgressDialog progressDialog;

    public MapFragment(FragmentInterface mapFragment) {
        fi = mapFragment;
    }

    public MapFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.map_main, container, false);
        myWebView = (WebView) v.findViewById(R.id.mapView);
        myWebView.getSettings().setJavaScriptEnabled(true);

        createProgressDialog();
        assignWebViewLayoutForMapActivity();

        if (NetworkAvailable.isNetworkAvailable(getActivity())) {
            myWebView.loadUrl(mapPath);
        } else {
            showToast("No Network Connection!!!");
        }

        return v;
    }

    private void createProgressDialog() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.show();
    }

    private void assignWebViewLayoutForMapActivity() {
        myWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            public void onPageFinished(WebView view, String url) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }
        });
    }

    void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }
}
