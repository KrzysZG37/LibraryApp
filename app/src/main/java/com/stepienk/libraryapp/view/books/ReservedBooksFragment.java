package com.stepienk.libraryapp.view.books;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stepienk.libraryapp.view.fragments_decorator.FragmentDecorator;
import com.stepienk.libraryapp.view.fragments_decorator.FragmentInterface;

import info.androidhive.LibraryApp.R;

/**
 * Created by Krzysiek on 2015-04-07.
 */
public class ReservedBooksFragment extends FragmentDecorator {

    public ReservedBooksFragment() {
    }

    public ReservedBooksFragment(FragmentInterface reservedBooksFragment) {
        fi = reservedBooksFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.books_reserved, container, false);

        return rootView;
    }
}
