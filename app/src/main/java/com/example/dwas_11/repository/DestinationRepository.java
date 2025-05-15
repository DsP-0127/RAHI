package com.example.dwas_11.repository;

import com.example.dwas_11.R;
import com.example.dwas_11.model.Destination;

import java.util.ArrayList;
import java.util.List;

/**
 * Repository class that abstracts the data sources for destinations.
 * This follows the Repository pattern to separate business logic 
 * from data sources (local database, remote API, etc.)
 */
public class DestinationRepository {
    private static DestinationRepository instance;
    private final List<Destination> cachedDestinations = new ArrayList<>();
    
    // Private constructor to enforce singleton pattern
    private DestinationRepository() {
        loadInitialData();
    }
    
    /**
     * Get singleton instance of the repository
     */
    public static synchronized DestinationRepository getInstance() {
        if (instance == null) {
            instance = new DestinationRepository();
        }
        return instance;
    }
    
    /**
     * Get all destinations (could be from cache, local DB, or remote API)
     */
    public List<Destination> getAllDestinations() {
        return new ArrayList<>(cachedDestinations);
    }
    
    /**
     * Get a destination by ID
     */
    public Destination getDestinationById(int id) {
        for (Destination destination : cachedDestinations) {
            if (destination.getId() == id) {
                return destination;
            }
        }
        return null;
    }
    
    /**
     * Search destinations by name or location
     */
    public List<Destination> searchDestinations(String query) {
        if (query == null || query.isEmpty()) {
            return getAllDestinations();
        }
        
        List<Destination> results = new ArrayList<>();
        String lowerQuery = query.toLowerCase();
        
        for (Destination destination : cachedDestinations) {
            if (destination.getName().toLowerCase().contains(lowerQuery) ||
                destination.getLocation().toLowerCase().contains(lowerQuery)) {
                results.add(destination);
            }
        }
        
        return results;
    }
    
    /**
     * Toggle favorite status for a destination
     */
    public void toggleFavorite(int destinationId) {
        for (Destination destination : cachedDestinations) {
            if (destination.getId() == destinationId) {
                destination.setFavorite(!destination.isFavorite());
                break;
            }
        }
        
        // In a real app, would also update local database or remote API
    }
    
