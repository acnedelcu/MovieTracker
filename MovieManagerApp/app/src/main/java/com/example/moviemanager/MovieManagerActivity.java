package com.example.moviemanager;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.Date;

public class MovieManagerActivity extends AppCompatActivity {

    // Variables
    ImageView posterImageView;
    Button addToWatchedListBtn;
    TextView nameTextView, yearTextView, countryTextView, genreTextView, ratingTextView, runtimeTextView, languageTextView, releaseTextView, plotTextView;
    Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_manager);

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
    }
}