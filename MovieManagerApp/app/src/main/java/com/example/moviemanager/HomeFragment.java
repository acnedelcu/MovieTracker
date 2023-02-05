package com.example.moviemanager;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class HomeFragment extends Fragment {
    // Declare objects
    private ArrayList<Movie> movies = new ArrayList<>();
    private View view;

    public HomeFragment() {
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
                Collections.sort(movies, (m1, m2) -> Double.compare(m2.getRating(), m1.getRating()));

                ImageView imageView1 = view.findViewById(R.id.homeImageView1);
                Picasso.get().load(movies.get(0).getPoster()).into(imageView1);

                TextView textView1 = view.findViewById(R.id.homeTextView1);
                textView1.setText(movies.get(0).getName() + " - " + movies.get(0).getRating().toString());

                CardView cardView1 = view.findViewById(R.id.card_view1);
                cardView1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO change all this text with open the movie details activity
                        Fragment movieFragment = new MoviesFragment();
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, movieFragment);
                        transaction.addToBackStack(null);
                        transaction.commit();
                        MainActivity mainActivity = (MainActivity) getActivity();
                        mainActivity.bottomNavigationView.setSelectedItemId(R.id.movies);
                    }
                });

                ImageView imageView2 = view.findViewById(R.id.homeImageView2);
                Picasso.get().load(movies.get(1).getPoster()).into(imageView2);

                TextView textView2 = view.findViewById(R.id.homeTextView2);
                textView2.setText(movies.get(1).getName() + " - " + movies.get(1).getRating().toString());

                CardView cardView2 = view.findViewById(R.id.card_view2);
                cardView2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO change all this text with open the movie details activity
                        Fragment movieFragment = new MoviesFragment();
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, movieFragment);
                        transaction.addToBackStack(null);
                        transaction.commit();
                        MainActivity mainActivity = (MainActivity) getActivity();
                        mainActivity.bottomNavigationView.setSelectedItemId(R.id.movies);
                    }
                });

                ImageView imageView3 = view.findViewById(R.id.homeImageView3);
                Picasso.get().load(movies.get(2).getPoster()).into(imageView3);

                TextView textView3 = view.findViewById(R.id.homeTextView3);
                textView3.setText(movies.get(2).getName() + " - " + movies.get(2).getRating().toString());

                CardView cardView3 = view.findViewById(R.id.card_view3);
                cardView3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO change all this text with open the movie details activity
                        Fragment movieFragment = new MoviesFragment();
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, movieFragment);
                        transaction.addToBackStack(null);
                        transaction.commit();
                        MainActivity mainActivity = (MainActivity) getActivity();
                        mainActivity.bottomNavigationView.setSelectedItemId(R.id.movies);
                    }
                });

                Button goToMovies = view.findViewById(R.id.goToMoviesBtn);
                goToMovies.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Fragment movieFragment = new MoviesFragment();
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, movieFragment);
                        transaction.addToBackStack(null);
                        transaction.commit();
                        MainActivity mainActivity = (MainActivity) getActivity();
                        mainActivity.bottomNavigationView.setSelectedItemId(R.id.movies);
                    }
                });
            }
        });

        view = inflater.inflate(R.layout.fragment_home, container, false);

        return view;
    }
}