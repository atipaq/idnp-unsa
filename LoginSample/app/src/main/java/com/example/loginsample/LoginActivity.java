package com.example.loginsample;

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
    private ActivityResultLauncher<Intent> accountActivityLauncher;
    private AccountEntity accountEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        accountActivityLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null && data.hasExtra("account")) {
                            accountEntity = (AccountEntity) data.getSerializableExtra("account");
                            EditText edtUsername = findViewById(R.id.edtUsername);
                            EditText edtPassword = findViewById(R.id.edtPassword);
                            edtUsername.setText(accountEntity.getUsername());
                            edtPassword.setText(accountEntity.getPassword());
                        }
                    } else if (result.getResultCode() == RESULT_CANCELED) {
                        Toast.makeText(LoginActivity.this, "Registro cancelado", Toast.LENGTH_SHORT).show();
                        Log.d("LogActivity", "Registro cancelado");
                    }
                });

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        EditText edtUsername = binding.edtUsername;
        EditText edtPassword = binding.edtPassword;
        Button btnLogin = binding.btnLogin;
        Button btnAddAccount = binding.btnAddAccount;

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputUsername = edtUsername.getText().toString();
                String inputPassword = edtPassword.getText().toString();

                if (accountEntity != null &&
                        inputUsername.equals(accountEntity.getUsername()) &&
                        inputPassword.equals(accountEntity.getPassword())) {
                    Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                    Log.d("LogActivity", "Login Success");

                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    intent.putExtra("account", accountEntity);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                    Log.d("LogActivity", "Authentication Failed");
                }
            }
        });

        btnAddAccount.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), AccountActivity.class);
            accountActivityLauncher.launch(intent);
        });
    }
}
