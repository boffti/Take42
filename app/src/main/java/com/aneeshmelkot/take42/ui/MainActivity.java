package com.aneeshmelkot.take42.ui;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.aneeshmelkot.take42.R;
import com.aneeshmelkot.take42.adapter.MovieAdapter;
import com.aneeshmelkot.take42.model.Movie;
import com.aneeshmelkot.take42.utils.NetworkUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.aneeshmelkot.take42.utils.NetworkUtil.API_KEY;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> movieList;
    ArrayList<String> movieDesc;
    ArrayList<String> releaseDate;
    List<Movie> movieComponentList;
    private RecyclerView rv;
    private GridLayoutManager gridLayoutManager;
    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = (RecyclerView)findViewById(R.id.rec_list);
        gridLayoutManager = new GridLayoutManager(this,2);
        rv.setLayoutManager(gridLayoutManager);

        movieAdapter = new MovieAdapter(this, movieComponentList);

        rv.setAdapter(movieAdapter);

        //lv = (ListView)findViewById(R.id.list_view);
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("api.themoviedb.org")
                .appendPath("3")
                .appendPath("discover")
                .appendPath("movie")
                .appendQueryParameter("page", "1")
                .appendQueryParameter("include_video", "false")
                .appendQueryParameter("include_adult", "false")
                .appendQueryParameter("sort_by", NetworkUtil.SORT_POP)
                .appendQueryParameter("language", "en-US")
                .appendQueryParameter("api_key", NetworkUtil.API_KEY);
        String myUrl = builder.build().toString();
        new DownloadTask().execute(myUrl);
    }


    public class DownloadTask extends AsyncTask<String, Void, Void> {


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
                //Log.i("Content",contentReturned);

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
                movieList = new ArrayList<>();
                movieDesc = new ArrayList<>();
                for(int i=0;i<resultsArr.length();i++) {
                    JSONObject movieObj = resultsArr.getJSONObject(i);
                    movieList.add(movieObj.getString("title"));
                    movieDesc.add(movieObj.getString("overview"));
                    releaseDate.add(movieObj.getString("release_date"));
                }
                //Log Title
                Log.i("title", Arrays.toString(movieList.toArray()));



            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
