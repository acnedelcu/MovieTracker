package com.example.moviemanager;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.moviemanager.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    public BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.watchedlist:
                    replaceFragment(new WatchedListFragment());
                    break;
                case R.id.movies:
                    replaceFragment(new MoviesFragment());
                    break;
                case R.id.cinemas:
                    replaceFragment(new CinemasFragment());
                    MoviesDataService moviesDataService = new MoviesDataService(MainActivity.this);

                    //example code for GetMovieById
//                    moviesDataService.getMovieById(1, new MoviesDataService.MovieResponseListener() {
//                        @Override
//                        public void onError(String message) {
//                            Toast.makeText(MainActivity.this, "Main thread error!", Toast.LENGTH_LONG).show();
//                        }
//
//                        @Override
//                        public void onResponse(Movie movie) {
//                            Toast.makeText(MainActivity.this, "Fetched the movie " + movie.getName(), Toast.LENGTH_LONG).show();
//                        }
//                    });

                    // example code for GetAllWatchedMoviesOfUser
//                    moviesDataService.getAllWatchedMoviesOfUser(1, new MoviesDataService.MovieListResponseListener() {
//                        @Override
//                        public void onError(String message) {
//                            Toast.makeText(MainActivity.this, "Main thread error!", Toast.LENGTH_LONG).show();
//                        }
//
//                        @Override
//                        public void onResponse(List<Movie> movies) {
//                            Toast.makeText(MainActivity.this, "Fetched the movie " + movies.get(1).getName(), Toast.LENGTH_LONG).show();
//                        }
//                    });

                    //example of addMovieToUserWatchList
//                    moviesDataService.addMovieToUserWatchList(3, 1, new Response.Listener<String>() {
//                        @Override
//                        public void onResponse(String response) {
//
//                        }
//                    });

                    //example of removeMovieFromUserWatchList
//                    moviesDataService.removeMovieFromUserWatchlist(3, 1, new Response.Listener<String>() {
//                        @Override
//                        public void onResponse(String response) {
//                            Toast.makeText(MainActivity.this, "Successfully removed from watchlist!", Toast.LENGTH_LONG).show();
//                        }
//                    });
                    break;

            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}