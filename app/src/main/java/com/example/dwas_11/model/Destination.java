package com.example.dwas_11.model;

public class Destination {
    private int id;
    private String name;
    private String description;
    private String imageUrl;
    private float rating;
    private int price;
    private String location;
    private boolean isFavorite;
    private String bestTimeToVisit;
    private String[] attractions;
    private String weather;
    private int reviewCount;
    private String[] tags;
    private int imageResourceId;

    // Simple constructor for basic initialization
    public Destination(String name, String location, String description, double price, int imageResourceId) {
        this.id = 0;
        this.name = name;
        this.location = location;
        this.description = description;
        this.price = (int) price;
        this.imageResourceId = imageResourceId;
        this.rating = 4.5f;
        this.bestTimeToVisit = "All year round";
        this.attractions = new String[]{"Main attraction 1", "Main attraction 2"};
        this.weather = "Mild";
        this.reviewCount = 100;
        this.tags = new String[]{"Popular", "Cultural"};
        this.isFavorite = false;
    }

    // Full constructor for complete initialization
    public Destination(int id, String name, String description, String imageUrl, float rating, 
                      int price, String location, String bestTimeToVisit, String[] attractions, 
                      String weather, int reviewCount, String[] tags, int imageResourceId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.rating = rating;
        this.price = price;
        this.location = location;
        this.bestTimeToVisit = bestTimeToVisit;
        this.attractions = attractions;
        this.weather = weather;
        this.reviewCount = reviewCount;
        this.tags = tags;
        this.imageResourceId = imageResourceId;
        this.isFavorite = false;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public float getRating() {
        return rating;
    }

    public int getPrice() {
        return price;
    }

    public String getLocation() {
        return location;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public String getBestTimeToVisit() {
        return bestTimeToVisit;
    }

    public String[] getAttractions() {
        return attractions;
    }

    public String getWeather() {
        return weather;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public String[] getTags() {
        return tags;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
} 