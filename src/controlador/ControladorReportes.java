/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.ArrayList;
import java.util.List;
import modelo.Asistente;
import modelo.Actividad;
import modelo.Gimnasio;
import modelo.Inscripcion;

public class ControladorReportes {

    private Gimnasio gimnasio;

    public ControladorReportes() {
        this.gimnasio = ControladorPrincipal.getInstance().getGimnasio();
    }

    public List<Asistente> obtenerAsistentesPorActividad(String idActividad) {
        List<Asistente> asistentes = new ArrayList<>();
        for (Asistente a : gimnasio.getAsistentes()) {
            for (Inscripcion i : a.getInscripciones()) {
                if (i.getIdActividad().equals(idActividad)) {
                    asistentes.add(a);
                    break;
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
        int totalInscritos = 0;
        double ingresos = 0.0;
        int equiposAdquiridos = 0;

        for (Asistente asistente : gimnasio.getAsistentes()) {
            for (Inscripcion inscripcion : asistente.getInscripciones()) {
                if (inscripcion.getIdActividad().equals(actividad.getId())) {
                    totalInscritos++;
                    ingresos += inscripcion.getCostoTotal();
                    if (inscripcion.isEquipoAdquirido()) {
                        equiposAdquiridos++;
                    }
                }
            }
        }

        return String.format(
                "Actividad: %s\nInstructor: %s\nHorario: %s\n"
                + "Total inscritos: %d\nCupo disponible: %d\n"
                + "Equipos adquiridos: %d\nIngresos generados: $%.2f",
                actividad.getNombre(), actividad.getInstructor(),
                actividad.getHorario(), totalInscritos,
                actividad.getCupoDisponible(), equiposAdquiridos, ingresos
        );
    }
}
