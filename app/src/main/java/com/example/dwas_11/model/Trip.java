package com.example.dwas_11.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Trip {
    private String id;
    private String name;
    private String userId;
    private Date startDate;
    private Date endDate;
    private List<TripDay> days;
    private List<Booking> bookings;
    private String shareableLink;
    private double totalBudget;
    private double spentAmount;
    private String notes;
    private boolean isDownloaded;
    private float sustainabilityScore; // 0-100
    private List<String> photos;
    private Weather destinationWeather;
    
    // Constructor
    public Trip(String name, String userId, Date startDate, Date endDate) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.days = new ArrayList<>();
        this.bookings = new ArrayList<>();
        this.shareableLink = "https://dwas-11.com/trip/" + id;
        this.totalBudget = 0;
        this.spentAmount = 0;
        this.notes = "";
        this.isDownloaded = false;
        this.sustainabilityScore = 0;
        this.photos = new ArrayList<>();
        this.destinationWeather = null;
    }
    
    // Nested classes
    public static class TripDay {
        private String id;
        private Date date;
        private List<TripActivity> activities;
        
        public TripDay(Date date) {
            this.id = UUID.randomUUID().toString();
            this.date = date;
            this.activities = new ArrayList<>();
        }
        
        // Getters and setters
        public String getId() {
            return id;
        }
        
        public Date getDate() {
            return date;
        }
        
        public void setDate(Date date) {
            this.date = date;
        }
        
        public List<TripActivity> getActivities() {
            return activities;
        }
        
        public void addActivity(TripActivity activity) {
            this.activities.add(activity);
        }
        
        public void removeActivity(String activityId) {
            activities.removeIf(activity -> activity.getId().equals(activityId));
        }
    }
    
    public static class TripActivity {
        private String id;
        private String name;
        private String description;
        private Date startTime;
        private Date endTime;
        private String location;
        private double cost;
        private String type; // "SIGHT", "FOOD", "TRANSPORT", "ACCOMMODATION", "EXPERIENCE"
        private Booking linkedBooking;
        private int position; // For drag-and-drop reordering
        
        public TripActivity(String name, Date startTime, Date endTime, String location, String type) {
            this.id = UUID.randomUUID().toString();
            this.name = name;
            this.startTime = startTime;
            this.endTime = endTime;
            this.location = location;
            this.type = type;
            this.cost = 0;
            this.position = 0;
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
        
        public String getDescription() {
            return description;
        }
        
        public void setDescription(String description) {
            this.description = description;
        }
        
        public Date getStartTime() {
            return startTime;
        }
        
        public void setStartTime(Date startTime) {
            this.startTime = startTime;
        }
        
        public Date getEndTime() {
            return endTime;
        }
        
        public void setEndTime(Date endTime) {
            this.endTime = endTime;
        }
        
        public String getLocation() {
            return location;
        }
        
        public void setLocation(String location) {
            this.location = location;
        }
        
        public double getCost() {
            return cost;
        }
        
        public void setCost(double cost) {
            this.cost = cost;
        }
        
        public String getType() {
            return type;
        }
        
        public void setType(String type) {
            this.type = type;
        }
        
        public Booking getLinkedBooking() {
            return linkedBooking;
        }
        
        public void setLinkedBooking(Booking linkedBooking) {
            this.linkedBooking = linkedBooking;
        }
        
        public int getPosition() {
            return position;
        }
        
        public void setPosition(int position) {
            this.position = position;
        }
    }
    
    public static class Booking {
        private String id;
        private String type; // "FLIGHT", "HOTEL", "TRAIN", "BUS", "EXPERIENCE"
        private String provider;
        private String confirmationNumber;
        private Date bookingDate;
        private double amount;
        private boolean isPaid;
        private String bookingUrl;
        private String notes;
        
        public Booking(String type, String provider, Date bookingDate, double amount) {
            this.id = UUID.randomUUID().toString();
            this.type = type;
            this.provider = provider;
            this.bookingDate = bookingDate;
            this.amount = amount;
            this.isPaid = false;
        }
        
        // Getters and setters
        public String getId() {
            return id;
        }
        
        public String getType() {
            return type;
        }
        
        public void setType(String type) {
            this.type = type;
        }
        
        public String getProvider() {
            return provider;
        }
        
        public void setProvider(String provider) {
            this.provider = provider;
        }
        
        public String getConfirmationNumber() {
            return confirmationNumber;
        }
        
        public void setConfirmationNumber(String confirmationNumber) {
            this.confirmationNumber = confirmationNumber;
        }
        
        public Date getBookingDate() {
            return bookingDate;
        }
        
        public void setBookingDate(Date bookingDate) {
            this.bookingDate = bookingDate;
        }
        
        public double getAmount() {
            return amount;
        }
        
        public void setAmount(double amount) {
            this.amount = amount;
        }
        
        public boolean isPaid() {
            return isPaid;
        }
        
        public void setPaid(boolean paid) {
            isPaid = paid;
        }
        
        public String getBookingUrl() {
            return bookingUrl;
        }
        
        public void setBookingUrl(String bookingUrl) {
            this.bookingUrl = bookingUrl;
        }
        
        public String getNotes() {
            return notes;
        }
        
        public void setNotes(String notes) {
            this.notes = notes;
        }
    }
    
    public static class Weather {
        private double temperature;
        private String condition; // "SUNNY", "CLOUDY", "RAINY", etc.
        private int humidity;
        private double precipitation;
        private double windSpeed;
        private String forecast; // Brief description
        
        public Weather(double temperature, String condition, int humidity) {
            this.temperature = temperature;
            this.condition = condition;
            this.humidity = humidity;
        }
        
        // Getters and setters
        public double getTemperature() {
            return temperature;
        }
        
        public void setTemperature(double temperature) {
            this.temperature = temperature;
        }
        
        public String getCondition() {
            return condition;
        }
        
        public void setCondition(String condition) {
            this.condition = condition;
        }
        
        public int getHumidity() {
            return humidity;
        }
        
        public void setHumidity(int humidity) {
            this.humidity = humidity;
        }
        
        public double getPrecipitation() {
            return precipitation;
        }
        
        public void setPrecipitation(double precipitation) {
            this.precipitation = precipitation;
        }
        
        public double getWindSpeed() {
            return windSpeed;
        }
        
        public void setWindSpeed(double windSpeed) {
            this.windSpeed = windSpeed;
        }
        
        public String getForecast() {
            return forecast;
        }
        
        public void setForecast(String forecast) {
            this.forecast = forecast;
        }
    }
    
    // Getters and setters for Trip
    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public Date getStartDate() {
        return startDate;
    }
    
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    public Date getEndDate() {
        return endDate;
    }
    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    public List<TripDay> getDays() {
        return days;
    }
    
    public void addDay(TripDay day) {
        this.days.add(day);
    }
    
    public void removeDay(String dayId) {
        days.removeIf(day -> day.getId().equals(dayId));
    }
    
    public List<Booking> getBookings() {
        return bookings;
    }
    
    public void addBooking(Booking booking) {
        this.bookings.add(booking);
        this.spentAmount += booking.getAmount();
    }
    
    public void removeBooking(String bookingId) {
        bookings.stream()
                .filter(booking -> booking.getId().equals(bookingId))
                .findFirst()
                .ifPresent(booking -> this.spentAmount -= booking.getAmount());
        
        bookings.removeIf(booking -> booking.getId().equals(bookingId));
    }
    
    public String getShareableLink() {
        return shareableLink;
    }
    
    public void setShareableLink(String shareableLink) {
        this.shareableLink = shareableLink;
    }
    
    public double getTotalBudget() {
        return totalBudget;
    }
    
    public void setTotalBudget(double totalBudget) {
        this.totalBudget = totalBudget;
    }
    
    public double getSpentAmount() {
        return spentAmount;
    }
    
    public void setSpentAmount(double spentAmount) {
        this.spentAmount = spentAmount;
    }
    
    public String getNotes() {
        return notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    public boolean isDownloaded() {
        return isDownloaded;
    }
    
    public void setDownloaded(boolean downloaded) {
        isDownloaded = downloaded;
    }
    
    public float getSustainabilityScore() {
        return sustainabilityScore;
    }
    
    public void setSustainabilityScore(float sustainabilityScore) {
        this.sustainabilityScore = sustainabilityScore;
    }
    
    public List<String> getPhotos() {
        return photos;
    }
    
    public void addPhoto(String photoUrl) {
        this.photos.add(photoUrl);
    }
    
    public Weather getDestinationWeather() {
        return destinationWeather;
    }
    
    public void setDestinationWeather(Weather destinationWeather) {
        this.destinationWeather = destinationWeather;
    }
} 