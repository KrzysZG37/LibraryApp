package com.stepienk.libraryapp.model.sql_books;

import android.util.Log;
import android.widget.EditText;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import net.sourceforge.jtds.jdbc.*;

import info.androidhive.LibraryApp.R;

/**
 * Created by Krzysiek on 2015-05-24.
 */
public class ReserveBook {

    public void ConnectToDatabase() {
        try {

            // SET CONNECTIONSTRING
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            String username = "XXXXXXXXX";
            String password = "XXXXXX";
            Connection DbConn = DriverManager.getConnection("jdbc:jtds:sqlserver://192.188.4.83:1433/DATABASE;user=" + username + ";password=" + password);

            Log.w("Connection", "open");
            Statement stmt = DbConn.createStatement();
            ResultSet reset = stmt.executeQuery(" select * from users ");


           /* EditText num = (EditText) findViewById(R.id.displaymessage);
            num.setText(reset.getString(1));
*/
            DbConn.close();

        } catch (Exception e) {
            Log.w("Error connection", "" + e.getMessage());
        }
    }

}
