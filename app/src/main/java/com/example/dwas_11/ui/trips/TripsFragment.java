package com.example.dwas_11.ui.trips;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dwas_11.R;
import com.example.dwas_11.model.Trip;
import com.example.dwas_11.viewmodel.TripsViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class TripsFragment extends Fragment {
    
    private TripsViewModel viewModel;
    private RecyclerView recyclerView;
    private TripAdapter adapter;
    private TextView emptyView;
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trips, container, false);
        
        // Initialize UI components
        recyclerView = view.findViewById(R.id.trips_recycler_view);
        emptyView = view.findViewById(R.id.empty_view);
        FloatingActionButton fabAddTrip = view.findViewById(R.id.fab_add_trip);
        
        // Setup RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new TripAdapter(getContext(), new ArrayList<>());
        recyclerView.setAdapter(adapter);
        
        // Initialize ViewModel
        viewModel = new ViewModelProvider(this).get(TripsViewModel.class);
        
        // Set up observers
        setupObservers();
        
        // Set up add trip button
        fabAddTrip.setOnClickListener(v -> {
            Toast.makeText(getActivity(), "Create new trip", Toast.LENGTH_SHORT).show();
            // In a real app, this would open a trip creation activity/fragment
        });
        
        return view;
    }
    
    private void setupObservers() {
        // Observe trips data
        viewModel.getTrips().observe(getViewLifecycleOwner(), trips -> {
            if (trips != null && !trips.isEmpty()) {
                updateUI(trips);
            } else {
                showEmptyView();
            }
        });
        
        // Observe loading state
        viewModel.isLoading().observe(getViewLifecycleOwner(), isLoading -> {
            // Could show a progress indicator here
        });
        
        // Observe error messages
        viewModel.getErrorMessage().observe(getViewLifecycleOwner(), errorMsg -> {
            if (errorMsg != null && !errorMsg.isEmpty()) {
                Toast.makeText(getContext(), errorMsg, Toast.LENGTH_SHORT).show();
            }
        });
    }
    
    private void updateUI(List<Trip> trips) {
        recyclerView.setVisibility(View.VISIBLE);
        emptyView.setVisibility(View.GONE);
        adapter = new TripAdapter(getContext(), trips);
        recyclerView.setAdapter(adapter);
    }
    
    private void showEmptyView() {
        recyclerView.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
    }
} 