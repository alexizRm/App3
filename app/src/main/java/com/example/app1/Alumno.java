package com.example.app1;

public class Alumno {
    long id;
    private String nombre;
    private String numeroCuenta;
    private String genero;
    private String email;
    private String password;

    public Alumno() {
    }

    public Alumno(long id, String nombre, String numeroCuenta, String email, String password, String genero) {
        this.id = id;
        this.nombre = nombre;
        this.genero = genero;
        this.numeroCuenta = numeroCuenta;
        this.email = email;
        this.password = password;
    }

    public Alumno(String nombre, String numeroCuenta, String email, String password, String genero) {
        this(-1, nombre, numeroCuenta, email, password, genero);
    }

    public Alumno(long id, String nombre, String numeroCuenta, String email, String genero) {
        this(id, nombre, numeroCuenta, email, "", genero);
    }

    public boolean isNull () {
        if(nombre.equals("") && numeroCuenta.equals("") && email.equals("") &&
                password.equals("")) {
            return false;
        }else{
            return true;
        }

    }

    @Override
    public String toString() {
        return "Alumno{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", numeroCuenta='" + numeroCuenta + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

}
