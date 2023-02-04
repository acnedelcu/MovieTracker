package com.example.moviemanager;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CinemaDataService {
    private static final String API_DOMAIN_MOVIES = "https://movietracker.azurewebsites.net/Cinemas/";
    private final Context context;

    public CinemaDataService(Context context) {
        this.context = context;
    }


    public interface CinemaListResponseListener {
        void onError(String message);

        void onResponse(List<Cinema> cinemas);
    }

    public void getAllCinemas(final CinemaListResponseListener cinemaListResponseListener) {
        String url = API_DOMAIN_MOVIES + "GetAllCinemas";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                List<Cinema> cinemas = new ArrayList<>();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        cinemas.add(new Cinema(jsonObject.getInt("id"), jsonObject.getString("name"), jsonObject.getString("geolocation")));

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                cinemaListResponseListener.onResponse(cinemas);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                cinemaListResponseListener.onError("Something went wrong!");
            }
        });
    }

    public void getAllMoviesInCinema(int cinemaId, final MoviesDataService.MovieListResponseListener movieListResponseListener) {
        String url = API_DOMAIN_MOVIES + "GetAllMoviesInCinema?cinemaId=" + cinemaId;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                List<Movie> movies = new ArrayList<>();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        movies.add(new Movie(jsonObject.getInt("id")
                                , jsonObject.getString("name")
                                , new Date()
                                , jsonObject.getString("country")
                                , jsonObject.getString("language")
                                , jsonObject.getString("genre")
                                , Double.parseDouble(jsonObject.getString("rating"))
                                , jsonObject.getString("plot")
                                , jsonObject.getString("poster")
                                , jsonObject.getString("runtime"))
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
    }


    public interface CinemaResponseListener {
        void onError(String message);

        void onResponse(Cinema cinemas);
    }

    public void getCinemaById(int cinemaId, final CinemaResponseListener cinemaResponseListener) {
        String url = API_DOMAIN_MOVIES + "GetCinemaById?cinemaId=" + cinemaId;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Cinema cinema = new Cinema(response.getInt("id"), response.getString("name"), response.getString("geolocation"));
                    cinemaResponseListener.onResponse(cinema);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                cinemaResponseListener.onError("Something went wrong!");
            }
        });
    }
}
