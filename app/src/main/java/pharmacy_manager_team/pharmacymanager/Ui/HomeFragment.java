package pharmacy_manager_team.pharmacymanager.Ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import pharmacy_manager_team.pharmacymanager.R;


public class HomeFragment extends Fragment {
    TextView add;
    RecyclerView recyclerView;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= LayoutInflater.from(getContext()).inflate(R.layout.fragment_home,container,false);
        recyclerView = view.findViewById(R.id.rvHome);
        add=view.findViewById(R.id.add);
        add.setOnClickListener(v -> { startActivity(new Intent(getContext(),MedicinesEntriesActivity.class)); });


        return view;
    }
}