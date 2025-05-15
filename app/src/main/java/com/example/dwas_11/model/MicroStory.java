package com.example.dwas_11.model;

import android.graphics.Bitmap;

public class MicroStory {
    private String title;
    private String type; // "audio" or "video"
    private String description;
    private int distanceInMeters;
    private Bitmap thumbnail;

    public MicroStory(String title, String type, String description, int distanceInMeters, Bitmap thumbnail) {
        this.title = title;
        this.type = type;
        this.description = description;
        this.distanceInMeters = distanceInMeters;
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDistanceInMeters() {
        return distanceInMeters;
    }

    public void setDistanceInMeters(int distanceInMeters) {
        this.distanceInMeters = distanceInMeters;
    }

    public Bitmap getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Bitmap thumbnail) {
        this.thumbnail = thumbnail;
    }
    
    public String getFormattedDistance() {
        if (distanceInMeters < 1000) {
            return distanceInMeters + "m away";
        } else {
            float km = distanceInMeters / 1000f;
            return String.format("%.1fkm away", km);
        }
    }
} 