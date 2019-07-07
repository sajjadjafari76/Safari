package com.fanava.rally.Fragment;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fanava.rally.R;
import com.fanava.rally.adapter.RecyclerMainAdapter;

public class TournamentTableFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerMainAdapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_tournament_table, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        adapter = new RecyclerMainAdapter(getContext(),true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapter);

        return view;
    }
//
}
