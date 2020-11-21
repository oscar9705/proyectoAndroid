package com.example.proyectoandroid.entity;

import java.util.List;

public class User {

    private Integer cedula;
    private String nombre;
    private String correo;
    private String telefono;
    private String password;
    private List<Student> alumnos;

    public User() {
        //constructor
    }

    public User(Integer cedula, String nombre, String correo, String telefono, String password, List<Student> alumnos) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.password = password;
        this.alumnos = alumnos;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getCedula() {
        return cedula;
    }

    public void setCedula(Integer cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<Student> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<Student> alumnos) {
        this.alumnos = alumnos;
    }
}
