package com.example.proyectoandroid.entity;

public class Student {
    private String nombre;
    private String seccion;

    public Student() {
        //constructor
    }

    public Student(String nombre, String seccion) {
        this.nombre = nombre;
        this.seccion = seccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }
}
