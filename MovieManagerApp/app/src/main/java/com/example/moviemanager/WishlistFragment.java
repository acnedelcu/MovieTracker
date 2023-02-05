package com.example.moviemanager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WishlistFragment extends Fragment {
    // Declare objects
    private final ArrayList<Movie> wishlist = new ArrayList<>();
    private View view;
    private ListView listView = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Context context = getActivity();

        if (view == null) {
            view = inflater.inflate(R.layout.fragment_wishlist, container, false);
        } else {
            ViewGroup parent = (ViewGroup) view.getParent();
            parent.removeView(view);
        }
        MoviesDataService moviesDataService = new MoviesDataService(context);
        moviesDataService.getAllMovies(new MoviesDataService.MovieListResponseListener() {
            @Override
            public void onError(String message) {
                Toast.makeText(context, "Main thread error!", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(List<Movie> movies) {
                Toast.makeText(context, "Fetched the movie " + movies.get(0).getName(), Toast.LENGTH_LONG).show();

            }
        });

        wishlist.add(new Movie(1, "The Shawshank Redemption", new Date(1994, 07, 02), "United States", "English", "Drama", 9.3, "", "https://i.ytimg.com/vi/19THOH_dvxg/movieposter_en.jpg", "123min"));
        wishlist.add(new Movie(2, "Titanic", new Date(1994, 07, 02), "United States", "English", "Drama", 9.3, "", "https://i.ytimg.com/vi/19THOH_dvxg/movieposter_en.jpg", "123min"));

        CustomAdapter myAdapter = new CustomAdapter(getActivity(), R.layout.wishlist_view_items, wishlist);
        listView = view.findViewById(R.id.wishlistView);
        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), MovieManagerActivity.class);
                intent.putExtra("selectedMovie", wishlist.get(position));
                startActivity(intent);
            }
        });
        return view;
    }
}