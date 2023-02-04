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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

//        HorizontalScrollView horizontalScrollView = view.findViewById(R.id.horizontalScrollView);
//        LinearLayout linearLayout = view.findViewById(R.id.linear_layout);
//
//        String[] imageUrls = {
//                "https://images.unsplash.com/photo-1542614471-001ccf2b449c?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=435&q=80",
//                "https://images.unsplash.com/photo-1542614471-001ccf2b449c?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=435&q=80",
//                "https://images.unsplash.com/photo-1542614471-001ccf2b449c?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=435&q=80",
//                "https://images.unsplash.com/photo-1542614471-001ccf2b449c?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=435&q=80",
//                "https://images.unsplash.com/photo-1542614471-001ccf2b449c?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=435&q=80",
//                "https://images.unsplash.com/photo-1542614471-001ccf2b449c?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=435&q=80",
//        };
//
//        for (String imageUrl : imageUrls) {
//            ImageView imageView = (ImageView) inflater.inflate(R.layout.image_view_item , linearLayout, false);
//            Picasso.get().load(imageUrl).into(imageView);
//            linearLayout.addView(imageView);
//        }
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