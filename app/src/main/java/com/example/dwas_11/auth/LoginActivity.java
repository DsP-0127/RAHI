package com.example.dwas_11.auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dwas_11.MainActivity;
import com.example.dwas_11.R;
import com.example.dwas_11.model.User;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText emailInput;
    private TextInputEditText passwordInput;
    private MaterialButton loginButton;
    private MaterialButton googleLoginButton;
    private MaterialButton facebookLoginButton;
    private TextView forgotPassword;
    private TextView signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize views
        emailInput = findViewById(R.id.email_input);
        passwordInput = findViewById(R.id.password_input);
        loginButton = findViewById(R.id.login_button);
        googleLoginButton = findViewById(R.id.google_login_button);
        facebookLoginButton = findViewById(R.id.facebook_login_button);
        forgotPassword = findViewById(R.id.forgot_password);
        signUpButton = findViewById(R.id.sign_up_button);

        // Set click listeners
        loginButton.setOnClickListener(v -> handleLoginClick());
        googleLoginButton.setOnClickListener(v -> handleGoogleLogin());
        facebookLoginButton.setOnClickListener(v -> handleFacebookLogin());
        forgotPassword.setOnClickListener(v -> handleForgotPassword());
        signUpButton.setOnClickListener(v -> handleSignUp());
    }

    private void handleLoginClick() {
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();

        if (validateForm(email, password)) {
            // In a real app, this would authenticate with a backend
            loginUser(email, password);
        }
    }

    private boolean validateForm(String email, String password) {
        boolean valid = true;

        if (TextUtils.isEmpty(email)) {
            emailInput.setError("Email is required");
            valid = false;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailInput.setError("Please enter a valid email address");
            valid = false;
        } else {
            emailInput.setError(null);
        }

        if (TextUtils.isEmpty(password)) {
            passwordInput.setError("Password is required");
            valid = false;
        } else if (password.length() < 6) {
            passwordInput.setError("Password must be at least 6 characters");
            valid = false;
        } else {
            passwordInput.setError(null);
        }

        return valid;
    }

    private void loginUser(String email, String password) {
        // For demo, just create a user and move to MainActivity
        // In a real app, this would authenticate with Firebase, etc.
        Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
        
        // Create a mock user
        User user = new User("user_123", email.split("@")[0], email);
        
        // Navigate to main activity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void handleGoogleLogin() {
        // In a real app, this would use the Google Sign-In API
        Toast.makeText(this, "Google login clicked", Toast.LENGTH_SHORT).show();
        
        // For demo, just create a user and move to MainActivity
        User user = new User("google_user_123", "Google User", "google.user@example.com");
        user.setAuthProvider("GOOGLE");
        
        // Navigate to main activity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void handleFacebookLogin() {
        // In a real app, this would use the Facebook Login SDK
        Toast.makeText(this, "Facebook login clicked", Toast.LENGTH_SHORT).show();
        
        // For demo, just create a user and move to MainActivity
        User user = new User("fb_user_123", "Facebook User", "facebook.user@example.com");
        user.setAuthProvider("FACEBOOK");
        
        // Navigate to main activity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void handleForgotPassword() {
        // In a real app, this would navigate to a password reset flow
        Toast.makeText(this, "Forgot password clicked", Toast.LENGTH_SHORT).show();
    }

    private void handleSignUp() {
        // In a real app, this would navigate to the sign up screen
        Toast.makeText(this, "Sign up clicked", Toast.LENGTH_SHORT).show();
        
        // Navigate to sign up activity (would be implemented in a real app)
        // Intent intent = new Intent(this, SignUpActivity.class);
        // startActivity(intent);
    }
} 