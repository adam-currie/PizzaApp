package com.github.adam_currie.pizzaapp.file;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;

import com.github.adam_currie.pizzaapp.file.SaveToBinaryFileTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Adam on 2016-03-09.
 */
public class DownloadFileTask extends AsyncTask{
    private Uri saveUri;
    private Context context;

    public DownloadFileTask(Uri saveUri, Context context){
        super();
        this.saveUri = saveUri;
        this.context = context;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        String urlStr = (String) params[0];

        try {
            URL url = new URL((String) urlStr);
            InputStream inStream = url.openStream();
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();

            byte[] buf = new byte[1400];
            int bytesRead;
            while((bytesRead = inStream.read(buf)) > 0){
                byteStream.write(buf, 0, bytesRead);
            }

            byte[] fileData = byteStream.toByteArray();
            inStream.close();
            byteStream.close();
            return fileData;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;//only reached in case of exception
    }

    @Override
    protected void onPostExecute(Object o) {
        if(o == null){
            return;
        }

        byte[] fileData = (byte[]) o;
        new SaveToBinaryFileTask().execute(saveUri, fileData, context);
    }
}
