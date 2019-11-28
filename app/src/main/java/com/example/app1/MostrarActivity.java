package com.example.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MostrarActivity extends AppCompatActivity {

    ListView listViewUsuarios;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);
        inicializar();
        ArrayList<Alumno> alumnos = dbHelper.getAllUsuarios();
        UsuarioAdapter adapter = new UsuarioAdapter(this, R.layout.list_item_user, alumnos);
        listViewUsuarios.setAdapter(adapter);
        listViewUsuarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Alumno u = (Alumno) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), "ID: " + u.getId(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void inicializar() {
        dbHelper = new DBHelper(this);
        listViewUsuarios = findViewById(R.id.mostrar_listView_usuarios);
    }
}
