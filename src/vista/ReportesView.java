/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import controlador.ControladorReportes;

public class ReportesView {
    private JPanel panel;
    private JComboBox<String> cmbReportes;
    private JTextArea txtResultado;
    private JButton btnGenerar, btnRegresar;
    private ControladorReportes ctrlReportes;

    public ReportesView() {
        ctrlReportes = new ControladorReportes();
        initComponents();
        cargarOpcionesReportes();
    }
    
    public JPanel getPanel() {
        return panel;
    }

    private void initComponents() {
        panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel pnlSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        pnlSuperior.add(new JLabel("Tipo de reporte:"));
        
        cmbReportes = new JComboBox<>();
        pnlSuperior.add(cmbReportes);
        
        btnGenerar = new JButton("Generar");
        btnGenerar.addActionListener(this::generarReporte);
        pnlSuperior.add(btnGenerar);

        panel.add(pnlSuperior, BorderLayout.NORTH);

        txtResultado = new JTextArea(15, 40);
        txtResultado.setEditable(false);
        JScrollPane scroll = new JScrollPane(txtResultado);
        panel.add(scroll, BorderLayout.CENTER);

        btnRegresar = new JButton("Regresar");
        btnRegresar.addActionListener(e -> MainContainer.getInstance().showMainMenu());
        panel.add(btnRegresar, BorderLayout.SOUTH);
    }

    private void cargarOpcionesReportes() {
        cmbReportes.removeAllItems();
        cmbReportes.addItem("Asistentes por actividad");
        cmbReportes.addItem("Ingresos totales");
        cmbReportes.addItem("Actividades más populares");
    }

    private void generarReporte(ActionEvent evt) {
        String reporteSeleccionado = (String) cmbReportes.getSelectedItem();
        String resultado = "";
        
        switch(reporteSeleccionado) {
            case "Asistentes por actividad":
                resultado = "Reporte de asistentes por actividad...";
                break;
            case "Ingresos totales":
                resultado = "Total de ingresos: $" + ctrlReportes.calcularIngresosTotales();
                break;
            case "Actividades más populares":
                resultado = "Actividades más populares...";
                break;
        }
        
        txtResultado.setText(resultado);
    }
}