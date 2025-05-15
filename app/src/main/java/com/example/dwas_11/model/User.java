package com.example.dwas_11.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String id;
    private String name;
    private String email;
    private String profileImageUrl;
    private boolean isLoggedIn;
    private String authProvider; // "EMAIL", "GOOGLE", "FACEBOOK"
    
    // Travel preferences
    private List<String> preferredDestinationTypes; // "Beach", "Mountain", "City", "Cultural"
    private List<String> preferredActivities; // "Hiking", "Shopping", "Food", "Museums"
    private int budgetRange; // 1-5 scale (1: Budget, 5: Luxury)
    private int travelPace; // 1-3 scale (1: Relaxed, 2: Moderate, 3: Fast-paced)
    private boolean preferLocalExperiences;
    private boolean preferSustainableOptions;
    private String preferredAccommodationType; // "Hotel", "Hostel", "Apartment", "Resort"
    
    // Safety preferences
    private boolean enableLocationSharing;
    private boolean enableCheckInReminders;
    private List<String> emergencyContacts;
    
    // Constructor
    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.isLoggedIn = true;
        this.authProvider = "EMAIL";
        this.preferredDestinationTypes = new ArrayList<>();
        this.preferredActivities = new ArrayList<>();
        this.budgetRange = 3;
        this.travelPace = 2;
        this.preferLocalExperiences = true;
        this.preferSustainableOptions = false;
        this.preferredAccommodationType = "Hotel";
        this.enableLocationSharing = false;
        this.enableCheckInReminders = true;
        this.emergencyContacts = new ArrayList<>();
    }
    
    // Getters and setters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public String getAuthProvider() {
        return authProvider;
    }

    public void setAuthProvider(String authProvider) {
        this.authProvider = authProvider;
    }

    public List<String> getPreferredDestinationTypes() {
        return preferredDestinationTypes;
    }

    public void setPreferredDestinationTypes(List<String> preferredDestinationTypes) {
        this.preferredDestinationTypes = preferredDestinationTypes;
    }

    public List<String> getPreferredActivities() {
        return preferredActivities;
    }

    public void setPreferredActivities(List<String> preferredActivities) {
        this.preferredActivities = preferredActivities;
    }

    public int getBudgetRange() {
        return budgetRange;
    }

    public void setBudgetRange(int budgetRange) {
        this.budgetRange = budgetRange;
    }

    public int getTravelPace() {
        return travelPace;
    }

    public void setTravelPace(int travelPace) {
        this.travelPace = travelPace;
    }

    public boolean isPreferLocalExperiences() {
        return preferLocalExperiences;
    }

    public void setPreferLocalExperiences(boolean preferLocalExperiences) {
        this.preferLocalExperiences = preferLocalExperiences;
    }

    public boolean isPreferSustainableOptions() {
        return preferSustainableOptions;
    }

    public void setPreferSustainableOptions(boolean preferSustainableOptions) {
        this.preferSustainableOptions = preferSustainableOptions;
    }

    public String getPreferredAccommodationType() {
        return preferredAccommodationType;
    }

    public void setPreferredAccommodationType(String preferredAccommodationType) {
        this.preferredAccommodationType = preferredAccommodationType;
    }

    public boolean isEnableLocationSharing() {
        return enableLocationSharing;
    }

    public void setEnableLocationSharing(boolean enableLocationSharing) {
        this.enableLocationSharing = enableLocationSharing;
    }

    public boolean isEnableCheckInReminders() {
        return enableCheckInReminders;
    }

    public void setEnableCheckInReminders(boolean enableCheckInReminders) {
        this.enableCheckInReminders = enableCheckInReminders;
    }

    public List<String> getEmergencyContacts() {
        return emergencyContacts;
    }

    public void setEmergencyContacts(List<String> emergencyContacts) {
        this.emergencyContacts = emergencyContacts;
    }
} 