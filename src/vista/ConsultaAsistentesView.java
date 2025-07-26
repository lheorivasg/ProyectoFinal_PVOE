/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import controlador.ControladorAsistentes;

public class ConsultaAsistentesView extends JFrame {
    private JTable tblAsistentes;
    private JTextField txtBusqueda;
    private JButton btnBuscar, btnDetalles, btnEditar, btnEliminar, btnSalir;
    private ControladorAsistentes ctrlAsistentes;

    public ConsultaAsistentesView() {
        ctrlAsistentes = new ControladorAsistentes();
        initComponents();
        cargarTodosAsistentes();
    }

    private void initComponents() {
        setTitle("Gimnasio Deportivo - Azcapotzalco: Consulta de Asistentes");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel pnlBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        pnlBusqueda.add(new JLabel("Buscar por nombre:"));
        txtBusqueda = new JTextField(20);
        pnlBusqueda.add(txtBusqueda);
        
        btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(e -> buscarAsistentes());
        pnlBusqueda.add(btnBuscar);

        panel.add(pnlBusqueda, BorderLayout.NORTH);

        tblAsistentes = new JTable();
        JScrollPane scrollAsistentes = new JScrollPane(tblAsistentes);
        panel.add(scrollAsistentes, BorderLayout.CENTER);

        JPanel pnlBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        
        btnDetalles = new JButton("Ver Detalles");
        btnDetalles.addActionListener(this::mostrarDetalles);
        pnlBotones.add(btnDetalles);
        
        btnEditar = new JButton("Editar");
        btnEditar.addActionListener(this::editarAsistente);
        pnlBotones.add(btnEditar);
        
        btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(this::eliminarAsistente);
        pnlBotones.add(btnEliminar);
        
        btnSalir = new JButton("Salir");
        btnSalir.addActionListener(e -> dispose());
        pnlBotones.add(btnSalir);

        panel.add(pnlBotones, BorderLayout.SOUTH);
        add(panel);
    }

    private void cargarTodosAsistentes() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("Apellidos");
        model.addColumn("Edad");
        model.addColumn("Teléfono");

        for (modelo.Asistente asistente : ctrlAsistentes.obtenerTodosAsistentes()) {
            model.addRow(new Object[]{
                asistente.getId(),
                asistente.getNombre(),
                asistente.getPrimerApellido() + " " + asistente.getSegundoApellido(),
                asistente.getEdad(),
                asistente.getTelefono()
            });
        }
        tblAsistentes.setModel(model);
    }

    private void buscarAsistentes() {
        String busqueda = txtBusqueda.getText().trim();
        DefaultTableModel model = (DefaultTableModel) tblAsistentes.getModel();
        model.setRowCount(0);

        for (modelo.Asistente asistente : ctrlAsistentes.buscarAsistentesPorNombre(busqueda)) {
            model.addRow(new Object[]{
                asistente.getId(),
                asistente.getNombre(),
                asistente.getPrimerApellido() + " " + asistente.getSegundoApellido(),
                asistente.getEdad(),
                asistente.getTelefono()
            });
        }
    }

    private void mostrarDetalles(ActionEvent evt) {
        int filaSeleccionada = tblAsistentes.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un asistente primero",
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String idAsistente = tblAsistentes.getValueAt(filaSeleccionada, 0).toString();
        new DetalleAsistenteView(idAsistente).setVisible(true);
    }

    private void editarAsistente(ActionEvent evt) {
        int filaSeleccionada = tblAsistentes.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un asistente primero",
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String idAsistente = tblAsistentes.getValueAt(filaSeleccionada, 0).toString();
        new EditarAsistenteView(idAsistente).setVisible(true);
        cargarTodosAsistentes();
    }

    private void eliminarAsistente(ActionEvent evt) {
        int filaSeleccionada = tblAsistentes.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un asistente primero",
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String idAsistente = tblAsistentes.getValueAt(filaSeleccionada, 0).toString();
        int confirmacion = JOptionPane.showConfirmDialog(this,
            "¿Está seguro que desea eliminar este asistente?",
            "Confirmar eliminación",
            JOptionPane.YES_NO_OPTION);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            if (ctrlAsistentes.eliminarAsistente(idAsistente)) {
                JOptionPane.showMessageDialog(this, "Asistente eliminado correctamente",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarTodosAsistentes();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar asistente",
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}