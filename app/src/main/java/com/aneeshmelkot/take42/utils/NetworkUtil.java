package com.aneeshmelkot.take42.utils;

/**
 * Created by melkoa on 13-Jan-17.
 */

public class NetworkUtil {

    public final static String BASE_URL = "https://api.themoviedb.org/3/discover/movie?page=1&include_video=false&include_adult=false";
    public final static String API_KEY = "a1141af604017894e4840fdf74f418f4";
    public final static String SORT_POP = "&sort_by=popularity.desc";
    public final static String SORT_TR = "&sort_by=vote_average.desc";
    public final static String LANGUAGE = "&language=en-US";
    public final static String POSTER_PATH_BASE = "http://image.tmdb.org/t/p/";


    public String getBASE_URL() {
        return BASE_URL;
    }

    public String getAPI_KEY() {
        return API_KEY;
    }

    public String getSORT_POP() {
        return SORT_POP;
    }

    public String getSORT_TR() {
        return SORT_TR;
    }

    public String getLANGUAGE() {
        return LANGUAGE;
    }
}
