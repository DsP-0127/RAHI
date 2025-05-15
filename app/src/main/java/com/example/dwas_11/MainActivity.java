package com.example.dwas_11;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.navigation.fragment.NavHostFragment;

import com.example.dwas_11.model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private NavController navController;
    private User currentUser;
    private ImageButton weatherButton;
    private ImageButton languageButton;
    private ImageButton notificationButton;
    private ImageButton profileButton;
    private ImageButton soloTravelerButton;
    private ImageView toolbarLogo;
    private TextView appTitle;
    private String currentLanguage = "en"; // Default language
    private Dialog weatherDialog;
    private Dialog languageDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        setupViews();
        
        // Setup user (mock data for now)
        setupUser();
        
        // Setup navigation controller
        setupNavigation();
    }

    private void setupViews() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        
        weatherButton = findViewById(R.id.weather_button);
        languageButton = findViewById(R.id.language_button);
        notificationButton = findViewById(R.id.notification_button);
        profileButton = findViewById(R.id.profile_button);
        soloTravelerButton = findViewById(R.id.favorites_button);
        toolbarLogo = findViewById(R.id.toolbar_logo);
        appTitle = findViewById(R.id.app_title);
        
        // Setup click listeners
        weatherButton.setOnClickListener(v -> showWeatherDialog());
        languageButton.setOnClickListener(v -> showLanguageSelector());
        notificationButton.setOnClickListener(v -> {
            Toast.makeText(this, "Notifications", Toast.LENGTH_SHORT).show();
            // In a real app, navigate to notifications screen or show notifications panel
        });
        profileButton.setOnClickListener(v -> {
            // Navigate to profile page
            navController.navigate(R.id.navigation_profile);
        });
        soloTravelerButton.setOnClickListener(v -> {
            // Navigate to solo traveler page
            navController.navigate(R.id.navigation_solo_traveler);
        });
        toolbarLogo.setOnClickListener(v -> {
            // Navigate to home page
            navController.navigate(R.id.navigation_home);
        });
    }
    
    private void setupUser() {
        // Create a mock user - in a real app this would come from login/auth
        currentUser = new User("user_123", "John Doe", "john.doe@example.com");
    }

    private void setupNavigation() {
        BottomNavigationView navView = findViewById(R.id.bottom_nav_view);
        
        // Change how we get the NavController
        navController = ((NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment))
                .getNavController();
        
        // Setup the top level destinations so the app doesn't show back button for them
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_explore, 
                R.id.navigation_trips, R.id.navigation_profile,
                R.id.navigation_solo_traveler)
                .build();

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        
        // Add navigation listener to adjust toolbar based on destination
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            int id = destination.getId();
            // Show app logo only on home screen
            toolbarLogo.setVisibility(id == R.id.navigation_home ? View.VISIBLE : View.GONE);
            
            // Set toolbar title
            if (id == R.id.navigation_solo_traveler) {
                appTitle.setText("Solo Travel");
            } else {
                appTitle.setText("RAHI");
            }
        });
    }
    
    private void showWeatherDialog() {
        weatherDialog = new Dialog(this);
        weatherDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        weatherDialog.setContentView(R.layout.dialog_weather);
        weatherDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        
        // Set weather information
        TextView weatherLocation = weatherDialog.findViewById(R.id.weather_location_name);
        TextView weatherTemp = weatherDialog.findViewById(R.id.weather_temp_value);
        TextView weatherCondition = weatherDialog.findViewById(R.id.weather_condition_text);
        TextView lastUpdated = weatherDialog.findViewById(R.id.weather_update_time);
        
        // Set values (would come from weather API in a real app)
        weatherLocation.setText("Current Location");
        weatherTemp.setText("22°C");
        weatherCondition.setText("Sunny");
        
        // Format current time for "last updated"
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
        lastUpdated.setText("Last updated " + sdf.format(new Date()));
        
        // Set button click listeners
        Button refreshButton = weatherDialog.findViewById(R.id.weather_refresh_button);
        Button closeButton = weatherDialog.findViewById(R.id.weather_close_button);
        
        refreshButton.setOnClickListener(v -> {
            Toast.makeText(this, "Refreshing weather data...", Toast.LENGTH_SHORT).show();
            // In a real app, this would refresh the weather data from API
        });
        
        closeButton.setOnClickListener(v -> weatherDialog.dismiss());
        
        weatherDialog.show();
    }
    
    private void showLanguageSelector() {
        languageDialog = new Dialog(this);
        languageDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        languageDialog.setContentView(R.layout.dialog_language_selector);
        languageDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        
        RadioGroup languageGroup = languageDialog.findViewById(R.id.language_radio_group);
        Button cancelButton = languageDialog.findViewById(R.id.language_cancel_button);
        Button applyButton = languageDialog.findViewById(R.id.language_apply_button);
        
        // Set current language
        switch (currentLanguage) {
            case "en":
                languageGroup.check(R.id.lang_english);
                break;
            case "hi":
                languageGroup.check(R.id.lang_hindi);
                break;
            case "bn":
                languageGroup.check(R.id.lang_bengali);
                break;
            case "ta":
                languageGroup.check(R.id.lang_tamil);
                break;
            case "te":
                languageGroup.check(R.id.lang_telugu);
                break;
        }
        
        // Set button click listeners
        cancelButton.setOnClickListener(v -> languageDialog.dismiss());
        
        applyButton.setOnClickListener(v -> {
            int selectedId = languageGroup.getCheckedRadioButtonId();
            String newLanguage = "en"; // Default to English
            
            if (selectedId == R.id.lang_hindi) {
                newLanguage = "hi";
            } else if (selectedId == R.id.lang_bengali) {
                newLanguage = "bn";
            } else if (selectedId == R.id.lang_tamil) {
                newLanguage = "ta";
            } else if (selectedId == R.id.lang_telugu) {
                newLanguage = "te";
            }
            
            if (!currentLanguage.equals(newLanguage)) {
                currentLanguage = newLanguage;
                // In a real app, apply the language change
                Toast.makeText(this, "Language changed", Toast.LENGTH_SHORT).show();
                // Would typically call a method to update locale, recreate activity
            }
            
            languageDialog.dismiss();
        });
        
        languageDialog.show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp() || super.onSupportNavigateUp();
    }
} 