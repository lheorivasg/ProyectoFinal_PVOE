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
import controlador.ControladorAsistentes;
import controlador.ControladorPersistencia;
import controlador.ControladorPrincipal;

public class RegistroAsistentesView extends JFrame {
    private JTextField txtNombre, txtPrimerApellido, txtSegundoApellido;
    private JTextField txtEdad, txtDireccion, txtTelefono, txtTelefonoEmergencia;
    private JComboBox<String> cmbGenero;
    private JButton btnGuardar, btnLimpiar, btnCancelar;
    private ControladorAsistentes ctrlAsistentes;

    public RegistroAsistentesView() {
        ctrlAsistentes = new ControladorAsistentes();
        initComponents();
    }

    private void initComponents() {
        setTitle("Gimnasio Deportivo - Azcapotzalco: Registro de Asistentes");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 450);
        setLocationRelativeTo(null);

        // Panel principal con GridBagLayout
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
        btnGuardar = new JButton("Guardar");
        btnLimpiar = new JButton("Limpiar");
        btnCancelar = new JButton("Cancelar");
        
        panelBotones.add(btnGuardar);
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnCancelar);

        gbc.gridx = 0; gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.CENTER;
        panel.add(panelBotones, gbc);

        // Listeners
        btnGuardar.addActionListener(this::guardarAsistente);
        btnLimpiar.addActionListener(e -> limpiarFormulario());
        btnCancelar.addActionListener(e -> dispose());

        add(panel);
    }

    private void guardarAsistente(ActionEvent evt) {
        try {
            // Registrar asistente
            boolean exito = ctrlAsistentes.registrarAsistente(
                txtNombre.getText(),
                txtPrimerApellido.getText(),
                txtSegundoApellido.getText(),
                Integer.parseInt(txtEdad.getText()),
                cmbGenero.getSelectedItem().toString(),
                txtDireccion.getText(),
                txtTelefono.getText(),
                txtTelefonoEmergencia.getText()
            );

            if (exito) {
                // Persistir datos
                ControladorPersistencia persistencia = new ControladorPersistencia();
                if (persistencia.guardarDatos(ControladorPrincipal.getInstance().getGimnasio())) {
                    JOptionPane.showMessageDialog(this, 
                        "Asistente registrado y datos guardados", 
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    limpiarFormulario();
                } else {
                    JOptionPane.showMessageDialog(this,
                        "Asistente registrado pero error al guardar en disco",
                        "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this,
                    "Error al registrar asistente",
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                "La edad debe ser un número válido",
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarFormulario() {
        txtNombre.setText("");
        txtPrimerApellido.setText("");
        txtSegundoApellido.setText("");
        txtEdad.setText("");
        cmbGenero.setSelectedIndex(0);
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtTelefonoEmergencia.setText("");
    }
}