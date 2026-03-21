package com.example.week10.model;

public class Movie {
    private  String title;
    private int year;
    private String genre;
    private String posterResource;

    public Movie(String title, int year, String genre, String posterResource) {
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.posterResource = posterResource;
    }

    public String getTitle() {
        return this.title;
    }

    public Integer getYear() {
        return this.year;
    }

    public String getGenre() {
        return this.genre;
    }

    public String getPosterResource() {
        return this.posterResource;
    }
}
