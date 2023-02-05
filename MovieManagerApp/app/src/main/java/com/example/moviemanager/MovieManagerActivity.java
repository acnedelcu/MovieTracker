package com.example.moviemanager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Response;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MovieManagerActivity extends AppCompatActivity {

    // Variables
    ImageView posterImageView;
    Button addToWatchedListBtn;
    TextView nameTextView, yearTextView, countryTextView, genreTextView, ratingTextView, runtimeTextView, languageTextView, releaseTextView, plotTextView;
    Movie movie;
    ArrayList<Movie> watchedMovies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_manager);
        MoviesDataService moviesDataService = new MoviesDataService(getApplicationContext());

        posterImageView = findViewById(R.id.posterImageView);
        addToWatchedListBtn = findViewById(R.id.watchedListButton);
        nameTextView = findViewById(R.id.nameTextView);
        yearTextView = findViewById(R.id.yearTextView);
        countryTextView = findViewById(R.id.countryTextView);
        genreTextView = findViewById(R.id.genreTextView);
        ratingTextView = findViewById(R.id.ratingTextView);
        runtimeTextView = findViewById(R.id.runtimeTextView);
        languageTextView = findViewById(R.id.languageTextView);
        releaseTextView = findViewById(R.id.releaseDateTextView);
        plotTextView = findViewById(R.id.plotTextView);

        // Get the movie from intent
        movie = (Movie) getIntent().getSerializableExtra("selectedMovie");

        // Get the release date and convert to calendar
        Date releaseDate = movie.getReleaseDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(releaseDate);

        // Set textViews fields
        nameTextView.setText(movie.getName());
        // TODO: Update year and date and picture
        //yearTextView.setText(calendar.get(Calendar.YEAR));
        countryTextView.setText(countryTextView.getText() + movie.getCountry());
        genreTextView.setText(movie.getGenre());
        ratingTextView.setText(movie.getRating().toString());
        runtimeTextView.setText(movie.getRuntime());
        languageTextView.setText(languageTextView.getText() + movie.getLanguage());
        releaseTextView.setText(releaseTextView.getText() + Integer.toString(calendar.get(Calendar.DATE)));
        if (!movie.getPlot().isEmpty()) {
            plotTextView.setText(plotTextView.getText() + movie.getPlot());
        } else {
            plotTextView.setText(plotTextView.getText() + "-");
        }
        Picasso.get().load(movie.getPoster()).into(posterImageView);

        moviesDataService.getAllWatchedMoviesOfUser(1, new MoviesDataService.MovieListResponseListener() {
            @Override
            public void onError(String message) {
                Toast.makeText(MovieManagerActivity.this, "Main thread error!", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(List<Movie> movies) {
                watchedMovies = new ArrayList<>(movies);
                if (watchedMovies.contains(movie)) {
                    addToWatchedListBtn.setText("Remove from watched");
                } else {
                    addToWatchedListBtn.setText("Add to watched");
                }

                addToWatchedListBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Search if the movie is watched
                        if (watchedMovies.contains(movie)) {
                            moviesDataService.removeMovieFromUserWatchlist(movie.getId(), 1, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    addToWatchedListBtn.setText("Add to watched");
                                }
                            });
                        } else {
                            moviesDataService.addMovieToUserWatchList(3, 1, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    addToWatchedListBtn.setText("Remove from watched");
                                }
                            });
                        }
                    }
                });
            }
        });
    }
}