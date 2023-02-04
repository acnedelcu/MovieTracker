package com.example.moviemanager;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MoviesDataService {

    private static final String API_DOMAIN_MOVIES = "https://movietracker.azurewebsites.net/Movies/";
    private final Context context;

    public MoviesDataService(Context context) {
        this.context = context;
    }


    public void addMovieToUserWatchList(int movieId, int userId, Response.Listener<String> responseListener) {
        String url = API_DOMAIN_MOVIES + "AddMovieToUserWatchList?movieId=" + movieId + "&&userId=" + userId;

        StringRequest request = new StringRequest(Request.Method.GET, url, null, null);
        RequestsSingleton.getInstance(context).addToRequestQueue(request);
    }

    public interface MovieListResponseListener {
        void onError(String message);

        void onResponse(List<Movie> movies);
    }

    public void getAllMovies(final MovieListResponseListener movieListResponseListener) {
        String url = API_DOMAIN_MOVIES + "GetAllMovies";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                List<Movie> movies = new ArrayList<Movie>();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonMovie = response.getJSONObject(i);
                        movies.add(new Movie(jsonMovie.getInt("id")
                                , jsonMovie.getString("name")
                                , new Date()
                                , jsonMovie.getString("country")
                                , jsonMovie.getString("language")
                                , jsonMovie.getString("genre")
                                , Double.parseDouble(jsonMovie.getString("rating"))
                                , jsonMovie.getString("plot")
                                , jsonMovie.getString("poster")
                                , jsonMovie.getString("runtime"))
                        );
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                movieListResponseListener.onResponse(movies);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                movieListResponseListener.onError("Something went wrong!");
            }
        });
        RequestsSingleton.getInstance(context).addToRequestQueue(request);
    }

    public void getAllWatchedMoviesOfUser(int userId, final MovieListResponseListener movieListResponseListener) {
        String url = API_DOMAIN_MOVIES + "GetAllWatchedMoviesByUserId?userId=" + userId;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                List<Movie> movies = new ArrayList<Movie>();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonMovie = response.getJSONObject(i);
                        movies.add(new Movie(jsonMovie.getInt("id")
                                , jsonMovie.getString("name")
                                , new Date()
                                , jsonMovie.getString("country")
                                , jsonMovie.getString("language")
                                , jsonMovie.getString("genre")
                                , Double.parseDouble(jsonMovie.getString("rating"))
                                , jsonMovie.getString("plot")
                                , jsonMovie.getString("poster")
                                , jsonMovie.getString("runtime"))
                        );
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                movieListResponseListener.onResponse(movies);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                movieListResponseListener.onError("Something went wrong!");
            }
        });
        RequestsSingleton.getInstance(context).addToRequestQueue(request);
    }

    public void searchMovies(String searchTerm, final MovieListResponseListener movieListResponseListener) {
        String url = API_DOMAIN_MOVIES + "SearchMovies?searchTerm=" + searchTerm; //TODO - encode special chars

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                List<Movie> movies = new ArrayList<Movie>();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonMovie = response.getJSONObject(i);
                        movies.add(new Movie(jsonMovie.getInt("id")
                                , jsonMovie.getString("name")
                                , new Date()
                                , jsonMovie.getString("country")
                                , jsonMovie.getString("language")
                                , jsonMovie.getString("genre")
                                , Double.parseDouble(jsonMovie.getString("rating"))
                                , jsonMovie.getString("plot")
                                , jsonMovie.getString("poster")
                                , jsonMovie.getString("runtime"))
                        );
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                movieListResponseListener.onResponse(movies);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                movieListResponseListener.onError("Something went wrong!");
            }
        });
        RequestsSingleton.getInstance(context).addToRequestQueue(request);
    }


    public interface MovieResponseListener {
        void onError(String message);

        void onResponse(Movie movie);
    }

    public void getMovieById(int movieId, final MovieResponseListener movieResponseListener) {
        String url = API_DOMAIN_MOVIES + "GetMovieById?movieId=" + movieId;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //Movie movie = null;
                try {
                    Movie movie = new Movie(response.getInt("id")
                            , response.getString("name")
                            , new Date() //TODO-fix date parsing
                            //,getParsedDateFromString(response.getString("releaseDate"))
                            , response.getString("country")
                            , response.getString("language")
                            , response.getString("genre")
                            , Double.parseDouble(response.getString("rating"))
                            , response.getString("plot")
                            , response.getString("poster")
                            , response.getString("runtime"));

                    movieResponseListener.onResponse(movie);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                movieResponseListener.onError("Something went wrong!");
            }
        });
        RequestsSingleton.getInstance(context).addToRequestQueue(request);
    }


    public void removeMovieFromUserWatchlist(int movieId, int userId, Response.Listener<String> responseListener) {
        String url = API_DOMAIN_MOVIES + "RemoveMovieFromWatchListByUserId?movieId=" + movieId + "&&userId=" + userId;

        StringRequest request = new StringRequest(Request.Method.GET, url, null, null);
        RequestsSingleton.getInstance(context).addToRequestQueue(request);
    }

    private Date getParsedDateFromString(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");
        try {
            Date parsedDate = formatter.parse(date);
            return parsedDate;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
