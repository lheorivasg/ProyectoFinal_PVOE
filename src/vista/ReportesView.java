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
import controlador.ControladorActividades;
import modelo.Actividad;

public class ReportesView {
    private JPanel panel;
    private JComboBox<String> cmbReportes;
    private JTextArea txtResultado;
    private JButton btnGenerar, btnRegresar;
    private ControladorReportes ctrlReportes;
    private ControladorActividades ctrlActividades;

    public ReportesView() {
        ctrlReportes = new ControladorReportes();
        ctrlActividades = new ControladorActividades();
        initComponents();
        cargarOpcionesReportes();
    }

    public JPanel getPanel() {
        return panel;
    }

    private void initComponents() {
        panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Panel superior con combobox y botón
        JPanel pnlSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        pnlSuperior.add(new JLabel("Tipo de reporte:"));
        
        cmbReportes = new JComboBox<>();
        cmbReportes.setPreferredSize(new Dimension(200, 25));
        pnlSuperior.add(cmbReportes);
        
        btnGenerar = new JButton("Generar");
        btnGenerar.addActionListener(this::generarReporte);
        pnlSuperior.add(btnGenerar);
        
        panel.add(pnlSuperior, BorderLayout.NORTH);

        // Área de texto para resultados
        txtResultado = new JTextArea(15, 50);
        txtResultado.setEditable(false);
        txtResultado.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scroll = new JScrollPane(txtResultado);
        panel.add(scroll, BorderLayout.CENTER);

        // Botón regresar
        btnRegresar = new JButton("Regresar");
        btnRegresar.addActionListener(e -> 
                MainContainer.getInstance().showMainMenu());
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
                resultado = generarReporteAsistentesPorActividad();
                break;
                
            case "Ingresos totales":
                resultado = "=== INGRESOS TOTALES ===\n\n";
                resultado += "Total de ingresos: $" + 
                        String.format("%.2f", ctrlReportes.calcularIngresosTotales());
                break;
                
            case "Actividades más populares":
                resultado = ctrlReportes.generarReporteActividadesPopulares();
                break;
        }

        txtResultado.setText(resultado);
    }

    private String generarReporteAsistentesPorActividad() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== ASISTENTES POR ACTIVIDAD ===\n\n");
        
        for (Actividad actividad : ctrlActividades.obtenerTodasActividades()) {
            sb.append(ctrlReportes.generarReporteActividad(actividad))
              .append("\n\n");
        }
        
        return sb.toString();
    }
}