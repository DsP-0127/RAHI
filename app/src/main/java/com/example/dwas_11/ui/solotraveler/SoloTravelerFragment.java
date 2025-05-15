package com.example.dwas_11.ui.solotraveler;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dwas_11.R;
import com.example.dwas_11.model.MicroStory;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.slider.RangeSlider;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SoloTravelerFragment extends Fragment {

    private RecyclerView nearbyStoriesList;
    private RangeSlider budgetSlider;
    private TextView labelBudget;
    private TextView statusAdaptiveMood;
    private TextView safetyStatus;
    private MaterialButton buttonAdaptiveMood;
    private MaterialButton btnGenerateMystery;
    private MaterialButton btnSetupSafety;
    private MaterialButton btnEmergency;
    private FloatingActionButton fabAddStory;
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_solo_traveler, container, false);
        
        initViews(root);
        setupClickListeners();
        setupBudgetSlider();
        setupMicroStories();
        
        return root;
    }

    private void initViews(View root) {
        nearbyStoriesList = root.findViewById(R.id.nearby_stories_list);
        budgetSlider = root.findViewById(R.id.budget_slider);
        labelBudget = root.findViewById(R.id.label_budget);
        statusAdaptiveMood = root.findViewById(R.id.status_adaptive_mood);
        safetyStatus = root.findViewById(R.id.safety_status);
        buttonAdaptiveMood = root.findViewById(R.id.button_adaptive_mood);
        btnGenerateMystery = root.findViewById(R.id.btn_generate_mystery);
        btnSetupSafety = root.findViewById(R.id.btn_setup_safety);
        btnEmergency = root.findViewById(R.id.btn_emergency);
        fabAddStory = root.findViewById(R.id.fab_add_story);
    }

    private void setupClickListeners() {
        buttonAdaptiveMood.setOnClickListener(v -> {
            Toast.makeText(requireContext(), "Connecting to wearable device...", Toast.LENGTH_SHORT).show();
        });
        
        btnGenerateMystery.setOnClickListener(v -> {
            Toast.makeText(requireContext(), "Generating your mystery day...", Toast.LENGTH_SHORT).show();
        });
        
        btnSetupSafety.setOnClickListener(v -> {
            Toast.makeText(requireContext(), "Setting up safety contacts...", Toast.LENGTH_SHORT).show();
        });
        
        btnEmergency.setOnClickListener(v -> {
            Toast.makeText(requireContext(), "SOS Alert sent to nearby Safety Ambassadors", Toast.LENGTH_SHORT).show();
        });
        
        fabAddStory.setOnClickListener(v -> {
            Toast.makeText(requireContext(), "Record your micro-story at this location", Toast.LENGTH_SHORT).show();
        });
    }
    
    private void setupBudgetSlider() {
        budgetSlider.setValues(1000f, 3000f);
        
        budgetSlider.addOnChangeListener((slider, value, fromUser) -> {
            List<Float> values = slider.getValues();
            labelBudget.setText(String.format(Locale.getDefault(), 
                    "Budget: ₹%.0f - ₹%.0f", values.get(0), values.get(1)));
        });
    }
    
    private void setupMicroStories() {
        // In a real app, this would load from a repository
        List<MicroStory> stories = createSampleMicroStories();
        
        MicroStoryAdapter adapter = new MicroStoryAdapter(requireContext(), stories);
        nearbyStoriesList.setAdapter(adapter);
    }
    
    private List<MicroStory> createSampleMicroStories() {
        List<MicroStory> stories = new ArrayList<>();
        
        stories.add(new MicroStory(
                "Hidden Chai Spot",
                "audio",
                "Discover this amazing chai stall tucked away in an alley",
                150,
                null
        ));
        
        stories.add(new MicroStory(
                "Sunset Point Secret",
                "video",
                "How to find the hidden path to the best sunset view",
                350,
                null
        ));
        
        stories.add(new MicroStory(
                "Local Market Tip",
                "audio",
                "Bargaining tips from a local shop owner",
                200,
                null
        ));
        
        return stories;
    }
} 