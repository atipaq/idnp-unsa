package com.example.loginsample;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        EditText edtFirstname = findViewById(R.id.edtFirstname);
        EditText edtLastname = findViewById(R.id.edtLastname);
        EditText edtEmail = findViewById(R.id.edtEmail);
        EditText edtPhone = findViewById(R.id.edtPhone);
        EditText edtUsername2 = findViewById(R.id.edtUsername2);
        EditText edtPassword2 = findViewById(R.id.edtPassword2);
        Button btnRegister = findViewById(R.id.btnRegister);
        Button btnCancel = findViewById(R.id.btnCancel);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstname = edtFirstname.getText().toString();
                String lastname = edtLastname.getText().toString();
                String email = edtEmail.getText().toString();
                String phone = edtPhone.getText().toString();
                String username = edtUsername2.getText().toString();
                String password = edtPassword2.getText().toString();

                Log.d("RegActivity", "Firstname: " + firstname);
                Log.d("RegActivity", "Lastname: " + lastname);
                Log.d("RegActivity", "Email: " + email);
                Log.d("RegActivity", "Phone: " + phone);
                Log.d("RegActivity", "Username: " + username);
                Log.d("RegActivity", "Password: " + password);

                AccountEntity accountEntity = new AccountEntity();
                accountEntity.setFirstname(firstname);
                accountEntity.setLastname(lastname);
                accountEntity.setEmail(email);
                accountEntity.setPhone(phone);
                accountEntity.setUsername(username);
                accountEntity.setPassword(password);

                Intent resultIntent = new Intent();
                resultIntent.putExtra("account", accountEntity);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
}
