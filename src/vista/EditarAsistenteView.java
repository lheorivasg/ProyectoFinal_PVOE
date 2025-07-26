/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import controlador.ControladorAsistentes;
import controlador.ControladorPersistencia;
import controlador.ControladorPrincipal;
import modelo.Asistente;

public class EditarAsistenteView extends JFrame {
    private JTextField txtNombre, txtPrimerApellido, txtSegundoApellido;
    private JTextField txtEdad, txtDireccion, txtTelefono, txtTelefonoEmergencia;
    private JComboBox<String> cmbGenero;
    private JButton btnGuardar, btnCancelar;
    private Asistente asistente;
    private ControladorAsistentes ctrlAsistentes;

    public EditarAsistenteView(String idAsistente) {
        ctrlAsistentes = new ControladorAsistentes();
        this.asistente = ctrlAsistentes.buscarAsistentePorId(idAsistente);
        
        if (asistente == null) {
            JOptionPane.showMessageDialog(null, "Asistente no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
            dispose();
            return;
        }
        
        initComponents();
        cargarDatosAsistente();
    }

    private void initComponents() {
        setTitle("Gimnasio Deportivo - Azcapotzalco: Editar Asistente");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 450);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Componentes
        JLabel lblNombre = new JLabel("Nombre:");
        txtNombre = new JTextField(20);
        
        JLabel lblPrimerApellido = new JLabel("Primer Apellido:");
        txtPrimerApellido = new JTextField(20);
        
        JLabel lblSegundoApellido = new JLabel("Segundo Apellido:");
        txtSegundoApellido = new JTextField(20);
        
        JLabel lblEdad = new JLabel("Edad:");
        txtEdad = new JTextField(5);
        
        JLabel lblGenero = new JLabel("Género:");
        cmbGenero = new JComboBox<>(new String[]{"Masculino", "Femenino", "Otro"});
        
        JLabel lblDireccion = new JLabel("Dirección:");
        txtDireccion = new JTextField(25);
        
        JLabel lblTelefono = new JLabel("Teléfono:");
        txtTelefono = new JTextField(15);
        
        JLabel lblTelefonoEmergencia = new JLabel("Teléfono Emergencia:");
        txtTelefonoEmergencia = new JTextField(15);

        // Posicionamiento
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(lblNombre, gbc);
        gbc.gridx = 1;
        panel.add(txtNombre, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(lblPrimerApellido, gbc);
        gbc.gridx = 1;
        panel.add(txtPrimerApellido, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(lblSegundoApellido, gbc);
        gbc.gridx = 1;
        panel.add(txtSegundoApellido, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(lblEdad, gbc);
        gbc.gridx = 1;
        panel.add(txtEdad, gbc);
        
        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(lblGenero, gbc);
        gbc.gridx = 1;
        panel.add(cmbGenero, gbc);
        
        gbc.gridx = 0; gbc.gridy = 5;
        panel.add(lblDireccion, gbc);
        gbc.gridx = 1;
        panel.add(txtDireccion, gbc);
        
        gbc.gridx = 0; gbc.gridy = 6;
        panel.add(lblTelefono, gbc);
        gbc.gridx = 1;
        panel.add(txtTelefono, gbc);
        
        gbc.gridx = 0; gbc.gridy = 7;
        panel.add(lblTelefonoEmergencia, gbc);
        gbc.gridx = 1;
        panel.add(txtTelefonoEmergencia, gbc);

        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnGuardar = new JButton("Guardar Cambios");
        btnGuardar.addActionListener(this::guardarCambios);
        
        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);

        gbc.gridx = 0; gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.CENTER;
        panel.add(panelBotones, gbc);

        add(panel);
    }

    private void cargarDatosAsistente() {
        txtNombre.setText(asistente.getNombre());
        txtPrimerApellido.setText(asistente.getPrimerApellido());
        txtSegundoApellido.setText(asistente.getSegundoApellido());
        txtEdad.setText(String.valueOf(asistente.getEdad()));
        cmbGenero.setSelectedItem(asistente.getGenero());
        txtDireccion.setText(asistente.getDireccion());
        txtTelefono.setText(asistente.getTelefono());
        txtTelefonoEmergencia.setText(asistente.getTelefonoEmergencia());
    }

    private void guardarCambios(ActionEvent evt) {
        try {
            // Actualizar el objeto asistente con los nuevos datos
            asistente.setNombre(txtNombre.getText());
            asistente.setPrimerApellido(txtPrimerApellido.getText());
            asistente.setSegundoApellido(txtSegundoApellido.getText());
            asistente.setEdad(Integer.parseInt(txtEdad.getText()));
            asistente.setGenero(cmbGenero.getSelectedItem().toString());
            asistente.setDireccion(txtDireccion.getText());
            asistente.setTelefono(txtTelefono.getText());
            asistente.setTelefonoEmergencia(txtTelefonoEmergencia.getText());

            // Guardar los cambios
            if (ctrlAsistentes.actualizarAsistente(asistente)) {
                // Persistir los cambios
                ControladorPersistencia persistencia = new ControladorPersistencia();
                persistencia.guardarDatos(ControladorPrincipal.getInstance().getGimnasio());
                
                JOptionPane.showMessageDialog(this, 
                    "Asistente actualizado correctamente",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this,
                    "Error al actualizar asistente",
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                "La edad debe ser un número válido",
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}