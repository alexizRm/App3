package com.example.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button buttonEntrar, buttonRegistrar;
    private EditText editTextEmail, editTextPassword;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializar();

        buttonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegistrarActivity.class);
                startActivity(intent);
            }
        });

        buttonEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarUsuario();
            }
        });
    }

    public void inicializar() {
        buttonEntrar = findViewById(R.id.main_button_entrar);
        buttonRegistrar = findViewById(R.id.main_button_registrar);
        editTextEmail = findViewById(R.id.main_editText_email);
        editTextPassword = findViewById(R.id.main_editText_password);
        dbHelper = new DBHelper(this);
    }

    // Todo: Implementar lógica para checar login de usuario
    private void verificarUsuario() {
        String email = editTextEmail.getText().toString();
        String password  = editTextPassword.getText().toString();
        String storedPassword = dbHelper.getPassword(email);
        if (storedPassword == null) {
            Toast.makeText(this, "No está registrado el email: " + email, Toast.LENGTH_LONG).show();
        } else {
            if (storedPassword.equals(password)) {
                Intent intent = new Intent(this, ActivityInicio.class);
                intent.putExtra("email", email);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
