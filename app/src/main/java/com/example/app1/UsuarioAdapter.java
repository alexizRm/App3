package com.example.app1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class UsuarioAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Alumno> alumnos;

    public UsuarioAdapter(Context context, int layout, ArrayList<Alumno> alumnos) {
        this.context = context;
        this.layout = layout;
        this.alumnos = alumnos;
    }

    public int getCount() {
        return this.alumnos.size();
    }

    public Object getItem(int position) {
        return this.alumnos.get(position);
    }

    public long getItemId(int id) {
        return id;
    }

    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View v = convertView;

        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        v = layoutInflater.inflate(R.layout.list_item_user, null);
        Alumno u = alumnos.get(position);
        ImageView imagen = (ImageView) v.findViewById(R.id.list_item_imageView_genero);
        TextView nombre = (TextView) v.findViewById(R.id.list_item_textView_nombre);
        TextView numeroCuenta = (TextView) v.findViewById(R.id.list_item_textView_numero_cuenta);
        switch (u.getGenero()) {
            case "Masculino":
                imagen.setImageResource(R.mipmap.masculino);
                break;
            case "Femenino":
                imagen.setImageResource(R.mipmap.femenino);
                break;
            default:
                imagen.setImageResource(R.mipmap.fi);
        }
        nombre.setText(u.getNombre());
        numeroCuenta.setText(u.getNumeroCuenta());
        return v;
    }
}

