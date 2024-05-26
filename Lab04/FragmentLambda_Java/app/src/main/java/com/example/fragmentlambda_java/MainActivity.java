package com.example.fragmentlambda_java;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity implements RegisterFragment.OnUserRegisteredListener {
    private TextView txtMensaje;
    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtMensaje = findViewById(R.id.txtMensaje);
        Button btnShowRegister = findViewById(R.id.btnShowRegister);
        Button btnShowUserDetails = findViewById(R.id.btnShowUserDetails);

        btnShowRegister.setOnClickListener(v -> {
            RegisterFragment registerFragment = RegisterFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameLayout, registerFragment)
                    .commit();
        });

        btnShowUserDetails.setOnClickListener(v -> {
            UserDetailsFragment userDetailsFragment = UserDetailsFragment.newInstance(currentUser);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameLayout, userDetailsFragment)
                    .commit();
        });
    }

    @Override
    public void onUserRegistered(User user) {
        currentUser = user;
        txtMensaje.setText("User Registered: " + user.getUsername());
    }
}
