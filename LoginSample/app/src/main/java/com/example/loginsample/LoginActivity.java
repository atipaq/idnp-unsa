package com.example.loginsample;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.loginsample.databinding.ActivityMainBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    // Declara el ActivityResultLauncher fuera del método onCreate
    private ActivityResultLauncher<Intent> accountActivityLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/

        // Inicializa el ActivityResultLauncher
        accountActivityLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null && data.hasExtra("account")) {
                            AccountEntity accountEntity = (AccountEntity) data.getSerializableExtra("account");
                            // Mostrar los datos en los EditText
                            EditText edtUsername = findViewById(R.id.edtUsername);
                            EditText edtPassword = findViewById(R.id.edtPassword);

                            // Establecer los datos en los EditText
                            edtUsername.setText(accountEntity.getUsername());
                            edtPassword.setText(accountEntity.getPassword());
                        }
                    }
                });

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        EditText edtUsername = binding.edtUsername;
        EditText edtPassword = binding.edtPassword;
        Button btnLogin = binding.btnLogin;
        Button btnAddAccount = binding.btnAddAccount;


        btnLogin.setOnClickListener(v -> {
            // Obtener los datos del usuario y contraseña del objeto AccountEntity
            String username = edtUsername.getText().toString();
            String password = edtPassword.getText().toString();

            // Verificar si los datos son válidos (en este ejemplo, simplemente comprobamos si no son nulos)
            if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                intent.putExtra("USERNAME", edtUsername.getText().toString());
                startActivity(intent);
                finish();

                // Iniciar sesión con los datos recibidos del objeto AccountEntity
                Toast.makeText(LoginActivity.this, "Logging in with username: " + username, Toast.LENGTH_SHORT).show();
                Log.d("LogActivity", "Logging in with username: " + username);
            } else {
                // Mostrar un mensaje de error si los datos son inválidos
                Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                Log.d("LogActivity", "Invalid username or password");
            }
        });


        btnAddAccount.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), AccountActivity.class);
            accountActivityLauncher.launch(intent);
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (data != null && data.hasExtra("account")) {
                AccountEntity accountEntity = (AccountEntity) data.getSerializableExtra("account");
                // Mostrar los datos en los EditText
                EditText edtUsername = findViewById(R.id.edtUsername);
                EditText edtPassword = findViewById(R.id.edtPassword);

                // Establecer los datos en los EditText
                edtUsername.setText(accountEntity.getUsername());
                edtPassword.setText(accountEntity.getPassword());
            }
        }
    }

}