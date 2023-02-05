package com.example.moviemanager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;


public class HomeFragment extends Fragment {
    // Declare objects
    private final ArrayList<Movie> movies = new ArrayList<>();
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
        view = inflater.inflate(R.layout.fragment_home, container, false);

        movies.add(new Movie(1, "The Shawshank Redemption", new Date(1994, 07, 02), "United States", "English", "Drama", 9.3, "", "https://i.ytimg.com/vi/19THOH_dvxg/movieposter_en.jpg", "123min"));

        ImageView imageView1 = view.findViewById(R.id.homeImageView1);
        Picasso.get().load(movies.get(0).getPoster()).into(imageView1);

        TextView textView1 = view.findViewById(R.id.homeTextView1);
        textView1.setText(movies.get(0).getName());

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
        Picasso.get().load(movies.get(0).getPoster()).into(imageView2);

        TextView textView2 = view.findViewById(R.id.homeTextView2);
        textView2.setText(movies.get(0).getName());

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
        Picasso.get().load(movies.get(0).getPoster()).into(imageView3);

        TextView textView3 = view.findViewById(R.id.homeTextView3);
        textView3.setText(movies.get(0).getName());

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

        return view;
    }
}