package com.example.dwas_11.data;

import com.example.dwas_11.model.Destination;
import java.util.ArrayList;
import java.util.List;

public class DestinationData {
    private static List<Destination> destinations = new ArrayList<>();

    static {
        destinations.add(new Destination(
            "Paris",
            "France",
            "The City of Light, known for its romantic atmosphere, iconic landmarks like the Eiffel Tower, and world-class museums.",
            99600.00,  // ₹99,600 (1200 USD * 83)
            0x7f070000 // Temporary hardcoded resource ID
        ));

        destinations.add(new Destination(
            "Tokyo",
            "Japan",
            "A vibrant metropolis where traditional culture meets cutting-edge technology, offering unique experiences and delicious cuisine.",
            124500.00,  // ₹124,500 (1500 USD * 83)
            0x7f070001 // Temporary hardcoded resource ID
        ));

        destinations.add(new Destination(
            "New York",
            "USA",
            "The city that never sleeps, featuring iconic landmarks, Broadway shows, and diverse cultural experiences.",
            83000.00,  // ₹83,000 (1000 USD * 83)
            0x7f070002 // Temporary hardcoded resource ID
        ));

        destinations.add(new Destination(
            "London",
            "UK",
            "A historic city with royal palaces, world-class museums, and a vibrant cultural scene.",
            91300.00,  // ₹91,300 (1100 USD * 83)
            0x7f070003 // Temporary hardcoded resource ID
        ));
    }

    public static List<Destination> getDestinations() {
        return new ArrayList<>(destinations);
    }
} 