/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import controlador.ControladorAsistentes;
import controlador.ControladorPersistencia;
import controlador.ControladorPrincipal;
import modelo.Asistente;

public class EditarAsistenteView {
    private JPanel panel;
    private JTextField txtNombre, txtPrimerApellido, txtSegundoApellido;
    private JTextField txtEdad, txtDireccion, txtTelefono, txtTelefonoEmergencia;
    private JComboBox<String> cmbGenero;
    private JButton btnGuardar, btnCancelar;
    private Asistente asistente;

    public EditarAsistenteView(String idAsistente) {
        ControladorAsistentes ctrl = new ControladorAsistentes();
        this.asistente = ctrl.buscarAsistentePorId(idAsistente);
        initComponents();
        cargarDatosAsistente();
    }
    
    public JPanel getPanel() {
        return panel;
    }

    private void initComponents() {
        panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel pnlCampos = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Nombre
        gbc.gridx = 0; gbc.gridy = 0;
        pnlCampos.add(new JLabel("Nombre:"), gbc);
        
        gbc.gridx = 1;
        txtNombre = new JTextField(20);
        pnlCampos.add(txtNombre, gbc);
        
        // Primer Apellido
        gbc.gridx = 0; gbc.gridy = 1;
        pnlCampos.add(new JLabel("Primer Apellido:"), gbc);
        
        gbc.gridx = 1;
        txtPrimerApellido = new JTextField(20);
        pnlCampos.add(txtPrimerApellido, gbc);
        
        // Segundo Apellido
        gbc.gridx = 0; gbc.gridy = 2;
        pnlCampos.add(new JLabel("Segundo Apellido:"), gbc);
        
        gbc.gridx = 1;
        txtSegundoApellido = new JTextField(20);
        pnlCampos.add(txtSegundoApellido, gbc);
        
        // Edad
        gbc.gridx = 0; gbc.gridy = 3;
        pnlCampos.add(new JLabel("Edad:"), gbc);
        
        gbc.gridx = 1;
        txtEdad = new JTextField(5);
        txtEdad.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar())) {
                    e.consume();
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        });
        pnlCampos.add(txtEdad, gbc);
        
        // Género
        gbc.gridx = 0; gbc.gridy = 4;
        pnlCampos.add(new JLabel("Género:"), gbc);
        
        gbc.gridx = 1;
        cmbGenero = new JComboBox<>(new String[]{"Masculino", "Femenino", "Otro"});
        cmbGenero.setPreferredSize(new Dimension(200, 25));
        pnlCampos.add(cmbGenero, gbc);
        
        // Dirección
        gbc.gridx = 0; gbc.gridy = 5;
        pnlCampos.add(new JLabel("Dirección:"), gbc);
        
        gbc.gridx = 1;
        txtDireccion = new JTextField(20);
        pnlCampos.add(txtDireccion, gbc);
        
        // Teléfono
        gbc.gridx = 0; gbc.gridy = 6;
        pnlCampos.add(new JLabel("Teléfono:"), gbc);
        
        gbc.gridx = 1;
        txtTelefono = new JTextField(15);
        pnlCampos.add(txtTelefono, gbc);
        
        // Teléfono Emergencia
        gbc.gridx = 0; gbc.gridy = 7;
        pnlCampos.add(new JLabel("Teléfono Emergencia:"), gbc);
        
        gbc.gridx = 1;
        txtTelefonoEmergencia = new JTextField(15);
        pnlCampos.add(txtTelefonoEmergencia, gbc);

        panel.add(pnlCampos, BorderLayout.CENTER);

        // Botones
        JPanel pnlBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnGuardar = new JButton("Guardar Cambios");
        btnGuardar.setPreferredSize(new Dimension(150, 30));
        btnGuardar.addActionListener(this::guardarCambios);
        
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setPreferredSize(new Dimension(150, 30));
        btnCancelar.addActionListener(e -> MainContainer.getInstance().showDetalleAsistente(asistente.getId()));
        
        pnlBotones.add(btnGuardar);
        pnlBotones.add(btnCancelar);

        panel.add(pnlBotones, BorderLayout.SOUTH);
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
            // Validar campos obligatorios
            if (txtNombre.getText().isEmpty() || txtPrimerApellido.getText().isEmpty() || txtEdad.getText().isEmpty()) {
                JOptionPane.showMessageDialog(panel, "Nombre, apellido y edad son campos obligatorios", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Actualizar todos los campos
            asistente.setNombre(txtNombre.getText());
            asistente.setPrimerApellido(txtPrimerApellido.getText());
            asistente.setSegundoApellido(txtSegundoApellido.getText());
            asistente.setEdad(Integer.parseInt(txtEdad.getText()));
            asistente.setGenero(cmbGenero.getSelectedItem().toString());
            asistente.setDireccion(txtDireccion.getText());
            asistente.setTelefono(txtTelefono.getText());
            asistente.setTelefonoEmergencia(txtTelefonoEmergencia.getText());

            ControladorAsistentes ctrl = new ControladorAsistentes();
            if (ctrl.actualizarAsistente(asistente)) {
                // Guardar cambios en disco
                new ControladorPersistencia().guardarDatos(ControladorPrincipal.getInstance().getGimnasio());
                
                JOptionPane.showMessageDialog(panel, "Cambios guardados exitosamente", 
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
                MainContainer.getInstance().showDetalleAsistente(asistente.getId());
            } else {
                JOptionPane.showMessageDialog(panel, "Error al guardar cambios", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(panel, "La edad debe ser un número válido", 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}