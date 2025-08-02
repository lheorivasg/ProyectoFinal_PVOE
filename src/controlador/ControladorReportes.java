/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.List;
import modelo.Asistente;
import modelo.Actividad;
import modelo.Inscripcion;
import modelo.Gimnasio;

public class ControladorReportes {
    private Gimnasio gimnasio;

    public ControladorReportes() {
        this.gimnasio = ControladorPrincipal.getInstance().getGimnasio();
    }

    public List<Asistente> obtenerAsistentesPorActividad(String idActividad) {
        List<Asistente> asistentes = new java.util.ArrayList<>();
        
        for (Asistente asistente : gimnasio.getAsistentes()) {
            for (Inscripcion inscripcion : asistente.getInscripciones()) {
                if (inscripcion.getIdActividad().equals(idActividad)) {
                    asistentes.add(asistente);
                    break; // Para no agregar al mismo asistente múltiples veces
                }
            }
        }
        return asistentes;
    }

    public double calcularIngresosTotales() {
        double total = 0.0;
        for (Asistente a : gimnasio.getAsistentes()) {
            total += a.calcularMensualidadTotal();
        }
        return total;
    }

    public String generarReporteActividad(Actividad actividad) {
        List<Asistente> asistentes = obtenerAsistentesPorActividad(actividad.getId());
        double ingresos = 0.0;
        int equiposAdquiridos = 0;

        for (Asistente asistente : asistentes) {
            for (Inscripcion inscripcion : asistente.getInscripciones()) {
                if (inscripcion.getIdActividad().equals(actividad.getId())) {
                    ingresos += inscripcion.getCostoTotal();
                    if (inscripcion.isEquipoAdquirido()) {
                        equiposAdquiridos++;
                    }
                }
            }
        }

        return String.format(
            "REPORTE DE ACTIVIDAD: %s\n\n"
            + "Instructor: %s\n"
            + "Horario: %s\n"
            + "Cupo disponible: %d\n"
            + "Total inscritos: %d\n"
            + "Equipos adquiridos: %d\n"
            + "Ingresos generados: $%.2f\n\n"
            + "=== LISTA DE ASISTENTES ===\n%s",
            actividad.getNombre(),
            actividad.getInstructor(),
            actividad.getHorario(),
            actividad.getCupoDisponible(),
            asistentes.size(),
            equiposAdquiridos,
            ingresos,
            generarListaAsistentes(asistentes)
        );
    }

    private String generarListaAsistentes(List<Asistente> asistentes) {
        if (asistentes.isEmpty()) {
            return "No hay asistentes inscritos en esta actividad";
        }
        
        StringBuilder sb = new StringBuilder();
        for (Asistente a : asistentes) {
            sb.append(String.format(
                "- %s %s (Tel: %s)\n",
                a.getNombre(),
                a.getPrimerApellido(),
                a.getTelefono()
            ));
        }
        return sb.toString();
    }

    public String generarReporteActividadesPopulares() {
        List<Actividad> actividades = gimnasio.getActividades();
        if (actividades.isEmpty()) {
            return "No hay actividades registradas";
        }

        // Ordenar actividades por cantidad de asistentes (de mayor a menor)
        actividades.sort((a1, a2) -> {
            int count1 = obtenerAsistentesPorActividad(a1.getId()).size();
            int count2 = obtenerAsistentesPorActividad(a2.getId()).size();
            return Integer.compare(count2, count1);
        });

        StringBuilder sb = new StringBuilder();
        sb.append("=== ACTIVIDADES MÁS POPULARES ===\n\n");
        
        for (Actividad act : actividades) {
            int inscritos = obtenerAsistentesPorActividad(act.getId()).size();
            sb.append(String.format(
                "%s: %d asistentes\nHorario: %s\nInstructor: %s\n\n",
                act.getNombre(),
                inscritos,
                act.getHorario(),
                act.getInstructor()
            ));
        }
        
        return sb.toString();
    }
}