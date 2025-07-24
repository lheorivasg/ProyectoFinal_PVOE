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
import java.awt.event.ActionListener;
import controlador.ControladorActividades;
import controlador.ControladorAsistentes;

public class InscripcionActividadesView extends JFrame {

    private JTable tblAsistentes;
    private JTable tblActividades;
    private JCheckBox chkEquipo;
    private JButton btnInscribir, btnCancelar;
    private ControladorAsistentes ctrlAsistentes;
    private ControladorActividades ctrlActividades;

    public InscripcionActividadesView() {
        ctrlAsistentes = new ControladorAsistentes();
        ctrlActividades = new ControladorActividades();
        initComponents();
        cargarAsistentes();
        cargarActividades();
    }

    private void initComponents() {
        setTitle("Gimnasio Deportivo - Azcapotzalco: Inscripción a Actividades");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel pnlAsistentes = new JPanel(new BorderLayout());
        pnlAsistentes.setBorder(BorderFactory.createTitledBorder("Asistentes"));

        tblAsistentes = new JTable();
        JScrollPane scrollAsistentes = new JScrollPane(tblAsistentes);
        pnlAsistentes.add(scrollAsistentes, BorderLayout.CENTER);

        JPanel pnlActividades = new JPanel(new BorderLayout());
        pnlActividades.setBorder(BorderFactory.createTitledBorder("Actividades Disponibles"));

        tblActividades = new JTable();
        JScrollPane scrollActividades = new JScrollPane(tblActividades);
        pnlActividades.add(scrollActividades, BorderLayout.CENTER);

        JPanel pnlOpciones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        chkEquipo = new JCheckBox("Adquirir equipo para la actividad");
        pnlOpciones.add(chkEquipo);

        btnInscribir = new JButton("Inscribir");
        btnInscribir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                inscribirAsistente(evt);
            }
        });
        pnlOpciones.add(btnInscribir);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        pnlOpciones.add(btnCancelar);

        panel.add(pnlAsistentes, BorderLayout.WEST);
        panel.add(pnlActividades, BorderLayout.CENTER);
        panel.add(pnlOpciones, BorderLayout.SOUTH);

        add(panel);
    }

    private void cargarAsistentes() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("Apellidos");

        for (modelo.Asistente asistente : ctrlAsistentes.obtenerTodosAsistentes()) {
            model.addRow(new Object[]{
                asistente.getId(),
                asistente.getNombre(),
                asistente.getPrimerApellido() + " " + asistente.getSegundoApellido()
            });
        }

        tblAsistentes.setModel(model);
    }

    private void cargarActividades() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Actividad");
        model.addColumn("Horario");
        model.addColumn("Instructor");
        model.addColumn("Costo");
        model.addColumn("Cupo");

        for (modelo.Actividad actividad : ctrlActividades.obtenerTodasActividades()) {
            model.addRow(new Object[]{
                actividad.getId(),
                actividad.getNombre(),
                actividad.getHorario(),
                actividad.getInstructor(),
                "$" + actividad.getCosto(),
                actividad.getCupoDisponible() + "/" + (actividad.getCupoDisponible() + 10) // Ejemplo
            });
        }

        tblActividades.setModel(model);
    }

    private void inscribirAsistente(ActionEvent evt) {
        int filaAsistente = tblAsistentes.getSelectedRow();
        int filaActividad = tblActividades.getSelectedRow();

        if (filaAsistente == -1 || filaActividad == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un asistente y una actividad",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String idAsistente = tblAsistentes.getValueAt(filaAsistente, 0).toString();
        String idActividad = tblActividades.getValueAt(filaActividad, 0).toString();
        boolean equipoAdquirido = chkEquipo.isSelected();

        if (ctrlActividades.inscribirAsistenteEnActividad(idAsistente, idActividad, equipoAdquirido)) {
            JOptionPane.showMessageDialog(this, "Inscripción realizada con éxito",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
            cargarActividades(); // Actualizar cupos
        } else {
            JOptionPane.showMessageDialog(this, "Error al realizar la inscripción",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
