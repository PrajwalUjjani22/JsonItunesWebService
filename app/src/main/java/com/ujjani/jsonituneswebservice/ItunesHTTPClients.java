package com.ujjani.jsonituneswebservice;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.jar.JarException;

public class ItunesHTTPClients {

    private static String BASE_URL = "https://itunes.apple.com/search?term=michael+jackson";



    public String getItunesStuffData() {

        HttpURLConnection httpURLConnection = null;

        InputStream inputStream = null;

        try {

            httpURLConnection = (HttpURLConnection) (new URL(BASE_URL)).openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoInput(true);
            // httpURLConnection.setDoOutput(true); it is used to upload something to the internet
            httpURLConnection.connect();

            //Reading the response
            StringBuffer stringBuffer = new StringBuffer();
            inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line + "\n");
            }

            inputStream.close();
            httpURLConnection.disconnect();

            return stringBuffer.toString();

        }catch (Throwable t){
            t.printStackTrace();
        }
        finally {
            try{
                inputStream.close();
                httpURLConnection.disconnect();
            }catch (Throwable t){
                t.printStackTrace();
            }
        }
        return null;

    }

    //Download image from the internet

    public Bitmap getBitmapFromURL(String stringURL){
        Bitmap bitmap = null;
        try{
            URL url = new URL(stringURL);
            InputStream inputStream = url.openStream();
            bitmap = BitmapFactory.decodeStream(inputStream);

        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
        return bitmap;
    }

}
