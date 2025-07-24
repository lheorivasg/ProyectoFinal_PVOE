/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import controlador.ControladorActividades;
import modelo.Asistente;

public class DetalleAsistenteView extends JFrame {

    private JLabel lblNombre, lblApellidos, lblEdad, lblGenero, lblDireccion, lblTelefono, lblTelefonoEmergencia;
    private JTable tblActividades;
    private JButton btnCerrar;
    private Asistente asistente;

    public DetalleAsistenteView(String idAsistente) {
        controlador.ControladorAsistentes ctrlAsistentes = new controlador.ControladorAsistentes();
        this.asistente = ctrlAsistentes.buscarAsistentePorId(idAsistente);

        if (asistente == null) {
            JOptionPane.showMessageDialog(null, "Asistente no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
            dispose();
            return;
        }

        initComponents();
        cargarDatosAsistente();
        cargarActividades();
    }

    private void initComponents() {
        setTitle("Gimnasio Deportivo - Azcapotzalco: Detalle de Asistente");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel pnlInfo = new JPanel(new GridLayout(7, 2, 5, 5));
        pnlInfo.setBorder(BorderFactory.createTitledBorder("Información Personal"));

        pnlInfo.add(new JLabel("Nombre:"));
        lblNombre = new JLabel();
        pnlInfo.add(lblNombre);

        pnlInfo.add(new JLabel("Apellidos:"));
        lblApellidos = new JLabel();
        pnlInfo.add(lblApellidos);

        pnlInfo.add(new JLabel("Edad:"));
        lblEdad = new JLabel();
        pnlInfo.add(lblEdad);

        pnlInfo.add(new JLabel("Género:"));
        lblGenero = new JLabel();
        pnlInfo.add(lblGenero);

        pnlInfo.add(new JLabel("Dirección:"));
        lblDireccion = new JLabel();
        pnlInfo.add(lblDireccion);

        pnlInfo.add(new JLabel("Teléfono:"));
        lblTelefono = new JLabel();
        pnlInfo.add(lblTelefono);

        pnlInfo.add(new JLabel("Teléfono Emergencia:"));
        lblTelefonoEmergencia = new JLabel();
        pnlInfo.add(lblTelefonoEmergencia);

        panel.add(pnlInfo, BorderLayout.NORTH);

        JPanel pnlActividades = new JPanel(new BorderLayout());
        pnlActividades.setBorder(BorderFactory.createTitledBorder("Actividades Inscritas"));

        tblActividades = new JTable();
        JScrollPane scrollActividades = new JScrollPane(tblActividades);
        pnlActividades.add(scrollActividades, BorderLayout.CENTER);

        ControladorActividades ctrlActividades = new ControladorActividades();
        double total = ctrlActividades.calcularMensualidadAsistente(asistente.getId());

        JLabel lblTotal = new JLabel("Total mensualidad: $" + total, SwingConstants.RIGHT);
        pnlActividades.add(lblTotal, BorderLayout.SOUTH);

        panel.add(pnlActividades, BorderLayout.CENTER);

        btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dispose();
            }
        });
        panel.add(btnCerrar, BorderLayout.SOUTH);

        add(panel);
    }

    private void cargarDatosAsistente() {
        lblNombre.setText(asistente.getNombre());
        lblApellidos.setText(asistente.getPrimerApellido() + " " + asistente.getSegundoApellido());
        lblEdad.setText(String.valueOf(asistente.getEdad()));
        lblGenero.setText(asistente.getGenero());
        lblDireccion.setText(asistente.getDireccion());
        lblTelefono.setText(asistente.getTelefono());
        lblTelefonoEmergencia.setText(asistente.getTelefonoEmergencia());
    }

    private void cargarActividades() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Actividad");
        model.addColumn("Horario");
        model.addColumn("Instructor");
        model.addColumn("Equipo");
        model.addColumn("Costo Actividad");
        model.addColumn("Costo Equipo");
        model.addColumn("Total");

        for (modelo.Inscripcion inscripcion : asistente.getInscripciones()) {
            model.addRow(new Object[]{
                inscripcion.getNombreActividad(),
                inscripcion.getHorario(),
                inscripcion.getInstructor(),
                inscripcion.isEquipoAdquirido() ? "Sí" : "No",
                "$" + inscripcion.getCostoActividad(),
                "$" + (inscripcion.isEquipoAdquirido() ? inscripcion.getCostoEquipo() : 0),
                "$" + inscripcion.getCostoTotal()
            });
        }

        tblActividades.setModel(model);
    }
}
