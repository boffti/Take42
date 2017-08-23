package com.aneeshmelkot.take42.adapter;

import android.app.LauncherActivity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aneeshmelkot.take42.R;
import com.aneeshmelkot.take42.model.Movie;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by melkoa on 13-Jan-17.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder>{
    private Context context;
    private List<Movie> movieData;

    public MovieAdapter(Context context, List<Movie> movieData) {
        this.context = context;
        this.movieData = movieData;
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return movieData.size();
    }

    class MovieHolder extends RecyclerView.ViewHolder {
        private TextView movieTitle;
        private ImageView icon;
        private TextView movieOverview;
        private TextView releaseDate;
        private View container;

        public MovieHolder(View itemView) {
            super(itemView);

        }
    }
}
