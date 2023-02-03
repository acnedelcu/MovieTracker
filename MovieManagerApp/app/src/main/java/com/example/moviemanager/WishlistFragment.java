package com.example.moviemanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Date;

public class WishlistFragment extends Fragment {

    private final ArrayList<Movie> wishlist = new ArrayList<>();
    // Declare objects
    private View view;
    private ListView listView = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (view == null) {
            view = inflater.inflate(R.layout.fragment_wishlist, container, false);
        } else {
            ViewGroup parent = (ViewGroup) view.getParent();
            parent.removeView(view);
        }
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