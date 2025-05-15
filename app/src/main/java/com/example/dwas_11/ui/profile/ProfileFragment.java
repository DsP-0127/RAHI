package com.example.dwas_11.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.dwas_11.R;
import com.example.dwas_11.model.User;
import com.google.android.material.tabs.TabLayout;

public class ProfileFragment extends Fragment {
    
    private TextView profileName;
    private TextView profileEmail;
    private ImageView profileImageView;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    
    // This would come from login/auth in a real app
    private User currentUser;
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        
        // Initialize UI components
        profileName = view.findViewById(R.id.profile_name);
        profileEmail = view.findViewById(R.id.profile_email);
        profileImageView = view.findViewById(R.id.profile_image);
        viewPager = view.findViewById(R.id.profile_viewpager);
        tabLayout = view.findViewById(R.id.profile_tabs);
        
        // Set profile image
        profileImageView.setImageResource(R.drawable.profile_image);
        
        // Create a sample user (in a real app, this would be retrieved from session/database)
        currentUser = new User("user123", "John Doe", "john.doe@example.com");
        
        // Set user data
        updateUI();
        
        // Setup ViewPager
        setupViewPager();
        
        return view;
    }
    
    private void updateUI() {
        profileName.setText(currentUser.getName());
        profileEmail.setText(currentUser.getEmail());
    }
    
    private void setupViewPager() {
        ProfileTabAdapter adapter = new ProfileTabAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
} 