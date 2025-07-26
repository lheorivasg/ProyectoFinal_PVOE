/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import modelo.Asistente;
import modelo.Gimnasio;
import modelo.GeneradorID;

public class ControladorAsistentes {
    private Gimnasio gimnasio;

    public ControladorAsistentes() {
        this.gimnasio = ControladorPrincipal.getInstance().getGimnasio();
    }

    public boolean registrarAsistente(String nombre, String primerApellido, String segundoApellido,
                                    int edad, String genero, String direccion, String telefono,
                                    String telefonoEmergencia) {
        String id = GeneradorID.generarIDAsistente();
        Asistente nuevoAsistente = new Asistente(id, nombre, primerApellido, segundoApellido,
                                                edad, genero, direccion, telefono, telefonoEmergencia);
        return gimnasio.agregarAsistente(nuevoAsistente);
    }

    public List<Asistente> obtenerTodosAsistentes() {
        return gimnasio.getAsistentes();
    }

    public Asistente buscarAsistentePorId(String id) {
        for (Asistente a : gimnasio.getAsistentes()) {
            if (a.getId().equals(id)) {
                return a;
            }
        }
        return null;
    }

    public List<Asistente> buscarAsistentesPorNombre(String nombre) {
        List<Asistente> resultados = new ArrayList<>();
        String busqueda = nombre.toLowerCase();
        for (Asistente a : gimnasio.getAsistentes()) {
            if (a.getNombre().toLowerCase().contains(busqueda)
                || a.getPrimerApellido().toLowerCase().contains(busqueda)
                || a.getSegundoApellido().toLowerCase().contains(busqueda)) {
                resultados.add(a);
            }
        }
        return resultados;
    }

    public boolean actualizarAsistente(Asistente asistenteActualizado) {
        if (asistenteActualizado == null) {
            return false;
        }
        
        List<Asistente> asistentes = gimnasio.getAsistentes();
        for (int i = 0; i < asistentes.size(); i++) {
            if (asistentes.get(i).getId().equals(asistenteActualizado.getId())) {
                asistentes.set(i, asistenteActualizado);
                return true;
            }
        }
        return false;
    }

    public boolean eliminarAsistente(String id) {
        if (id == null || id.isEmpty()) {
            return false;
        }
        
        Iterator<Asistente> iterator = gimnasio.getAsistentes().iterator();
        while (iterator.hasNext()) {
            Asistente a = iterator.next();
            if (a.getId().equals(id)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }
}