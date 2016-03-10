package com.github.adam_currie.pizzaapp;

import android.util.Log;
import android.content.Context;
import android.os.AsyncTask;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * Created by Adam on 2016-03-09.
 */
public class SaveToFileTask extends AsyncTask{

    @Override
    protected Object doInBackground(Object[] params) {
        OutputStream outStream = (OutputStream) params[0];
        String data = (String) params[1];///text to save
        Context context = (Context) params[2];

        try {
            OutputStreamWriter fileOut = new OutputStreamWriter(outStream);
            fileOut.write(data);
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
