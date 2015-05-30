package com.stepienk.libraryapp.model.interfaces;

import java.util.List;

/**
 * Created by Krzysiek on 2015-05-04.
 */
public interface dropBoxNamesParserInterface<T> {
    public List<T> getData(String url);
}
