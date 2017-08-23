package com.aneeshmelkot.take42.utils;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by melkoa on 06-Jan-17.
 */

public class DownloadTask extends AsyncTask <String, Void, Void> {

    final private String BASE_URL = "https://api.themoviedb.org/3/discover/movie?page=10&include_video=false&include_adult=false";
    final private String API_KEY = "&api_key=a1141af604017894e4840fdf74f418f4";
    final private String SORT_POP = "&sort_by=popularity.desc";
    final private String SORT_TR = "&sort_by=vote_average.desc";
    final private String LANGUAGE = "&language=en-US";
    private URL url;
    private URLConnection conn;
    private String contentReturned;
    private BufferedReader reader = null;

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected Void doInBackground(String... urls) {


        try {

            //Defined URL
            url = new URL(urls[0]);
            conn = url.openConnection();
            conn.setDoOutput(true);
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;

            //Read Server Response
            while ((line=reader.readLine())!=null) {
                //Append Server response to String
                sb.append(line+"");
            }
            //Append Server Response to COntent String
            contentReturned = sb.toString();
            Log.i("Content",contentReturned);

        } catch (Exception e) {
            e.printStackTrace();
        }

        finally {
            try {
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        try {

            JSONObject jsonObject = new JSONObject(contentReturned);
            String results = jsonObject.getString("results");
            JSONArray resultsArr = new JSONArray(results);
            for(int i=0;i<resultsArr.length();i++) {
                JSONObject movieObj = resultsArr.getJSONObject(i);
                //Log Title
                Log.i("Movie Title ", movieObj.getString("title"));

                //Log Description
                Log.i("Movie Description ", movieObj.getString("overview"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
