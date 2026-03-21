package com.example.week10.util;

import android.content.Context;
import android.util.Log;

import com.example.week10.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JsonUtil {
    private static final String TAG = "JsonUtil";

    public static List<Movie> loadMoviesFromJson(Context context, int resourceId) throws IOException, JSONException {
        List<Movie> movies = new ArrayList<>();

        String jsonContent = readJsonFile(context, resourceId);
        JSONArray jsonArray = new JSONArray(jsonContent);

        String title;
        int year;
        String genre;
        String posterResource;
        for (int i = 0; i < jsonArray.length(); i++){
            JSONObject movie = jsonArray.getJSONObject(i);

            if (movie.has("title") && !movie.isNull("title")) {
                title = movie.getString("title");
            } else {
                Log.w(TAG, "Movie title is missing in JSON");
                title = "Placeholder Title";
            }

            if (movie.has("year") && !movie.isNull("year")) {
                try {
                    year = movie.getInt("year");
                } catch (JSONException e) {
                    ErrorHandler.logError(TAG, "Year is not an integer", e);
                    year = 0;
                }
            } else {
                Log.w(TAG, "Movie year is missing in JSON");
                year = 0;
            }

            if (movie.has("genre") && !movie.isNull("genre")) {
                genre = movie.getString("genre");
            } else {
                Log.w(TAG, "Movie genre is missing in JSON");
                genre = "Placeholder Genre";
            }

            if (movie.has("poster") && !movie.isNull("poster")) {
                posterResource = movie.getString("poster");
            } else {
                Log.w(TAG, "Movie poster is missing in JSON");
                posterResource = "Placeholder Poster";
            }

            movies.add(new Movie(title, year, genre, posterResource));

        }

        return movies;
    }

    private static String readJsonFile(Context context, int resourceId) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try (InputStream inputStream = context.getResources().openRawResource(resourceId);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            Log.e(TAG, "Error reading JSON file", e);
            throw e;
        }

        return stringBuilder.toString();
    }
}
