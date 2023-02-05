package com.example.moviemanager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class WatchedListFragment extends Fragment {
    // Declare objects
    private ArrayList<Movie> watchedList = new ArrayList<>();
    private View view;
    private ListView listView = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Context context = getActivity();

        if (view == null) {
            view = inflater.inflate(R.layout.fragment_watchedlist, container, false);
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
                watchedList = new ArrayList<>(allWatchedMovies);

                CustomAdapter myAdapter = new CustomAdapter(getActivity(), R.layout.watched_view_items, watchedList);
                listView = view.findViewById(R.id.watchedListView);
                listView.setAdapter(myAdapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getContext(), MovieManagerActivity.class);
                        intent.putExtra("selectedMovie", watchedList.get(position));
                        startActivity(intent);
                    }
                });
            }
        });
        return view;
    }
}