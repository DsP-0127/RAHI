package com.example.dwas_11.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dwas_11.model.Destination;
import com.example.dwas_11.repository.DestinationRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * ViewModel for the Explore fragment following the MVVM architecture.
 * This class is responsible for preparing and managing data for the UI,
 * and handling user interactions that affect the data.
 */
public class ExploreViewModel extends ViewModel {
    private final DestinationRepository destinationRepository;
    
    private final MutableLiveData<List<Destination>> destinations = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private final MutableLiveData<String> selectedCategory = new MutableLiveData<>("All");
    
    public ExploreViewModel() {
        // Get repository instance
        destinationRepository = DestinationRepository.getInstance();
        
        // Load initial data
        loadDestinations();
    }
    
    /**
     * Get all destinations as LiveData
     */
    public LiveData<List<Destination>> getDestinations() {
        return destinations;
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
     * Get selected category as LiveData
     */
    public LiveData<String> getSelectedCategory() {
        return selectedCategory;
    }
    
    /**
     * Load all destinations
     */
    public void loadDestinations() {
        isLoading.setValue(true);
        try {
            List<Destination> allDestinations = destinationRepository.getAllDestinations();
            destinations.setValue(allDestinations);
            isLoading.setValue(false);
        } catch (Exception e) {
            isLoading.setValue(false);
            errorMessage.setValue("Failed to load destinations: " + e.getMessage());
        }
    }
    
    /**
     * Search destinations by name or location
     */
    public void searchDestinations(String query) {
        isLoading.setValue(true);
        try {
            List<Destination> searchResults = destinationRepository.searchDestinations(query);
            destinations.setValue(searchResults);
            isLoading.setValue(false);
        } catch (Exception e) {
            isLoading.setValue(false);
            errorMessage.setValue("Search failed: " + e.getMessage());
        }
    }
    
    /**
     * Filter destinations by category
     */
    public void filterByCategory(String category) {
        selectedCategory.setValue(category);
        
        isLoading.setValue(true);
        try {
            List<Destination> allDestinations = destinationRepository.getAllDestinations();
            List<Destination> filteredDestinations = new ArrayList<>();
            
            if (category == null || category.equals("All")) {
                filteredDestinations = allDestinations;
            } else {
                // Filter destinations based on category
                for (Destination destination : allDestinations) {
                    String[] tags = destination.getTags();
                    for (String tag : tags) {
                        if (tag.equals(category) || 
                            // Handle special cases
                            (category.equals("Spiritual") && (tag.equals("Religious") || tag.equals("Spiritual"))) || 
                            (category.equals("Mountains") && tag.equals("Hills")) ||
                            (category.equals("Beaches") && tag.equals("Backwaters"))) {
                            filteredDestinations.add(destination);
                            break;
                        }
                    }
                }
            }
            
            destinations.setValue(filteredDestinations);
            isLoading.setValue(false);
        } catch (Exception e) {
            isLoading.setValue(false);
            errorMessage.setValue("Filter failed: " + e.getMessage());
        }
    }
    
    /**
     * Toggle favorite status for a destination
     */
    public void toggleFavorite(int destinationId) {
        destinationRepository.toggleFavorite(destinationId);
        
        // Refresh the list to show updated favorite status
        List<Destination> currentList = destinations.getValue();
        destinations.setValue(currentList);
    }
} 