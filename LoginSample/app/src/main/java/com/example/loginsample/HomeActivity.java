package com.example.loginsample;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Obtener el nombre de usuario enviado desde LoginActivity
        String username = getIntent().getStringExtra("USERNAME");

        // Mostrar el nombre de usuario en el TextView de bienvenida
        TextView txtWelcomeMessage = findViewById(R.id.txtWelcomeMessage);
        txtWelcomeMessage.setText("Bienvenido " + username);
    }
}
