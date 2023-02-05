package com.example.moviemanager;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class CinemasFragment extends Fragment {
    private View view;
    private ArrayList<Cinema> cinemas = new ArrayList<>();

    public CinemasFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cinemas, container, false);
        TextView textView = view.findViewById(R.id.cinemaTextView);
        Context context = getActivity();
        CinemaDataService cinemaDataService = new CinemaDataService(context);
        cinemaDataService.getAllCinemas(new CinemaDataService.CinemaListResponseListener() {
            @Override
            public void onError(String message) {
                Toast.makeText(context, "Main thread error!", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(List<Cinema> allCinemas) {
                cinemas = new ArrayList<>(allCinemas);
                textView.setText(cinemas.get(0).getName());
            }
        });

        WebView webView = view.findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.cinemaone.ro/home#/");

        return view;
    }
}