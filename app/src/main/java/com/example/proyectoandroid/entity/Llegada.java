package com.example.proyectoandroid.entity;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Llegada {

    private String correo;
    private String estado;
    private String carril;
    private Long fecha;
    private List<Student> alumnos;



    public Llegada(String correo, String estado, String carril, Long fecha,List<Student> alumnos) {
        this.correo = correo;
        this.estado = estado;
        this.carril = carril;
        this.fecha = fecha;
        this.alumnos = alumnos;
    }
    public Llegada(String correo, String estado, String carril,List<Student> alumnos) {
        this.correo = correo;
        this.estado = estado;
        this.carril = carril;
        this.fecha = new Date().getTime();
        this.alumnos = alumnos;
    }

    public Llegada() {
        // Constructor
    }

    public List<Student> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<Student> alumnos) {
        this.alumnos = alumnos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCarril() {
        return carril;
    }

    public void setCarril(String carril) {
        this.carril = carril;
    }

    public Long getFecha() {
        return fecha;
    }

    public void setFecha(Long fecha) {
        this.fecha = fecha;
    }
}
