package com.stepienk.libraryapp.view.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.stepienk.libraryapp.view.main.MainActivity;

import info.androidhive.LibraryApp.R;

import com.stepienk.libraryapp.model.session.SessionManager;
import com.stepienk.libraryapp.model.sql_login.SQLiteHandler;

import java.util.HashMap;

/**
 * Created by Krzysiek on 2015-03-30.
 */
public class WelcomeActivity extends Activity {

    private TextView txtName;
    private TextView txtEmail;
    private Button btnLogout;
    private Button btnNews;

    private SQLiteHandler db;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_welcome);


        db = new SQLiteHandler(getApplicationContext());
        session = new SessionManager(getApplicationContext());
        Bundle b = getIntent().getExtras();

        if (!session.isLoggedIn()) {
            logoutUser();
        }
        // Fetching user details from sqLite
        HashMap<String, String> user = db.getUserDetails();
        assignControlsForWelcomeActivity(b);
    }

    public void assignControlsForWelcomeActivity(Bundle b) {
        txtName = (TextView) findViewById(R.id.name);
        txtEmail = (TextView) findViewById(R.id.email);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnNews = (Button) findViewById(R.id.btnNews);
        String name = "";

        if (b != null) {
            name = b.getString("user_name");
        }

        // Displaying the user details on the screen
        txtName.setText(name);

        // Logout button click event
        btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });
        btnNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
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
        Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }


}
