package com.example.week10.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.week10.R;
import com.example.week10.model.Movie;
import com.example.week10.util.ErrorHandler;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Movie> movies;
    private final Context context;

   public MovieAdapter(Context context, List<Movie> movies) {
       this.movies = movies;
       this.context = context;
   }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.movie_cards, parent, false);
        return new MovieViewHolder.ItemViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        try {
            int viewType = getItemViewType(position);
            Movie movie = this.movies.get(position);

            ((MovieViewHolder.ItemViewHolder) holder).bind(movie);
        } catch (Exception e) {
            ErrorHandler.logError("MenuAdapter", "Error binding view at position" + position, e);
        }

        Movie movie = this.movies.get(position);

        try {
            int imageResourceId = ErrorHandler.getDrawableResourceId(
                    context, movie.getPosterResource(), android.R.drawable.ic_menu_gallery);
            ((MovieViewHolder.ItemViewHolder) holder).getPosterImageView().setImageResource(imageResourceId);
        } catch (Exception e) {
            // If all else fails, set a background color
            ((MovieViewHolder.ItemViewHolder) holder).getPosterImageView().setBackgroundColor(
                    context.getResources().getColor(android.R.color.darker_gray));
            ErrorHandler.logError("MenuAdapter", "Error loading image", e);
        }
    }

    @Override
    public int getItemCount() {
        return this.movies.size();
    }
}
