package com.example.dwas_11.ui.explore;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dwas_11.R;
import com.example.dwas_11.adapter.DestinationAdapter;
import com.example.dwas_11.model.Destination;
import com.example.dwas_11.viewmodel.ExploreViewModel;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;

public class ExploreFragment extends Fragment implements DestinationAdapter.OnDestinationClickListener {
    
    private ExploreViewModel viewModel;
    private RecyclerView recyclerView;
    private RecyclerView trendingRecyclerView;
    private DestinationAdapter adapter;
    private DestinationAdapter trendingAdapter;
    private TextView emptyView;
    private ProgressBar loadingIndicator;
    private ChipGroup filterChipGroup;
    private EditText searchEditText;
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_explore, container, false);
        
        // Initialize UI components
        recyclerView = view.findViewById(R.id.destinations_recycler_view);
        trendingRecyclerView = view.findViewById(R.id.trending_recycler_view);
        emptyView = view.findViewById(R.id.empty_view);
        loadingIndicator = view.findViewById(R.id.loading_indicator);
        filterChipGroup = view.findViewById(R.id.filter_chip_group);
        searchEditText = view.findViewById(R.id.search_edit_text);
        
        // Set up RecyclerViews
        setupRecyclerViews();
        
        // Initialize ViewModel
        viewModel = new ViewModelProvider(this).get(ExploreViewModel.class);
        
        // Set up observers
        setupObservers();
        
        // Set up chip listeners
        setupChipListeners();
        
        // Set up search functionality
        setupSearchFunctionality();
        
        return view;
    }
    
    private void setupRecyclerViews() {
        // Main destinations
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new DestinationAdapter(new ArrayList<>(), this);
        recyclerView.setAdapter(adapter);
        
        // Trending destinations (horizontal scroll)
        trendingRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        trendingAdapter = new DestinationAdapter(new ArrayList<>(), this);
        trendingRecyclerView.setAdapter(trendingAdapter);
    }
    
    private void setupObservers() {
        // Observe destinations list
        viewModel.getDestinations().observe(getViewLifecycleOwner(), destinations -> {
            if (destinations != null && !destinations.isEmpty()) {
                adapter.updateDestinations(destinations);
                trendingAdapter.updateDestinations(destinations); // Use same data for now
                recyclerView.setVisibility(View.VISIBLE);
                trendingRecyclerView.setVisibility(View.VISIBLE);
                emptyView.setVisibility(View.GONE);
            } else {
                recyclerView.setVisibility(View.GONE);
                trendingRecyclerView.setVisibility(View.GONE);
                emptyView.setVisibility(View.VISIBLE);
            }
        });
        
        // Observe loading state
        viewModel.isLoading().observe(getViewLifecycleOwner(), isLoading -> {
            loadingIndicator.setVisibility(isLoading ? View.VISIBLE : View.GONE);
        });
        
        // Observe error messages
        viewModel.getErrorMessage().observe(getViewLifecycleOwner(), errorMsg -> {
            if (errorMsg != null && !errorMsg.isEmpty()) {
                Toast.makeText(getContext(), errorMsg, Toast.LENGTH_SHORT).show();
            }
        });
        
        // Observe selected category
        viewModel.getSelectedCategory().observe(getViewLifecycleOwner(), category -> {
            // Update UI to reflect selected category
            updateSelectedChip(category);
        });
    }
    
    private void setupChipListeners() {
        // Add click listeners to all filter chips
        for (int i = 0; i < filterChipGroup.getChildCount(); i++) {
            Chip chip = (Chip) filterChipGroup.getChildAt(i);
            chip.setOnClickListener(v -> {
                viewModel.filterByCategory(chip.getText().toString());
            });
        }
        
        // Set chip texts to Indian travel categories
        ((Chip) filterChipGroup.findViewById(R.id.filter_beaches)).setText("Beaches");
        ((Chip) filterChipGroup.findViewById(R.id.filter_mountains)).setText("Mountains");
        ((Chip) filterChipGroup.findViewById(R.id.filter_cities)).setText("Spiritual");
        ((Chip) filterChipGroup.findViewById(R.id.filter_historical)).setText("Historical");
        ((Chip) filterChipGroup.findViewById(R.id.filter_cultural)).setText("Cultural");
        
        // Add one more chip for "Adventure"
        Chip adventureChip = new Chip(getContext());
        adventureChip.setText("Adventure");
        adventureChip.setCheckable(true);
        adventureChip.setClickable(true);
        adventureChip.setChipBackgroundColorResource(R.color.colorPrimaryLight);
        adventureChip.setTextColor(ContextCompat.getColorStateList(requireContext(), R.color.dark_gray));
        adventureChip.setOnClickListener(v -> {
            viewModel.filterByCategory("Adventure");
        });
        filterChipGroup.addView(adventureChip);
    }
    
    private void updateSelectedChip(String category) {
        for (int i = 0; i < filterChipGroup.getChildCount(); i++) {
            Chip chip = (Chip) filterChipGroup.getChildAt(i);
            chip.setChecked(chip.getText().toString().equals(category));
        }
    }
    
    private void setupSearchFunctionality() {
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Not needed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Filter destinations based on search text
                if (adapter != null) {
                    adapter.filter(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Not needed
            }
        });
    }
    
    @Override
    public void onDestinationClick(Destination destination) {
        // Handle destination click - navigate to details
        Toast.makeText(getContext(), "Selected: " + destination.getName(), Toast.LENGTH_SHORT).show();
        // In a real app, would navigate to destination details
    }
    
    @Override
    public void onFavoriteClick(Destination destination) {
        // Handle favorite button click
        viewModel.toggleFavorite(destination.getId());
    }
} 