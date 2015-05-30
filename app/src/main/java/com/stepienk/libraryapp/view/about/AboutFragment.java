package com.stepienk.libraryapp.view.about;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.stepienk.libraryapp.model.session.SessionManager;
import com.stepienk.libraryapp.model.sql_login.SQLiteHandler;
import com.stepienk.libraryapp.view.fragments_decorator.FragmentDecorator;
import com.stepienk.libraryapp.view.fragments_decorator.FragmentInterface;
import com.stepienk.libraryapp.view.login.LoginActivity;

import info.androidhive.LibraryApp.R;

public class AboutFragment extends FragmentDecorator {

    private Button btnLogout;
    private SQLiteHandler db;
    private SessionManager session;

    public AboutFragment(FragmentInterface aboutFragment) {
        fi = aboutFragment;
    }

    public AboutFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        RelativeLayout mLinearLayout = (RelativeLayout) inflater.inflate(R.layout.about,
                container, false);

        db = new SQLiteHandler(getActivity());
        session = new SessionManager(getActivity());
        assignLayoutForAboutActivity(mLinearLayout);

        return mLinearLayout;
    }

    private void assignLayoutForAboutActivity(RelativeLayout mLinearLayout) {
        btnLogout = (Button) mLinearLayout.findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });
    }

    /**
     * Logging out the user. Will set isLoggedIn flag to false in shared
     * preferences Clears the user data from sqLite users table
     */
    private void logoutUser() {
        session.setLogin(false);
        db.deleteUsers();

        // Launching the login activity
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

}