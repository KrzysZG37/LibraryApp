package com.stepienk.libraryapp.model.user;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by Krzysiek on 2015-06-06.
 */
public class User {
    private Context mContext;
    private static final int READ_BLOCK_SIZE = 100;
    private String name = "";
    public static String nameForData = "";

    public User(Context context) {
        this.mContext = context;
    }

    /**
     * Saves name of the user into text file
     * Then this field is accessed in Books section
     *
     * @param name String with name of the user
     */
    public void writeUserNameToFile(String name) {
        try {
            FileOutputStream fileout = mContext.openFileOutput("mytextfile.txt", mContext.MODE_PRIVATE);
            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
            outputWriter.write(name);
            outputWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads name of the user from text file
     * Then this field is accessed in Books section
     */
    public void readUserNameFromFile() {
        String s = "";
        try {
            FileInputStream fileIn = mContext.openFileInput("mytextfile.txt");
            InputStreamReader InputRead = new InputStreamReader(fileIn);
            char[] inputBuffer = new char[READ_BLOCK_SIZE];
            int charRead;

            while ((charRead = InputRead.read(inputBuffer)) > 0) {
                String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                s += readstring;
            }
            InputRead.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        name = s;
        nameForData = s;
    }

    public String getUserName() {
        return name;
    }

}
