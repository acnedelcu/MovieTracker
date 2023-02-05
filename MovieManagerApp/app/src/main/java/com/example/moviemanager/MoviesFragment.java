package com.example.moviemanager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class MoviesFragment extends Fragment {
    // Declare objects
    private final ArrayList<String> moviesUrls = new ArrayList<>();
    private ArrayList<Movie> movies = new ArrayList<>();
    private View view;

    public MoviesFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Context context = getActivity();
        MoviesDataService moviesDataService = new MoviesDataService(context);
        moviesDataService.getAllMovies(new MoviesDataService.MovieListResponseListener() {
            @Override
            public void onError(String message) {
                Toast.makeText(context, "Main thread error!", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(List<Movie> allMovies) {
                movies = new ArrayList<>(allMovies);

                // Get the movies urls
                for (int i = 0; i < movies.size(); i++) {
                    moviesUrls.add(movies.get(i).getPoster());
                }
                GridView gridview = (GridView) view.findViewById(R.id.gridview);
                gridview.setAdapter(new ImageAdapter(getContext(), moviesUrls));

                gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getContext(), MovieManagerActivity.class);
                        intent.putExtra("selectedMovie", movies.get(position));
                        startActivity(intent);
                    }
                });
            }
        });

        view = inflater.inflate(R.layout.fragment_movies, container, false);

        return view;
    }
}