/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;

public class Actividad implements Serializable {

    private String id;
    private String nombre;
    private String horario;
    private String instructor;
    private double costo;
    private double costoEquipo;
    private int cupoDisponible;

    public Actividad(String id, String nombre, String horario, String instructor,
            double costo, double costoEquipo, int cupoDisponible) {
        this.id = id;
        this.nombre = nombre;
        this.horario = horario;
        this.instructor = instructor;
        this.costo = costo;
        this.costoEquipo = costoEquipo;
        this.cupoDisponible = cupoDisponible;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public double getCostoEquipo() {
        return costoEquipo;
    }

    public void setCostoEquipo(double costoEquipo) {
        this.costoEquipo = costoEquipo;
    }

    public int getCupoDisponible() {
        return cupoDisponible;
    }

    public void setCupoDisponible(int cupoDisponible) {
        this.cupoDisponible = cupoDisponible;
    }

    // Métodos para gestión de cupos
    public boolean reducirCupo() {
        if (cupoDisponible > 0) {
            cupoDisponible--;
            return true;
        }
        return false;
    }

    public void aumentarCupo() {
        cupoDisponible++;
    }
}
