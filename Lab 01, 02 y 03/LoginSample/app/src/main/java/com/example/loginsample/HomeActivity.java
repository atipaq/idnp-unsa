package com.example.loginsample;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        AccountEntity accountEntity = (AccountEntity) getIntent().getSerializableExtra("account");

        TextView txtWelcomeMessage = findViewById(R.id.txtWelcomeMessage);
        if (accountEntity != null) {
            String welcomeMessage = "Bienvenido " + accountEntity.getUsername() + "\n" +
                    "Nombre: " + accountEntity.getFirstname() + "\n" +
                    "Apellido: " + accountEntity.getLastname() + "\n" +
                    "Email: " + accountEntity.getEmail() + "\n" +
                    "Teléfono: " + accountEntity.getPhone();
            txtWelcomeMessage.setText(welcomeMessage);
        } else {
            txtWelcomeMessage.setText("Bienvenido");
        }
    }
}
