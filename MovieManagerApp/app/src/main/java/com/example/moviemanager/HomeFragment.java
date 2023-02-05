package com.example.moviemanager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


public class HomeFragment extends Fragment {
    // Declare objects
    private View view;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);


        Button goToMovies = view.findViewById(R.id.goToMoviesBtn);

        goToMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment movieFragment = new MoviesFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, movieFragment);
                transaction.addToBackStack(null);
                transaction.commit();
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.bottomNavigationView.setSelectedItemId(R.id.movies);
            }
        });

        return view;
    }
}