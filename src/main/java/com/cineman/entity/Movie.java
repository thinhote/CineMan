// Movie.java
package com.cineman.entity;

public class Movie {
    private int movieId;
    private String title;
    private String genre;
    private String duration;     // ví dụ "125" phút hoặc "2h05m"
    private String description;
    private String year;
    private String cast;
    private String director;

    public Movie() {}

    public int getMovieId() {
        return movieId;
    }
    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public String getDuration() {
        return duration;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getYear() { return year;}
    public void setYear(String year) {this.year = year;}
    public String getCast() { return cast;}
    public void setCast(String cast) {this.cast = cast;}
    public String getDirector() { return director;}
    public void setDirector(String director) {this.director = director;}
}
