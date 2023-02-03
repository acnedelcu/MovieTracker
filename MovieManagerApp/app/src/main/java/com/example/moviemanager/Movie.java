package com.example.moviemanager;

import java.io.Serializable;
import java.util.Date;

public class Movie implements Serializable {
    private final int id;
    private final String name;
    private final Date releaseDate;
    private final String country;
    private final String language;
    private final String genre;
    private final Double rating;
    private final String plot;
    private final String poster;
    private final String runtime;

    public Movie(int id, String name, Date releaseDate, String country, String language, String genre, Double rating, String plot, String poster, String runtime) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.country = country;
        this.language = language;
        this.genre = genre;
        this.rating = rating;
        this.plot = plot;
        this.poster = poster;
        this.runtime = runtime;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public String getCountry() {
        return country;
    }

    public String getLanguage() {
        return language;
    }

    public String getGenre() {
        return genre;
    }

    public Double getRating() {
        return rating;
    }

    public String getPlot() {
        return plot;
    }

    public String getPoster() {
        return poster;
    }

    public String getRuntime() {
        return runtime;
    }
}
