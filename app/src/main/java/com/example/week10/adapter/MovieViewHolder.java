package com.example.week10.adapter;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.week10.R;
import com.example.week10.model.Movie;

import org.w3c.dom.Text;

public class MovieViewHolder {

    public static class ItemViewHolder extends RecyclerView.ViewHolder{
        private final ImageView posterImageView;
        private final TextView titleTextView;
        private final TextView yearTextView;
        private final TextView genreTextview;


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            posterImageView = itemView.findViewById(R.id.imageViewPoster);
            titleTextView = itemView.findViewById(R.id.textViewMovieTitle);
            yearTextView = itemView.findViewById(R.id.textViewItemReleaseYear);
            genreTextview = itemView.findViewById(R.id.textViewMovieGenre);
        }

        public ImageView getPosterImageView() {return posterImageView;}

        public void bind(Movie movie){
            String titleString = "Title: " + movie.getTitle();
            String yearString = "Year: " + String.valueOf(movie.getYear());
            String genreString = "Genre: " + movie.getGenre();
            titleTextView.setText(titleString);
            yearTextView.setText(yearString);
            genreTextview.setText(genreString);
        }
    }

}
