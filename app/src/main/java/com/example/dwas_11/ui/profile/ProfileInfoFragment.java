package com.example.dwas_11.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dwas_11.R;

public class ProfileInfoFragment extends Fragment {
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_info, container, false);
        
        // Setup click listeners
        setupClickListeners(view);
        
        return view;
    }
    
    private void setupClickListeners(View view) {
        // Travel preferences
        view.findViewById(R.id.edit_preferences).setOnClickListener(v -> {
            Toast.makeText(getActivity(), "Edit preferences clicked", Toast.LENGTH_SHORT).show();
            // This would open a preferences editor in a real app
        });
        
        // Settings options
        view.findViewById(R.id.notification_settings).setOnClickListener(v -> {
            Toast.makeText(getActivity(), "Notification settings clicked", Toast.LENGTH_SHORT).show();
            // This would open notification settings in a real app
        });
        
        view.findViewById(R.id.privacy_settings).setOnClickListener(v -> {
            Toast.makeText(getActivity(), "Privacy settings clicked", Toast.LENGTH_SHORT).show();
            // This would open privacy settings in a real app
        });
        
        view.findViewById(R.id.language_settings).setOnClickListener(v -> {
            Toast.makeText(getActivity(), "Language settings clicked", Toast.LENGTH_SHORT).show();
            // This would open language settings in a real app
        });
        
        // Logout button
        view.findViewById(R.id.logout_button).setOnClickListener(v -> {
            Toast.makeText(getActivity(), "Logging out...", Toast.LENGTH_SHORT).show();
            // In a real app, this would clear user session and navigate to login screen
        });
    }
} 