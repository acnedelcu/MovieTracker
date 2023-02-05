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
import java.util.List;

public class WishlistFragment extends Fragment {
    // Declare objects
    private ArrayList<Movie> watchedlist = new ArrayList<>();
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
        moviesDataService.getAllWatchedMoviesOfUser(1, new MoviesDataService.MovieListResponseListener() {
            @Override
            public void onError(String message) {
                Toast.makeText(context, "Main thread error!", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(List<Movie> allWatchedMovies) {
                watchedlist = new ArrayList<>(allWatchedMovies);

                CustomAdapter myAdapter = new CustomAdapter(getActivity(), R.layout.wishlist_view_items, watchedlist);
                listView = view.findViewById(R.id.wishlistView);
                listView.setAdapter(myAdapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getContext(), MovieManagerActivity.class);
                        intent.putExtra("selectedMovie", watchedlist.get(position));
                        startActivity(intent);
                    }
                });
            }
        });
        return view;
    }
}