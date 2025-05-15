package com.example.dwas_11.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dwas_11.model.Trip;
import com.example.dwas_11.repository.TripRepository;

import java.util.List;

/**
 * ViewModel for the Trips fragment following the MVVM architecture.
 * This class is responsible for preparing and managing the trip data for the UI,
 * and handling user interactions that affect the data.
 */
public class TripsViewModel extends ViewModel {
    private final TripRepository tripRepository;
    
    private final MutableLiveData<List<Trip>> trips = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    
    public TripsViewModel() {
        // Get repository instance
        tripRepository = TripRepository.getInstance();
        
        // Load initial data
        loadTrips();
    }
    
    /**
     * Get all trips as LiveData
     */
    public LiveData<List<Trip>> getTrips() {
        return trips;
    }
    
    /**
     * Get loading state as LiveData
     */
    public LiveData<Boolean> isLoading() {
        return isLoading;
    }
    
    /**
     * Get error message as LiveData
     */
    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }
    
    /**
     * Load all trips for the current user
     */
    public void loadTrips() {
        isLoading.setValue(true);
        try {
            List<Trip> userTrips = tripRepository.getAllTrips();
            trips.setValue(userTrips);
            isLoading.setValue(false);
        } catch (Exception e) {
            isLoading.setValue(false);
            errorMessage.setValue("Failed to load trips: " + e.getMessage());
        }
    }
    
    /**
     * Add a new trip
     */
    public void addTrip(Trip trip) {
        try {
            tripRepository.addTrip(trip);
            loadTrips(); // Refresh list
        } catch (Exception e) {
            errorMessage.setValue("Failed to add trip: " + e.getMessage());
        }
    }
    
    /**
     * Update an existing trip
     */
    public void updateTrip(Trip trip) {
        try {
            tripRepository.updateTrip(trip);
            loadTrips(); // Refresh list
        } catch (Exception e) {
            errorMessage.setValue("Failed to update trip: " + e.getMessage());
        }
    }
    
    /**
     * Delete a trip
     */
    public void deleteTrip(String tripId) {
        try {
            tripRepository.deleteTrip(tripId);
            loadTrips(); // Refresh list
        } catch (Exception e) {
            errorMessage.setValue("Failed to delete trip: " + e.getMessage());
        }
    }
} 