package com.example.infante.myapplication.entidades;
//hecho por infante 20180804
//del video turorial de Cristian Henao
//        https://youtu.be/MOE_Hokh_O0
import java.io.Serializable;

public class Mascota implements Serializable{

    private Integer idDuenio;
    private Integer idMascota;
    private String nombreMascota;
    private String raza;

    public Mascota() {
    }

    public Mascota(Integer idDuenio, Integer idMascota, String nombreMascota, String raza) {
        this.idDuenio = idDuenio;
        this.idMascota = idMascota;
        this.nombreMascota = nombreMascota;
        this.raza = raza;
    }

    public Integer getIdDuenio() {
        return idDuenio;
    }

    public void setIdDuenio(Integer idDuenio) {
        this.idDuenio = idDuenio;
    }

    public Integer getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(Integer idMascota) {
        this.idMascota = idMascota;
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }
}
