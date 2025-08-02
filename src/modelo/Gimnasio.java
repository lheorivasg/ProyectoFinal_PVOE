/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Gimnasio implements Serializable {
    private List<Asistente> asistentes;
    private List<Actividad> actividades;
    private List<Usuario> usuarios;

    public Gimnasio() {
        this.asistentes = new ArrayList<>();
        this.actividades = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        inicializarDatosPrueba();
    }

    private void inicializarDatosPrueba() {
        // Solo actividades de prueba, los usuarios se cargan desde archivo
        actividades.add(new Actividad("ACT001", "Acondicionamiento físico", 
                "Lunes y Miércoles 8:00-9:00", "Juan Pérez", 500.00, 200.00, 20));
        actividades.add(new Actividad("ACT002", "Gimnasia Olímpica", 
                "Martes y Jueves 16:00-17:30", "María García", 600.00, 350.00, 15));
        actividades.add(new Actividad("ACT003", "Taekwondo", 
                "Lunes, Miércoles y Viernes 18:00-19:00", "Carlos López", 550.00, 300.00, 25));
    }

    public List<Asistente> getAsistentes() {
        return asistentes;
    }

    public List<Actividad> getActividades() {
        return actividades;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public boolean agregarAsistente(Asistente asistente) {
        if (asistente != null && !existeAsistente(asistente.getId())) {
            asistentes.add(asistente);
            return true;
        }
        return false;
    }

    public boolean existeAsistente(String id) {
        for (Asistente a : asistentes) {
            if (a.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public Actividad buscarActividad(String id) {
        for (Actividad a : actividades) {
            if (a.getId().equals(id)) {
                return a;
            }
        }
        return null;
    }

    public Usuario autenticarUsuario(String username, String password) {
        for (Usuario u : usuarios) {
            if (u.validarCredenciales(username, password)) {
                return u;
            }
        }
        return null;
    }
}