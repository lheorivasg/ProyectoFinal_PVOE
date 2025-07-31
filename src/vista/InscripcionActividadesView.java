/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import controlador.ControladorActividades;
import controlador.ControladorAsistentes;
import modelo.Asistente;
import modelo.Actividad;

public class InscripcionActividadesView {
    private JPanel panel;
    private JComboBox<Asistente> cmbAsistentes;
    private JComboBox<Actividad> cmbActividades;
    private JCheckBox chkEquipo;
    private JButton btnInscribir, btnRegresar;
    private ControladorActividades ctrlActividades;
    private ControladorAsistentes ctrlAsistentes;

    public InscripcionActividadesView() {
        ctrlActividades = new ControladorActividades();
        ctrlAsistentes = new ControladorAsistentes();
        initComponents();
        cargarAsistentes();
        cargarActividades();
    }
    
    public JPanel getPanel() {
        return panel;
    }

    private void initComponents() {
        panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel pnlFormulario = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Asistente
        gbc.gridx = 0; gbc.gridy = 0;
        pnlFormulario.add(new JLabel("Asistente:"), gbc);
        
        gbc.gridx = 1;
        cmbAsistentes = new JComboBox<>();
        cmbAsistentes.setPreferredSize(new Dimension(300, 25));
        pnlFormulario.add(cmbAsistentes, gbc);

        // Actividad
        gbc.gridx = 0; gbc.gridy = 1;
        pnlFormulario.add(new JLabel("Actividad:"), gbc);
        
        gbc.gridx = 1;
        cmbActividades = new JComboBox<>();
        cmbActividades.setPreferredSize(new Dimension(300, 25));
        pnlFormulario.add(cmbActividades, gbc);

        // Equipo
        gbc.gridx = 0; gbc.gridy = 2;
        pnlFormulario.add(new JLabel("¿Adquirir equipo?"), gbc);
        
        gbc.gridx = 1;
        chkEquipo = new JCheckBox();
        pnlFormulario.add(chkEquipo, gbc);

        panel.add(pnlFormulario, BorderLayout.CENTER);

        // Botones
        JPanel pnlBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnInscribir = new JButton("Inscribir");
        btnInscribir.setPreferredSize(new Dimension(120, 30));
        btnInscribir.addActionListener(this::inscribirAsistente);
        
        btnRegresar = new JButton("Regresar");
        btnRegresar.setPreferredSize(new Dimension(120, 30));
        btnRegresar.addActionListener(e -> MainContainer.getInstance().showMainMenu());
        
        pnlBotones.add(btnInscribir);
        pnlBotones.add(btnRegresar);

        panel.add(pnlBotones, BorderLayout.SOUTH);
    }

    private void cargarAsistentes() {
        cmbAsistentes.removeAllItems();
        DefaultComboBoxModel<Asistente> model = new DefaultComboBoxModel<>();
        for (Asistente asistente : ctrlAsistentes.obtenerTodosAsistentes()) {
            model.addElement(asistente);
        }
        cmbAsistentes.setModel(model);
        cmbAsistentes.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Asistente) {
                    Asistente a = (Asistente) value;
                    setText(a.getNombre() + " " + a.getPrimerApellido() + " (" + a.getId() + ")");
                }
                return this;
            }
        });
    }

    private void cargarActividades() {
        cmbActividades.removeAllItems();
        DefaultComboBoxModel<Actividad> model = new DefaultComboBoxModel<>();
        for (Actividad actividad : ctrlActividades.obtenerTodasActividades()) {
            model.addElement(actividad);
        }
        cmbActividades.setModel(model);
        cmbActividades.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Actividad) {
                    Actividad a = (Actividad) value;
                    setText(a.getNombre() + " - " + a.getHorario() + " (" + a.getCupoDisponible() + " cupos)");
                }
                return this;
            }
        });
    }

    private void inscribirAsistente(ActionEvent evt) {
        Asistente asistente = (Asistente) cmbAsistentes.getSelectedItem();
        Actividad actividad = (Actividad) cmbActividades.getSelectedItem();
        
        if (asistente == null || actividad == null) {
            JOptionPane.showMessageDialog(panel, "Seleccione un asistente y una actividad", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        boolean exito = ctrlActividades.inscribirAsistenteEnActividad(
            asistente.getId(), 
            actividad.getId(), 
            chkEquipo.isSelected()
        );
        
        if (exito) {
            JOptionPane.showMessageDialog(panel, "Inscripción realizada con éxito", 
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
            cargarActividades(); // Actualizar cupos disponibles
        } else {
            JOptionPane.showMessageDialog(panel, "Error al realizar la inscripción", 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}