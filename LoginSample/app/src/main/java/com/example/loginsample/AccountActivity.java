package com.example.loginsample;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AccountActivity extends androidx.appcompat.app.AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Creando un objeto AccountEntity
        setContentView(R.layout.activity_account);

        EditText edtFirstname = findViewById(R.id.edtFirstname);
        EditText edtLastname = findViewById(R.id.edtLastname);
        EditText edtEmail = findViewById(R.id.edtEmail);
        EditText edtPhone = findViewById(R.id.edtPhone);
        EditText edtUsername2 = findViewById(R.id.edtUsername2);
        EditText edtPassword2 = findViewById(R.id.edtPassword2);
        Button btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener datos del formulario
                String firstname = edtFirstname.getText().toString();
                String lastname = edtLastname.getText().toString();
                String email = edtEmail.getText().toString();
                String phone = edtPhone.getText().toString();
                String username = edtUsername2.getText().toString();
                String password = edtPassword2.getText().toString();

                // Registro de los valores obtenidos
                Log.d("RegActivity", "Firstname: " + firstname);
                Log.d("RegActivity", "Lastname: " + lastname);
                Log.d("RegActivity", "Email: " + email);
                Log.d("RegActivity", "Phone: " + phone);
                Log.d("RegActivity", "Username: " + username);
                Log.d("RegActivity", "Password: " + password);

                // Crear objeto AccountEntity
                AccountEntity accountEntity = new AccountEntity();
                accountEntity.setFirstname(firstname);
                accountEntity.setLastname(lastname);
                accountEntity.setEmail(email);
                accountEntity.setPhone(phone);
                accountEntity.setUsername(username);
                accountEntity.setPassword(password);

                // Pasar objeto AccountEntity a LoginActivity
                Intent intent = new Intent();
                intent.putExtra("account", accountEntity);
                setResult(RESULT_OK, intent);
                finish();

            }
        });

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}