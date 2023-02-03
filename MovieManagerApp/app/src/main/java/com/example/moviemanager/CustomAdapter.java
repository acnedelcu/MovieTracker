package com.example.moviemanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Movie> {
    ArrayList<Movie> moviesList = new ArrayList<Movie>();

    public CustomAdapter(Context context, int textViewResourceId, ArrayList<Movie> objects) {
        super(context, textViewResourceId, objects);
        moviesList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.wishlist_view_items, null);

        TextView textView = view.findViewById(R.id.textView);
        ImageView imageView = view.findViewById(R.id.imageView);

        textView.setText(moviesList.get(position).getName());

        String imageUrl = moviesList.get(position).getPoster();
        Picasso.get().load(imageUrl).into(imageView);

        return view;
    }
}
