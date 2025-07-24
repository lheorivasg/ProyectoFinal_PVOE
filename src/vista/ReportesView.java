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
import controlador.ControladorActividades;
import modelo.Actividad;

public class ReportesView extends JFrame {

    private JComboBox<Actividad> cmbActividades;
    private JTextArea txtReporte;
    private JButton btnGenerar, btnSalir;
    private ControladorActividades ctrlActividades;

    public ReportesView() {
        ctrlActividades = new ControladorActividades();
        initComponents();
        cargarActividades();
    }

    private void initComponents() {
        setTitle("Gimnasio Deportivo – Azcapotzalco: Reportes");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel pnlSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        pnlSuperior.add(new JLabel("Seleccione actividad:"));

        cmbActividades = new JComboBox<>();
        pnlSuperior.add(cmbActividades);

        btnGenerar = new JButton("Generar Reporte");
        btnGenerar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                generarReporte();
            }
        });
        pnlSuperior.add(btnGenerar);

        panel.add(pnlSuperior, BorderLayout.NORTH);

        txtReporte = new JTextArea(10, 40);
        txtReporte.setEditable(false);
        JScrollPane scroll = new JScrollPane(txtReporte);
        panel.add(scroll, BorderLayout.CENTER);

        btnSalir = new JButton("Salir");
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        panel.add(btnSalir, BorderLayout.SOUTH);

        add(panel);
    }

    private void cargarActividades() {
        cmbActividades.removeAllItems();
        for (Actividad actividad : ctrlActividades.obtenerTodasActividades()) {
            cmbActividades.addItem(actividad);
        }
    }

    private void generarReporte() {
        Actividad actividad = (Actividad) cmbActividades.getSelectedItem();
        if (actividad != null) {
            String reporte = ctrlActividades.generarReporteActividad(actividad);
            txtReporte.setText(reporte);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Seleccione una actividad válida",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
