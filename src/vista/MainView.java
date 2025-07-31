/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import controlador.ControladorPersistencia;
import controlador.ControladorPrincipal;

public class MainView {
    private JPanel panel;
    
    public MainView() {
        initComponents();
    }
    
    public JPanel getPanel() {
        return panel;
    }
    
    private void initComponents() {
        panel = new JPanel(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnRegistroAsistentes = new JButton("Registro de Asistentes");
        JButton btnInscripciones = new JButton("Inscripción a Actividades");
        JButton btnConsultas = new JButton("Consulta de Información");
        JButton btnReportes = new JButton("Reportes");
        JButton btnSalir = new JButton("Salir del Sistema");

        btnRegistroAsistentes.addActionListener(e -> 
            MainContainer.getInstance().showRegistroAsistentes());
        
        btnInscripciones.addActionListener(e -> 
            MainContainer.getInstance().showInscripcionActividades());
        
        btnConsultas.addActionListener(e -> 
            MainContainer.getInstance().showConsultaAsistentes());
        
        btnReportes.addActionListener(e -> 
            MainContainer.getInstance().showReportes());
        
        btnSalir.addActionListener(e -> confirmarSalida());

        panel.add(btnRegistroAsistentes);
        panel.add(btnInscripciones);
        panel.add(btnConsultas);
        panel.add(btnReportes);
        panel.add(btnSalir);
    }
    
    private void confirmarSalida() {
        int opcion = JOptionPane.showConfirmDialog(
            panel,
            "¿Desea guardar los cambios antes de salir?",
            "Confirmar salida",
            JOptionPane.YES_NO_CANCEL_OPTION
        );

        switch (opcion) {
            case JOptionPane.YES_OPTION:
                guardarYSalir();
                break;
            case JOptionPane.NO_OPTION:
                System.exit(0);
                break;
        }
    }
    
    private void guardarYSalir() {
        ControladorPersistencia persistencia = new ControladorPersistencia();
        if (persistencia.guardarDatos(ControladorPrincipal.getInstance().getGimnasio())) {
            JOptionPane.showMessageDialog(panel,
                "Datos guardados correctamente",
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        } else {
            JOptionPane.showMessageDialog(panel,
                "Error al guardar datos. ¿Salir sin guardar?",
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}