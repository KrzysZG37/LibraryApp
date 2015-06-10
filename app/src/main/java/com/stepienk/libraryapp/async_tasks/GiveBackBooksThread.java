package com.stepienk.libraryapp.async_tasks;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Created by Krzysiek on 2015-06-10.
 */
public class GiveBackBooksThread extends Thread {

    private HttpClient client = new DefaultHttpClient();
    private String urlToGiveBackBook;


    /*
    Runs HttpClient to return specified book
     */
    @Override
    public void run() {
        try {
            client.execute(new HttpGet(urlToGiveBackBook));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setUrlToGiveBackBook(String url) {
        this.urlToGiveBackBook = url;
    }

}
