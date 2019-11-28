package com.example.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrarActivity extends AppCompatActivity {

    private EditText editTextNombre, editTextNumeroCuenta, editTextEmail, editTextPassword;
    private Button buttonRegistrar, buttonCancelar;
    private RadioGroup radioGroupGenero;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        inicializar();

        buttonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        buttonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Alumno u = generarUsuario();
                if (u != null) {
                    boolean check = dbHelper.insertUser(u);
                    if (check) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Ya existe el usuario con el correo: " +u.getEmail(), Toast.LENGTH_LONG).show();

                    }
                }
            }
        });
    }

    private void inicializar() {
        editTextNombre = findViewById(R.id.registrar_editText_nombre);
        editTextNumeroCuenta = findViewById(R.id.registrar_editText_numeroCuenta);
        editTextEmail = findViewById(R.id.registrar_editText_email);
        editTextPassword = findViewById(R.id.registrar_editText_password);
        buttonRegistrar = findViewById(R.id.registrar_button_registrar);
        buttonCancelar = findViewById(R.id.registrar_button_cancelar);
        radioGroupGenero = findViewById(R.id.registrar_radioGroup_genero);
        dbHelper = new DBHelper(this);
    }

    private Alumno generarUsuario() {
        boolean error = false;
        String nombre = editTextNombre.getText().toString();
        String numeroCuenta = editTextNumeroCuenta.getText().toString();
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        String genero = "";
        String errorMensaje = "Error en: ";
        if (!verificarNombre(nombre)){
            errorMensaje += " Nombre ";
            error = true;
        }
        if (!verificarNumeroCuenta(numeroCuenta)) {
            errorMensaje += " NumeroCuenta ";
            error = true;
        }
        if (!verificarEmail(email)) {
            errorMensaje += " Email ";
            error = true;
        }

        if (radioGroupGenero.getCheckedRadioButtonId() == R.id.registro_radio_femenino) {
            genero = "Femenino";
        } else if (radioGroupGenero.getCheckedRadioButtonId() == R.id.registro_radio_masculino) {
            genero = "Masculino";
        }

        if (!error) {
            return new Alumno(nombre, numeroCuenta, email, password, genero);
        } else {
            Toast t = Toast.makeText(this, errorMensaje, Toast.LENGTH_LONG);
            t.show();
            return null;
        }
    }

    private boolean verificarNombre(String nombre) {
        if (TextUtils.isEmpty(nombre)) {
            return false;
        }
        Pattern p = Pattern.compile("^[A-Za-z\\s]+$*");
        Matcher m = p.matcher(nombre);
        return m.matches();
    }

    private boolean verificarEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean verificarNumeroCuenta(String numeroCuenta) {
        if (TextUtils.isEmpty(numeroCuenta)) {
            return false;
        }
        Pattern p = Pattern.compile("[0-9]{9}");
        Matcher m = p.matcher(numeroCuenta);
        return m.matches();
    }

}
