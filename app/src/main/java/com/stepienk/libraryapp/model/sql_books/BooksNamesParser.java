package com.stepienk.libraryapp.model.sql_books;

import android.util.Log;

import com.stepienk.libraryapp.model.interfaces.booksNamesParserInterface;
import com.stepienk.libraryapp.model.network.AppConfig;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by stepienk on 2015-04-07.
 */
public class BooksNamesParser implements booksNamesParserInterface {

    private HttpEntity httpEntity;
    private JSONObject jsonObject;
    private int tagForTest = 0;
    private String url = AppConfig.URL_GET_DATA;

    /**
     * Function for returning all Books in JSONArray object
     * Retrieve URL to take all data from server
     * Take HttpResponse Object from url and then get HttpEntity from Http Response Object
     * Finally convert HttpEntity into JSON Array
     *
     * @return
     */
    public JSONArray getAllBooks() {
        if (tagForTest == 0) {
            httpEntity = downloadBooks();
        } else {
        }

        // Convert HttpEntity into JSON Array
        JSONArray jsonArray = null;

        if (httpEntity != null) {
            try {
                String entityResponse = EntityUtils.toString(httpEntity);
                Log.e("Entity Response  : ", entityResponse);
                jsonArray = new JSONArray(entityResponse);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                jsonArray = new JSONArray(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


        return jsonArray;
    }

    /**
     * Function for downloading all Books from server using specified url
     *
     * @return
     */
    public HttpEntity downloadBooks() {
        HttpEntity httpEntity = null;

        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();  // Default HttpClient
            HttpGet httpGet = new HttpGet(url);

            HttpResponse httpResponse = httpClient.execute(httpGet);
            httpEntity = httpResponse.getEntity();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return httpEntity;
    }


    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public void setTagForTest(int tag) {
        this.tagForTest = tag;
    }
}
