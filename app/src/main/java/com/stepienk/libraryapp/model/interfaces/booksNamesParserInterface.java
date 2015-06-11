package com.stepienk.libraryapp.model.interfaces;

import org.apache.http.HttpEntity;
import org.json.JSONArray;

/**
 * Created by Krzysiek on 2015-05-04.
 */
public interface booksNamesParserInterface {
    public JSONArray getAllBooks();
    public HttpEntity downloadBooks();
}
