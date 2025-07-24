/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Asistente implements Serializable {

    private String id;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private int edad;
    private String genero;
    private String direccion;
    private String telefono;
    private String telefonoEmergencia;
    private List<Inscripcion> inscripciones;

    public Asistente() {
        this.inscripciones = new ArrayList<>();
    }

    public Asistente(String id, String nombre, String primerApellido, String segundoApellido,
            int edad, String genero, String direccion, String telefono,
            String telefonoEmergencia) {
        this();
        this.id = id;
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.edad = edad;
        this.genero = genero;
        this.direccion = direccion;
        this.telefono = telefono;
        this.telefonoEmergencia = telefonoEmergencia;
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

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTelefonoEmergencia() {
        return telefonoEmergencia;
    }

    public void setTelefonoEmergencia(String telefonoEmergencia) {
        this.telefonoEmergencia = telefonoEmergencia;
    }

    public List<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(List<Inscripcion> inscripciones) {
        this.inscripciones = inscripciones;
    }

    // Métodos adicionales
    public void agregarInscripcion(Inscripcion inscripcion) {
        this.inscripciones.add(inscripcion);
    }

    public boolean eliminarInscripcion(String idActividad) {
        for (Inscripcion inscripcion : inscripciones) {
            if (inscripcion.getIdActividad().equals(idActividad)) {
                inscripciones.remove(inscripcion);
                return true;
            }
        }
        return false;
    }

    public double calcularMensualidadTotal() {
        double total = 0.0;
        for (Inscripcion inscripcion : inscripciones) {
            total += inscripcion.getCostoTotal();
        }
        return total;
    }
}
