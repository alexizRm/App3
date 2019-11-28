package com.example.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ActivityInicio extends AppCompatActivity {

    Button buttonEliminar, buttonMostrarUsuarios, buttonCerrarSesion;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        inicializar();

        buttonCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        buttonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*String email = getIntent().getStringExtra("email");
                if (dbHelper.eliminarUsuario(email) > 0) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "No se pudo eleminar a: " + email, Toast.LENGTH_LONG).show();
                }*/

                Toast.makeText(getApplicationContext(), "Aun no implementado", Toast.LENGTH_LONG).show();
            }
        });

        buttonMostrarUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MostrarActivity.class);
                startActivity(intent);
            }
        });
    }

    private void inicializar() {
        dbHelper = new DBHelper(this);
        buttonEliminar = findViewById(R.id.inicio_button_eliminar);
        buttonCerrarSesion = findViewById(R.id.inicio_button_cerrar_sesion);
        buttonMostrarUsuarios = findViewById(R.id.inicio_button_mostrar_usuarios);
    }

}
