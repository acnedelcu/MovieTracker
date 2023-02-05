package com.example.moviemanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Date;

public class MoviesFragment extends Fragment {
    // Declare objects
    private final ArrayList<Movie> movies = new ArrayList<>();
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
        movies.add(new Movie(1, "The Shawshank Redemption", new Date(1994, 07, 02), "United States", "English", "Drama", 9.3, "", "https://i.ytimg.com/vi/19THOH_dvxg/movieposter_en.jpg", "123min"));
        movies.add(new Movie(2, "Titanic", new Date(1994, 07, 02), "United States", "English", "Drama", 9.3, "", "https://i.ytimg.com/vi/19THOH_dvxg/movieposter_en.jpg", "123min"));

        view = inflater.inflate(R.layout.fragment_movies, container, false);

        GridView gridview = (GridView) view.findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(getContext()));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), MovieManagerActivity.class);
                intent.putExtra("selectedMovie", movies.get(position));
                startActivity(intent);
            }
        });

        return view;
    }
}