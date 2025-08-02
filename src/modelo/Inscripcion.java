/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;

public class Inscripcion implements Serializable {

    private String idActividad;
    private String nombreActividad;
    private String horario;
    private String instructor;
    private boolean equipoAdquirido;
    private double costoActividad;
    private double costoEquipo;

    public Inscripcion(String idActividad, String nombreActividad, String horario,
            String instructor, boolean equipoAdquirido,
            double costoActividad, double costoEquipo) {
        this.idActividad = idActividad;
        this.nombreActividad = nombreActividad;
        this.horario = horario;
        this.instructor = instructor;
        this.equipoAdquirido = equipoAdquirido;
        this.costoActividad = costoActividad;
        this.costoEquipo = costoEquipo;
    }

    // Getters y Setters
    public String getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(String idActividad) {
        this.idActividad = idActividad;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
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

    public boolean isEquipoAdquirido() {
        return equipoAdquirido;
    }

    public void setEquipoAdquirido(boolean equipoAdquirido) {
        this.equipoAdquirido = equipoAdquirido;
    }

    public double getCostoActividad() {
        return costoActividad;
    }

    public void setCostoActividad(double costoActividad) {
        this.costoActividad = costoActividad;
    }

    public double getCostoEquipo() {
        return costoEquipo;
    }

    public void setCostoEquipo(double costoEquipo) {
        this.costoEquipo = costoEquipo;
    }

    // Método para calcular costo total
    public double getCostoTotal() {
        return costoActividad + (equipoAdquirido ? costoEquipo : 0);
    }
    
    
}
