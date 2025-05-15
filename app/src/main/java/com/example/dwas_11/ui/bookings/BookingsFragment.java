package com.example.dwas_11.ui.bookings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dwas_11.R;
import com.google.android.material.tabs.TabLayout;

public class BookingsFragment extends Fragment {
    
    private RecyclerView recyclerView;
    private TextView emptyView;
    private TabLayout tabLayout;
    private NestedScrollView sampleContent;
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bookings, container, false);
        
        // Initialize UI components
        recyclerView = view.findViewById(R.id.bookings_recycler_view);
        emptyView = view.findViewById(R.id.empty_view);
        tabLayout = view.findViewById(R.id.booking_tabs);
        sampleContent = view.findViewById(R.id.sample_content);
        
        // Setup RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        
        // Hide RecyclerView and show sample content
        recyclerView.setVisibility(View.GONE);
        sampleContent.setVisibility(View.VISIBLE);
        emptyView.setVisibility(View.GONE);
        
        // Setup TabLayout
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // In a real app, this would filter bookings based on the selected tab
                updateBookingsList(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Not needed for now
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Not needed for now
            }
        });
        
        // Load initial bookings (upcoming)
        updateBookingsList(0);
        
        return view;
    }
    
    private void updateBookingsList(int tabPosition) {
        // Show sample content for Upcoming tab, empty view for others
        if (tabPosition == 0) {
            sampleContent.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        } else {
            sampleContent.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
            
            // Update the empty view text based on the tab position
            switch (tabPosition) {
                case 1:
                    emptyView.setText("No past bookings");
                    break;
                case 2:
                    emptyView.setText("No cancelled bookings");
                    break;
                default:
                    emptyView.setText("No bookings found");
                    break;
            }
        }
        recyclerView.setVisibility(View.GONE);
    }
} 