package com.example.dwas_11.ui.trips;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dwas_11.R;
import com.example.dwas_11.model.Trip;
import com.example.dwas_11.utils.CurrencyUtils;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.TripViewHolder> {
    
    private final Context context;
    private final List<Trip> trips;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
    
    public TripAdapter(Context context, List<Trip> trips) {
        this.context = context;
        this.trips = trips;
    }
    
    @NonNull
    @Override
    public TripViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_trip_list, parent, false);
        return new TripViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(@NonNull TripViewHolder holder, int position) {
        Trip trip = trips.get(position);
        
        holder.tripName.setText(trip.getName());
        
        // Use the name as destination for now since Trip doesn't have a specific destination field
        if (holder.tripDestination != null) {
            holder.tripDestination.setText(trip.getName());
        }
        
        String dateRange = dateFormat.format(trip.getStartDate()) + " - " + 
                           dateFormat.format(trip.getEndDate());
        holder.tripDates.setText(dateRange);
        
        // Format budget with currency if the budget view exists
        if (holder.tripBudget != null) {
            // Use the CurrencyUtils to format the budget in INR
            holder.tripBudget.setText(CurrencyUtils.formatInr(trip.getTotalBudget()));
        }
    }
    
    @Override
    public int getItemCount() {
        return trips.size();
    }
    
    static class TripViewHolder extends RecyclerView.ViewHolder {
        TextView tripName;
        TextView tripDestination;
        TextView tripDates;
        TextView tripBudget;
        
        public TripViewHolder(@NonNull View itemView) {
            super(itemView);
            tripName = itemView.findViewById(R.id.trip_name);
            tripDates = itemView.findViewById(R.id.trip_dates);
            
            // These might not exist in all trip item layouts
            try {
                tripDestination = itemView.findViewById(R.id.trip_destination);
            } catch (Exception e) {
                // View not found, it's ok
            }
            
            try {
                tripBudget = itemView.findViewById(R.id.trip_budget);
            } catch (Exception e) {
                // View not found, it's ok
            }
        }
    }
} 