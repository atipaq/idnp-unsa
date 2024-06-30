package com.example.lab07;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enableEdgeToEdge();
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, RegisterFragment.newInstance())
                    .commitNow();
        }
    }

    private void enableEdgeToEdge() {
        // Implementación de edge-to-edge según tus necesidades
        // Por ejemplo, para habilitar el borde a borde en la ventana:
        // getWindow().getInsetsController().show(WindowInsets.Type.statusBars() | WindowInsets.Type.navigationBars());
    }
}
