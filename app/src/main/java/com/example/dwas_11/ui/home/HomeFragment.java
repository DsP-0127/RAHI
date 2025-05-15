package com.example.dwas_11.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dwas_11.R;
import com.example.dwas_11.adapter.DestinationAdapter;
import com.example.dwas_11.model.Destination;
import com.example.dwas_11.model.Trip;
import com.example.dwas_11.ui.trips.TripAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HomeFragment extends Fragment implements DestinationAdapter.OnDestinationClickListener {

    private TextView welcomeText;
    private TextView weatherLocation;
    private TextView weatherTemp;
    private TextView weatherCondition;
    private RecyclerView upcomingTripsRecycler;
    private RecyclerView destinationsRecyclerView;
    private TabLayout discoverTabs;

    private List<Trip> trips;
    private List<Destination> destinations;
    private TripAdapter tripAdapter;
    private DestinationAdapter destinationAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        
        initViews(root);
        setupWelcomeMessage();
        setupWeather();
        setupUpcomingTrips();
        setupDiscoverSection();
        
        return root;
    }

    private void initViews(View root) {
        welcomeText = root.findViewById(R.id.welcome_text);
        weatherLocation = root.findViewById(R.id.weather_location);
        weatherTemp = root.findViewById(R.id.weather_temp);
        weatherCondition = root.findViewById(R.id.weather_condition);
        upcomingTripsRecycler = root.findViewById(R.id.upcoming_trips_recycler);
        destinationsRecyclerView = root.findViewById(R.id.destinations_recycler_view);
        discoverTabs = root.findViewById(R.id.discover_tabs);
        
        root.findViewById(R.id.see_all_trips).setOnClickListener(v -> navigateToTripsFragment());
    }

    private void setupWelcomeMessage() {
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
        
        String greeting;
        if (timeOfDay < 12) {
            greeting = "Good morning";
        } else if (timeOfDay < 16) {
            greeting = "Good afternoon";
        } else if (timeOfDay < 21) {
            greeting = "Good evening";
        } else {
            greeting = "Good night";
        }
        
        welcomeText.setText(greeting + ", Traveler!");
    }

    private void setupWeather() {
        // In a real app, this would call a weather API
        weatherLocation.setText("Current Location");
        // Convert 72°F to Celsius: (72-32)*5/9 = 22.22°C
        weatherTemp.setText("22°C");
        weatherCondition.setText("Sunny");
    }

    private void setupUpcomingTrips() {
        trips = createSampleTrips();
        tripAdapter = new TripAdapter(requireContext(), trips);
        
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                requireContext(), LinearLayoutManager.HORIZONTAL, false);
        upcomingTripsRecycler.setLayoutManager(layoutManager);
        upcomingTripsRecycler.setAdapter(tripAdapter);
    }

    private void setupDiscoverSection() {
        // Add tabs
        discoverTabs.addTab(discoverTabs.newTab().setText("For You"));
        discoverTabs.addTab(discoverTabs.newTab().setText("Popular"));
        discoverTabs.addTab(discoverTabs.newTab().setText("Trending"));
        discoverTabs.addTab(discoverTabs.newTab().setText("Deals"));
        discoverTabs.addTab(discoverTabs.newTab().setText("Sustainable"));

        discoverTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                updateDestinationsByCategory(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Not needed
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Not needed
            }
        });

        // Setup recycler view
        destinations = createSampleDestinations();
        destinationAdapter = new DestinationAdapter(destinations, this);
        
        destinationsRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        destinationsRecyclerView.setAdapter(destinationAdapter);
    }
    
    private void updateDestinationsByCategory(int category) {
        // In a real app, this would filter based on the category
        // For now, just simulate by changing the filter text
        String query = "";
        switch (category) {
            case 1: // Popular
                query = "paris"; // Just to show filtering working
                break;
            case 2: // Trending
                query = "tokyo";
                break;
            case 3: // Deals
                query = "york";
                break;
            case 4: // Sustainable
                query = "london";
                break;
            default: // For You (show all)
                query = "";
                break;
        }
        
        destinationAdapter.filter(query);
    }
    
    private List<Trip> createSampleTrips() {
        List<Trip> sampleTrips = new ArrayList<>();
        
        // Get current time as a base for trip dates
        Calendar calendar = Calendar.getInstance();
        
        // Trip 1: Starting next week
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        Date startDate1 = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, 5);
        Date endDate1 = calendar.getTime();
        
        Trip parisTrip = new Trip("Paris Getaway", "user_id", startDate1, endDate1);
        
        // Trip 2: Starting in 3 weeks
        calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 21);
        Date startDate2 = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, 10);
        Date endDate2 = calendar.getTime();
        
        Trip tokyoTrip = new Trip("Tokyo Adventure", "user_id", startDate2, endDate2);
        
        sampleTrips.add(parisTrip);
        sampleTrips.add(tokyoTrip);
        
        return sampleTrips;
    }
    
    private List<Destination> createSampleDestinations() {
        List<Destination> sampleDestinations = new ArrayList<>();
        
        sampleDestinations.add(new Destination("Paris", "France", "The City of Light", 1200.00, R.drawable.paris));
        sampleDestinations.add(new Destination("Tokyo", "Japan", "The Land of the Rising Sun", 1500.00, R.drawable.tokyo));
        sampleDestinations.add(new Destination("New York", "USA", "The Big Apple", 1000.00, R.drawable.new_york));
        sampleDestinations.add(new Destination("London", "UK", "The Great City", 1100.00, R.drawable.london));
        
        // Add Indian destinations
        sampleDestinations.add(new Destination("Manali", "Himachal Pradesh", "A picturesque hill station nestled in the mountains", 12500.00, R.drawable.manali));
        sampleDestinations.add(new Destination("Goa", "Goa", "India's beach paradise known for its stunning coastline", 8500.00, R.drawable.goa));
        sampleDestinations.add(new Destination("Shimla", "Himachal Pradesh", "The capital of Himachal Pradesh and a popular hill station", 11200.00, R.drawable.shimla));
        sampleDestinations.add(new Destination("Darjeeling", "West Bengal", "Famous for its tea plantations and stunning views", 15400.00, R.drawable.darjeeling));
        
        return sampleDestinations;
    }
    
    private void navigateToTripsFragment() {
        // In a real app, this would use Navigation Component
        // For now, just simulate navigation by printing to console
        if (getActivity() != null) {
            getActivity().findViewById(R.id.navigation_trips).performClick();
        }
    }

    @Override
    public void onDestinationClick(Destination destination) {
        // Handle destination click
    }

    @Override
    public void onFavoriteClick(Destination destination) {
        // Handle favorite click
    }
} 