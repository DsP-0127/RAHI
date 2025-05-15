package com.example.dwas_11;

import android.app.Application;

import com.example.dwas_11.repository.DestinationRepository;
import com.example.dwas_11.repository.TripRepository;

/**
 * Main Application class for initializing app-wide components and services.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        
        // Initialize repositories early to preload data
        initializeRepositories();
    }
    
    private void initializeRepositories() {
        // Initialize repositories
        DestinationRepository.getInstance();
        TripRepository.getInstance();
        
        // In a real app, this would also initialize:
        // - Database connections
        // - API clients
        // - SharedPreferences wrappers
        // - Analytics services
        // - Crash reporting
    }
} 