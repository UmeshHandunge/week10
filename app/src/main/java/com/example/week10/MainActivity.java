package com.example.week10;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.week10.adapter.MovieAdapter;
import com.example.week10.model.Movie;
import com.example.week10.util.ErrorHandler;
import com.example.week10.util.JsonUtil;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private RecyclerView movieRecyclerView;
    private List<Movie> movies;
    private MovieAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        initializeViews();
        loadMovieData();
    }

    private void initializeViews(){
        movieRecyclerView = findViewById(R.id.recyclerViewMenu);
        movieRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        movieRecyclerView.setHasFixedSize(true);
    }

    private void loadMovieData(){
        try {
            movies = JsonUtil.loadMoviesFromJson(this, R.raw.movies);
            adapter = new MovieAdapter(this, movies);
            movieRecyclerView.setAdapter(adapter);
        } catch (Exception e) {
            ErrorHandler.handleError(this, e, "Failed to load Movie data");
        }
    }
}