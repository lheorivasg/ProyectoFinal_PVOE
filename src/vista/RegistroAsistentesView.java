 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import controlador.ControladorAsistentes;
import controlador.ControladorPersistencia;
import controlador.ControladorPrincipal;

public class RegistroAsistentesView {
    private JPanel panel;
    private JTextField txtNombre, txtPrimerApellido, txtSegundoApellido;
    private JTextField txtEdad, txtDireccion, txtTelefono, txtTelefonoEmergencia;
    private JComboBox<String> cmbGenero;
    private JButton btnGuardar, btnLimpiar, btnCancelar;
    private ControladorAsistentes ctrlAsistentes;

    public RegistroAsistentesView() {
        ctrlAsistentes = new ControladorAsistentes();
        initComponents();
    }
    
    public JPanel getPanel() {
        return panel;
    }

    private void initComponents() {
        panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // Título
        JLabel lblTitulo = new JLabel("REGISTRO DE NUEVO ASISTENTE", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
        panel.add(lblTitulo, BorderLayout.NORTH);

        // Panel principal con GridBagLayout
        JPanel pnlPrincipal = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Configuración de campos
        JLabel lblNombre = new JLabel("Nombre:");
        txtNombre = new JTextField();
        txtNombre.setPreferredSize(new Dimension(250, 25));

        JLabel lblPrimerApellido = new JLabel("Primer Apellido:");
        txtPrimerApellido = new JTextField();
        txtPrimerApellido.setPreferredSize(new Dimension(250, 25));

        JLabel lblSegundoApellido = new JLabel("Segundo Apellido:");
        txtSegundoApellido = new JTextField();
        txtSegundoApellido.setPreferredSize(new Dimension(250, 25));

        JLabel lblEdad = new JLabel("Edad:");
        txtEdad = new JTextField();
        txtEdad.setPreferredSize(new Dimension(50, 25));
        ((AbstractDocument)txtEdad.getDocument()).setDocumentFilter(new NumericFilter());

        JLabel lblGenero = new JLabel("Género:");
        cmbGenero = new JComboBox<>(new String[]{"Masculino", "Femenino", "Otro"});
        cmbGenero.setPreferredSize(new Dimension(250, 25));

        JLabel lblDireccion = new JLabel("Dirección:");
        txtDireccion = new JTextField();
        txtDireccion.setPreferredSize(new Dimension(250, 25));

        JLabel lblTelefono = new JLabel("Teléfono:");
        txtTelefono = new JTextField();
        txtTelefono.setPreferredSize(new Dimension(150, 25));

        JLabel lblTelefonoEmergencia = new JLabel("Teléfono Emergencia:");
        txtTelefonoEmergencia = new JTextField();
        txtTelefonoEmergencia.setPreferredSize(new Dimension(150, 25));

        // Posicionamiento de componentes
        gbc.gridx = 0; gbc.gridy = 0;
        pnlPrincipal.add(lblNombre, gbc);
        gbc.gridx = 1;
        pnlPrincipal.add(txtNombre, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        pnlPrincipal.add(lblPrimerApellido, gbc);
        gbc.gridx = 1;
        pnlPrincipal.add(txtPrimerApellido, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        pnlPrincipal.add(lblSegundoApellido, gbc);
        gbc.gridx = 1;
        pnlPrincipal.add(txtSegundoApellido, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        pnlPrincipal.add(lblEdad, gbc);
        gbc.gridx = 1;
        pnlPrincipal.add(txtEdad, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        pnlPrincipal.add(lblGenero, gbc);
        gbc.gridx = 1;
        pnlPrincipal.add(cmbGenero, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        pnlPrincipal.add(lblDireccion, gbc);
        gbc.gridx = 1;
        pnlPrincipal.add(txtDireccion, gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        pnlPrincipal.add(lblTelefono, gbc);
        gbc.gridx = 1;
        pnlPrincipal.add(txtTelefono, gbc);

        gbc.gridx = 0; gbc.gridy = 7;
        pnlPrincipal.add(lblTelefonoEmergencia, gbc);
        gbc.gridx = 1;
        pnlPrincipal.add(txtTelefonoEmergencia, gbc);

        panel.add(pnlPrincipal, BorderLayout.CENTER);

        // Panel de botones
        JPanel pnlBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnGuardar = new JButton("Guardar");
        btnGuardar.setPreferredSize(new Dimension(100, 30));
        btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnGuardar.addActionListener(this::guardarAsistente);
        
        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setPreferredSize(new Dimension(100, 30));
        btnLimpiar.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnLimpiar.addActionListener(e -> limpiarFormulario());
        
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setPreferredSize(new Dimension(100, 30));
        btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnCancelar.addActionListener(e -> MainContainer.getInstance().showMainMenu());
        
        pnlBotones.add(btnGuardar);
        pnlBotones.add(btnLimpiar);
        pnlBotones.add(btnCancelar);

        panel.add(pnlBotones, BorderLayout.SOUTH);
    }

    private void guardarAsistente(ActionEvent evt) {
        try {
            // Validar campos obligatorios
            if (txtNombre.getText().trim().isEmpty() || txtPrimerApellido.getText().trim().isEmpty() || 
                txtEdad.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(panel, 
                    "Nombre, apellido y edad son campos obligatorios", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Registrar asistente
            boolean exito = ctrlAsistentes.registrarAsistente(
                txtNombre.getText().trim(),
                txtPrimerApellido.getText().trim(),
                txtSegundoApellido.getText().trim(),
                Integer.parseInt(txtEdad.getText().trim()),
                cmbGenero.getSelectedItem().toString(),
                txtDireccion.getText().trim(),
                txtTelefono.getText().trim(),
                txtTelefonoEmergencia.getText().trim()
            );

            if (exito) {
                // Persistir datos
                ControladorPersistencia persistencia = new ControladorPersistencia();
                if (persistencia.guardarDatos(ControladorPrincipal.getInstance().getGimnasio())) {
                    JOptionPane.showMessageDialog(panel,
                        "Asistente registrado exitosamente",
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    limpiarFormulario();
                } else {
                    JOptionPane.showMessageDialog(panel,
                        "Asistente registrado pero error al guardar datos",
                        "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(panel,
                    "Error al registrar asistente",
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(panel,
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

    // Filtro para solo permitir números en el campo de edad
    private class NumericFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) 
            throws BadLocationException {
            if (string.matches("\\d*")) {
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) 
            throws BadLocationException {
            if (text.matches("\\d*")) {
                super.replace(fb, offset, length, text, attrs);
            }
        }
    }
}