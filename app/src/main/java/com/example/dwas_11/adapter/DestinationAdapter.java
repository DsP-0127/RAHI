package com.example.dwas_11.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.dwas_11.R;
import com.example.dwas_11.model.Destination;
import com.example.dwas_11.utils.CurrencyUtils;
import java.util.ArrayList;
import java.util.List;

public class DestinationAdapter extends RecyclerView.Adapter<DestinationAdapter.DestinationViewHolder> {
    private List<Destination> destinations;
    private List<Destination> filteredDestinations;
    private OnDestinationClickListener listener;

    public interface OnDestinationClickListener {
        void onDestinationClick(Destination destination);
        void onFavoriteClick(Destination destination);
    }

    public DestinationAdapter(List<Destination> destinations, OnDestinationClickListener listener) {
        this.destinations = destinations;
        this.filteredDestinations = new ArrayList<>(destinations);
        this.listener = listener;
    }

    public void filter(String query) {
        filteredDestinations.clear();
        if (query.isEmpty()) {
            filteredDestinations.addAll(destinations);
        } else {
            query = query.toLowerCase();
            for (Destination destination : destinations) {
                if (destination.getName().toLowerCase().contains(query) ||
                    destination.getLocation().toLowerCase().contains(query) ||
                    destination.getDescription().toLowerCase().contains(query)) {
                    filteredDestinations.add(destination);
                }
            }
        }
        notifyDataSetChanged();
    }

    /**
     * Update the adapter with a new list of destinations
     * @param newDestinations Updated list of destinations
     */
    public void updateDestinations(List<Destination> newDestinations) {
        this.destinations.clear();
        this.destinations.addAll(newDestinations);
        this.filteredDestinations.clear();
        this.filteredDestinations.addAll(newDestinations);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DestinationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_destination, parent, false);
        return new DestinationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DestinationViewHolder holder, int position) {
        Destination destination = filteredDestinations.get(position);
        holder.bind(destination);
    }

    @Override
    public int getItemCount() {
        return filteredDestinations.size();
    }

    class DestinationViewHolder extends RecyclerView.ViewHolder {
        private ImageView destinationImageView;
        private ImageView favoriteImageView;
        private TextView destinationName;
        private TextView destinationLocation;
        private TextView destinationPrice;
        private TextView priceLabel;
        private TextView destinationRating;
        private TextView reviewCount;
        private TextView bestTime;
        private TextView weather;
        private com.google.android.material.chip.Chip tag1;
        private com.google.android.material.chip.Chip tag2;
        private com.google.android.material.chip.Chip distanceTag;

        DestinationViewHolder(@NonNull View itemView) {
            super(itemView);
            destinationImageView = itemView.findViewById(R.id.destination_image);
            favoriteImageView = itemView.findViewById(R.id.favorite_button);
            destinationName = itemView.findViewById(R.id.destination_name);
            destinationLocation = itemView.findViewById(R.id.destination_location);
            destinationPrice = itemView.findViewById(R.id.destination_price);
            priceLabel = itemView.findViewById(R.id.price_label);
            destinationRating = itemView.findViewById(R.id.destination_rating);
            reviewCount = itemView.findViewById(R.id.review_count);
            bestTime = itemView.findViewById(R.id.best_time);
            weather = itemView.findViewById(R.id.weather);
            tag1 = itemView.findViewById(R.id.tag_1);
            tag2 = itemView.findViewById(R.id.tag_2);
            distanceTag = itemView.findViewById(R.id.distance_tag);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.onDestinationClick(filteredDestinations.get(position));
                }
            });

            favoriteImageView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Destination destination = filteredDestinations.get(position);
                    destination.setFavorite(!destination.isFavorite());
                    notifyItemChanged(position);
                    listener.onFavoriteClick(destination);
                }
            });
        }

        void bind(Destination destination) {
            destinationImageView.setImageResource(destination.getImageResourceId());
            destinationName.setText(destination.getName());
            destinationLocation.setText(destination.getLocation());
            destinationPrice.setText(CurrencyUtils.formatInr(destination.getPrice()));
            destinationRating.setText(String.format("%.1f", destination.getRating()));
            reviewCount.setText("(" + destination.getReviewCount() + ")");
            
            // Format best time to visit with more context
            bestTime.setText(formatBestTimeToVisit(destination.getBestTimeToVisit()));
            
            // Format weather with temperature unit
            weather.setText(formatWeather(destination.getWeather()));
            
            // Set up tags
            String[] tags = destination.getTags();
            if (tags.length > 0) {
                tag1.setText(tags[0]);
                tag1.setVisibility(View.VISIBLE);
            } else {
                tag1.setVisibility(View.GONE);
            }
            
            if (tags.length > 1) {
                tag2.setText(tags[1]);
                tag2.setVisibility(View.VISIBLE);
            } else {
                tag2.setVisibility(View.GONE);
            }
            
            // Set distance tag (example with mock data)
            // In a real app, this would be calculated based on user's location
            distanceTag.setText(getRandomDistance());
            distanceTag.setVisibility(View.VISIBLE);

            // Set favorite icon
            if (destination.isFavorite()) {
                favoriteImageView.setImageResource(R.drawable.ic_favorite);
            } else {
                favoriteImageView.setImageResource(R.drawable.ic_favorite_border);
            }
        }
        
        private String formatBestTimeToVisit(String bestTime) {
            return "Best time: " + bestTime;
        }
        
        private String formatWeather(String weatherDesc) {
            // Converting simple weather description to include temperature
            // In a real app, this would come from actual weather data
            if (weatherDesc.toLowerCase().contains("sunny")) {
                return "25-30°C, " + weatherDesc;
            } else if (weatherDesc.toLowerCase().contains("mild")) {
                return "20-25°C, " + weatherDesc;
            } else if (weatherDesc.toLowerCase().contains("cold")) {
                return "5-15°C, " + weatherDesc;
            } else {
                return "22°C, " + weatherDesc;
            }
        }
        
        private String getRandomDistance() {
            // Mock distance data
            int[] distances = {1250, 7850, 3420, 9650, 5200};
            int randomDistance = distances[(int)(Math.random() * distances.length)];
            return randomDistance + " km";
        }
    }
} 