    /**
     * Load initial set of destinations
     * In a real app, this would come from a local database or remote API
     */
    private void loadInitialData() {
        // Add popular Indian destinations with prices in INR
        
        // 1. Manali
        cachedDestinations.add(new Destination(
            1,
            "Manali",
            "A picturesque hill station nestled in the mountains of Himachal Pradesh, known for its snow-capped peaks, adventure sports, and serene landscapes.",
            "", // No external URL needed as we use local drawables
            4.7f,
            12500, // Flight: ₹7500, Accommodation: ₹4000, Local expenses: ₹1000
            "Himachal Pradesh",
            "March-June, September-November",
            new String[]{"Solang Valley", "Rohtang Pass", "Hadimba Temple", "Old Manali"},
            "Cool, Snowy winters and mild summers",
            2450,
            new String[]{"Mountains", "Adventure"},
            R.drawable.manali
        ));
        
        // 2. Nainital
        cachedDestinations.add(new Destination(
            2,
            "Nainital",
            "A charming lake district in the Kumaon region of Uttarakhand, famous for its Naini Lake surrounded by mountains and colonial architecture.",
            "",
            4.5f,
            9800, // Flight: ₹5500, Accommodation: ₹3500, Local expenses: ₹800
            "Uttarakhand",
            "March-June, September-October",
            new String[]{"Naini Lake", "Mall Road", "Naina Devi Temple", "Snow View Point"},
            "Pleasant, Mild temperature year-round",
            1980,
            new String[]{"Lakes", "Hills"},
            R.drawable.nainital
        ));
        
        // 3. Shimla
        cachedDestinations.add(new Destination(
            3,
            "Shimla",
            "The capital of Himachal Pradesh and a popular hill station with Victorian architecture, panoramic mountain views, and vibrant markets.",
            "",
            4.6f,
            11200, // Flight: ₹6800, Accommodation: ₹3500, Local expenses: ₹900
            "Himachal Pradesh",
            "March-June, October-December",
            new String[]{"Mall Road", "Ridge", "Christ Church", "Jakhu Temple"},
            "Cool, Snow in winters and pleasant summers",
            3200,
            new String[]{"Hills", "Colonial"},
            R.drawable.shimla
        ));
        
        // 4. Darjeeling
        cachedDestinations.add(new Destination(
            4,
            "Darjeeling",
            "A hill station in West Bengal famous for its tea plantations, stunning views of Kanchenjunga, and the historic Darjeeling Himalayan Railway.",
            "",
            4.8f,
            15400, // Flight: ₹8500, Accommodation: ₹5500, Local expenses: ₹1400
            "West Bengal",
            "March-May, September-November",
            new String[]{"Tiger Hill", "Batasia Loop", "Tea Gardens", "Himalayan Mountaineering Institute"},
            "Cool, Foggy with occasional rain",
            2100,
            new String[]{"Tea Gardens", "Mountains"},
            R.drawable.darjeeling
        ));
        
        // 5. Gangtok
        cachedDestinations.add(new Destination(
            5,
            "Gangtok",
            "The capital of Sikkim, offering breathtaking views of the Himalayas, vibrant Buddhist culture, and clean, well-planned cityscape.",
            "",
            4.7f,
            16200, // Flight: ₹9500, Accommodation: ₹5000, Local expenses: ₹1700
            "Sikkim",
            "March-May, September-November",
            new String[]{"Rumtek Monastery", "Nathula Pass", "MG Marg", "Tsomgo Lake"},
            "Cool, Mild temperature with occasional rainfall",
            2450,
            new String[]{"Mountains", "Cultural"},
            R.drawable.gangtok
        ));
        
        // 6. Guwahati
        cachedDestinations.add(new Destination(
            6,
            "Guwahati",
            "The gateway to Northeast India situated on the banks of the Brahmaputra River, known for its sacred sites, wildlife, and cultural heritage.",
            "",
            4.2f,
            13800, // Flight: ₹8200, Accommodation: ₹4000, Local expenses: ₹1600
            "Assam",
            "October-March",
            new String[]{"Kamakhya Temple", "Umananda Island", "Assam State Museum", "Brahmaputra River Cruise"},
            "Humid, Warm with moderate rainfall",
            2100,
            new String[]{"Cultural", "Historical"},
            R.drawable.guwahati
        ));
        
        // 7. Goa
        cachedDestinations.add(new Destination(
            7,
            "Goa",
            "India's beach paradise known for its stunning coastline, Portuguese heritage, vibrant nightlife, and relaxed atmosphere.",
            "",
            4.9f,
            8500, // Flight: ₹3800, Accommodation: ₹3500, Local expenses: ₹1200
            "Goa",
            "November-February",
            new String[]{"Calangute Beach", "Baga Beach", "Fort Aguada", "Basilica of Bom Jesus"},
            "Tropical, Warm and humid year-round",
            590,
            new String[]{"Beaches", "Nightlife"},
            R.drawable.goa
        ));
        
        // 8. Jaipur
        cachedDestinations.add(new Destination(
            8,
            "Jaipur",
            "The 'Pink City' and capital of Rajasthan, famous for its majestic palaces, forts, vibrant bazaars, and rich cultural heritage.",
            "",
            4.6f,
            9700, // Flight: ₹4500, Accommodation: ₹4000, Local expenses: ₹1200
            "Rajasthan",
            "October-March",
            new String[]{"Amber Fort", "Hawa Mahal", "City Palace", "Jantar Mantar"},
            "Arid, Hot summers and mild winters",
            1180,
            new String[]{"Historical", "Cultural"},
            R.drawable.jaipur
        ));
        
        // 9. Kochi
        cachedDestinations.add(new Destination(
            9,
            "Kochi",
            "A vibrant city on Kerala's coast with a unique blend of Dutch, Portuguese and British colonial architecture, famous for Chinese fishing nets and backwaters.",
            "",
            4.5f,
            14200, // Flight: ₹7500, Accommodation: ₹5000, Local expenses: ₹1700
            "Kerala",
            "October-March",
            new String[]{"Fort Kochi", "Chinese Fishing Nets", "Mattancherry Palace", "Kerala Backwater Cruise"},
            "Tropical, Humid with moderate rainfall",
            1020,
            new String[]{"Backwaters", "Colonial"},
            R.drawable.kochi
        ));
        
        // 10. Agra
        cachedDestinations.add(new Destination(
            10,
            "Agra",
            "Home to the iconic Taj Mahal, Agra showcases the finest Mughal architecture with its forts, mausoleums, and historic significance.",
            "",
            4.7f,
            8700, // Flight: ₹4200, Accommodation: ₹3500, Local expenses: ₹1000
            "Uttar Pradesh",
            "October-March",
            new String[]{"Taj Mahal", "Agra Fort", "Fatehpur Sikri", "Mehtab Bagh"},
            "Semi-arid, Hot summers and cool winters",
            1415,
            new String[]{"Historical", "Cultural"},
            R.drawable.agra
        ));
        
        // 11. Amritsar
        cachedDestinations.add(new Destination(
            11,
            "Amritsar",
            "A city in Punjab known for the Golden Temple, Sikh heritage, patriotic sites, and delicious Punjabi cuisine.",
            "",
            4.8f,
            9500, // Flight: ₹5200, Accommodation: ₹3300, Local expenses: ₹1000
            "Punjab",
            "October-March",
            new String[]{"Golden Temple", "Jallianwala Bagh", "Wagah Border", "Partition Museum"},
            "Semi-arid, Hot summers and cool winters",
            1750,
            new String[]{"Religious", "Cultural"},
            R.drawable.amritsar
        ));
        
        // 12. Rishikesh
        cachedDestinations.add(new Destination(
            12,
            "Rishikesh",
            "The 'Yoga Capital of the World' nestled in the foothills of the Himalayas, known for spiritual experiences, adventure sports, and pristine nature.",
            "",
            4.6f,
            11300, // Flight: ₹6500, Accommodation: ₹3800, Local expenses: ₹1000
            "Uttarakhand",
            "February-April, September-November",
            new String[]{"Laxman Jhula", "Triveni Ghat", "Beatles Ashram", "River Rafting"},
            "Moderate, Pleasant with occasional rainfall",
            1845,
            new String[]{"Spiritual", "Adventure"},
            R.drawable.rishikesh
        ));
        
        // 13. Varanasi
        cachedDestinations.add(new Destination(
            13,
            "Varanasi",
            "One of the world's oldest continuously inhabited cities on the banks of the Ganges, known for its spiritual significance, ghats, and rich cultural heritage.",
            "",
            4.5f,
            10500, // Flight: ₹6200, Accommodation: ₹3300, Local expenses: ₹1000
            "Uttar Pradesh",
            "October-March",
            new String[]{"Dashashwamedh Ghat", "Kashi Vishwanath Temple", "Sarnath", "Evening Ganga Aarti"},
            "Semi-arid, Hot summers and cool winters",
            1520,
            new String[]{"Spiritual", "Historical"},
            R.drawable.varanasi
        ));
    }
} 