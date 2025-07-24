/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controlador.ControladorPersistencia;
import controlador.ControladorPrincipal;

public class MainView extends JFrame {
    private JButton btnRegistroAsistentes;
    private JButton btnInscripciones;
    private JButton btnConsultas;
    private JButton btnReportes;
    private JButton btnSalir;

    public MainView() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Gimnasio Deportivo - Azcapotzalco: Menú Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 350);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Botones principales
        btnRegistroAsistentes = new JButton("Registro de Asistentes");
        btnInscripciones = new JButton("Inscripción a Actividades");
        btnConsultas = new JButton("Consulta de Información");
        btnReportes = new JButton("Reportes");
        btnSalir = new JButton("Salir del Sistema");

        // Listeners mejorados
        btnRegistroAsistentes.addActionListener(e -> new RegistroAsistentesView().setVisible(true));
        btnInscripciones.addActionListener(e -> new InscripcionActividadesView().setVisible(true));
        btnConsultas.addActionListener(e -> new ConsultaAsistentesView().setVisible(true));
        btnReportes.addActionListener(e -> new ReportesView().setVisible(true));
        
        btnSalir.addActionListener(e -> confirmarSalida());

        // Agregar componentes
        panel.add(btnRegistroAsistentes);
        panel.add(btnInscripciones);
        panel.add(btnConsultas);
        panel.add(btnReportes);
        panel.add(btnSalir);

        add(panel);
    }

    private void confirmarSalida() {
        int opcion = JOptionPane.showConfirmDialog(
            this,
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
            // CANCEL_OPTION no hace nada
        }
    }

    private void guardarYSalir() {
        ControladorPersistencia persistencia = new ControladorPersistencia();
        if (persistencia.guardarDatos(ControladorPrincipal.getInstance().getGimnasio())) {
            JOptionPane.showMessageDialog(this,
                "Datos guardados correctamente",
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        } else {
            JOptionPane.showMessageDialog(this,
                "Error al guardar datos. ¿Salir sin guardar?",
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}