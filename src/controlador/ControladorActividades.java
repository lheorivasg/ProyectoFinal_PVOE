/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.List;
import modelo.Actividad;
import modelo.Asistente;
import modelo.Inscripcion;
import modelo.Gimnasio;

public class ControladorActividades {

    private Gimnasio gimnasio;

    public ControladorActividades() {
        this.gimnasio = ControladorPrincipal.getInstance().getGimnasio();
    }

    public List<Actividad> obtenerTodasActividades() {
        return gimnasio.getActividades();
    }

    public Actividad obtenerActividadPorId(String id) {
        return gimnasio.buscarActividad(id);
    }

    public boolean inscribirAsistenteEnActividad(String idAsistente, String idActividad, boolean equipoAdquirido) {
        Asistente asistente = null;
        for (Asistente a : gimnasio.getAsistentes()) {
            if (a.getId().equals(idAsistente)) {
                asistente = a;
                break;
            }
        }

        Actividad actividad = gimnasio.buscarActividad(idActividad);

        if (asistente != null && actividad != null && actividad.reducirCupo()) {
            Inscripcion nuevaInscripcion = new Inscripcion(
                    actividad.getId(),
                    actividad.getNombre(),
                    actividad.getHorario(),
                    actividad.getInstructor(),
                    equipoAdquirido,
                    actividad.getCosto(),
                    actividad.getCostoEquipo()
            );

            asistente.agregarInscripcion(nuevaInscripcion);
            return true;
        }
        return false;
    }

    public boolean cancelarInscripcion(String idAsistente, String idActividad) {
        Asistente asistente = null;
        for (Asistente a : gimnasio.getAsistentes()) {
            if (a.getId().equals(idAsistente)) {
                asistente = a;
                break;
            }
        }

        Actividad actividad = gimnasio.buscarActividad(idActividad);

        if (asistente != null && actividad != null) {
            boolean eliminada = asistente.eliminarInscripcion(idActividad);
            if (eliminada) {
                actividad.aumentarCupo();
            }
            return eliminada;
        }
        return false;
    }

    public double calcularMensualidadAsistente(String idAsistente) {
        for (Asistente a : gimnasio.getAsistentes()) {
            if (a.getId().equals(idAsistente)) {
                return a.calcularMensualidadTotal();
            }
        }
        return 0.0;
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
                "REPORTE DE ACTIVIDAD\n\n"
                + "Nombre: %s\n"
                + "Horario: %s\n"
                + "Instructor: %s\n"
                + "Cupo disponible: %d\n"
                + "Total inscritos: %d\n"
                + "Equipos adquiridos: %d\n"
                + "Ingresos generados: $%.2f",
                actividad.getNombre(),
                actividad.getHorario(),
                actividad.getInstructor(),
                actividad.getCupoDisponible(),
                totalInscritos,
                equiposAdquiridos,
                ingresos
        );
    }
}
