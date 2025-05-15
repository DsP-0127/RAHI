package com.example.dwas_11.repository;

import com.example.dwas_11.model.Trip;
import com.example.dwas_11.utils.CurrencyUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Repository class that abstracts the data sources for trips.
 * This follows the Repository pattern to separate business logic 
 * from data sources (local database, remote API, etc.)
 */
public class TripRepository {
    private static TripRepository instance;
    private final List<Trip> cachedTrips = new ArrayList<>();
    
    // Private constructor for singleton pattern
    private TripRepository() {
        loadMockData();
    }
    
    /**
     * Get singleton instance of the repository
     */
    public static synchronized TripRepository getInstance() {
        if (instance == null) {
            instance = new TripRepository();
        }
        return instance;
    }
    
    /**
     * Get all trips for the current user
     */
    public List<Trip> getAllTrips() {
        return new ArrayList<>(cachedTrips);
    }
    
    /**
     * Get a trip by ID
     */
    public Trip getTripById(String tripId) {
        for (Trip trip : cachedTrips) {
            if (trip.getId().equals(tripId)) {
                return trip;
            }
        }
        return null;
    }
    
    /**
     * Add a new trip
     */
    public void addTrip(Trip trip) {
        cachedTrips.add(trip);
        // In a real app, would also update local database or remote API
    }
    
    /**
     * Update an existing trip
     */
    public void updateTrip(Trip trip) {
        for (int i = 0; i < cachedTrips.size(); i++) {
            if (cachedTrips.get(i).getId().equals(trip.getId())) {
                cachedTrips.set(i, trip);
                break;
            }
        }
        // In a real app, would also update local database or remote API
    }
    
    /**
     * Delete a trip
     */
    public void deleteTrip(String tripId) {
        cachedTrips.removeIf(trip -> trip.getId().equals(tripId));
        // In a real app, would also update local database or remote API
    }
    
    /**
     * Load mock data for testing
     */
    private void loadMockData() {
        // Add mock trips with budget values already in INR directly
        Trip summerTrip = new Trip(
                "Summer Vacation", 
                "user123", 
                new Date(), 
                new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000L)
        );
        summerTrip.setTotalBudget(CurrencyUtils.convertUsdToInr(2000));
        cachedTrips.add(summerTrip);
        
        Trip winterTrip = new Trip(
                "Winter Getaway", 
                "user123", 
                new Date(System.currentTimeMillis() + 60 * 24 * 60 * 60 * 1000L), 
                new Date(System.currentTimeMillis() + 67 * 24 * 60 * 60 * 1000L)
        );
        winterTrip.setTotalBudget(CurrencyUtils.convertUsdToInr(3000));
        cachedTrips.add(winterTrip);
    }
} 