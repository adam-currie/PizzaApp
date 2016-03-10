package com.github.adam_currie.pizzaapp;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.ParcelFileDescriptor;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * Created by Adam on 2016-03-09.
 */
public class SaveToBinaryFileTask extends AsyncTask {

    @Override
    protected Object doInBackground(Object[] params) {
        Uri uri = (Uri) params[0];
        byte[] data = (byte[]) params[1];
        Context context = (Context) params[2];

        try {
            ParcelFileDescriptor parcelFile = context.getContentResolver().openFileDescriptor(uri, "w");
            FileOutputStream fileOut = new FileOutputStream(parcelFile.getFileDescriptor());
            fileOut.write(data);
            fileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
