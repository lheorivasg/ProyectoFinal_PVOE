/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import controlador.ControladorUsuarios;

public class LoginView {
    private JPanel panel;
    private JTextField txtUsuario;
    private JPasswordField txtPassword;

    public LoginView() {
        initComponents();
    }

    public JPanel getPanel() {
        return panel;
    }

    private void initComponents() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        // Título
        JLabel lblTitulo = new JLabel("GIMNASIO DEPORTIVO - AZCAPOTZALCO", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblTitulo);
        panel.add(Box.createVerticalStrut(20));

        JLabel lblSubtitulo = new JLabel("INICIO DE SESIÓN", SwingConstants.CENTER);
        lblSubtitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblSubtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblSubtitulo);
        panel.add(Box.createVerticalStrut(30));

        // Campo Usuario
        JPanel pnlUsuario = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setPreferredSize(new Dimension(100, 20));
        pnlUsuario.add(lblUsuario);
        txtUsuario = new JTextField();
        txtUsuario.setPreferredSize(new Dimension(200, 30));
        pnlUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlUsuario.add(txtUsuario);
        panel.add(pnlUsuario);
        panel.add(Box.createVerticalStrut(15));

        // Campo Contraseña
        JPanel pnlPassword = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setPreferredSize(new Dimension(100, 20));
        pnlPassword.add(lblPassword);
        txtPassword = new JPasswordField();
        txtPassword.setPreferredSize(new Dimension(200, 30));
        pnlPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlPassword.add(txtPassword);
        panel.add(pnlPassword);
        panel.add(Box.createVerticalStrut(30));

        // Botón Ingresar
        JButton btnLogin = new JButton("Ingresar");
        btnLogin.setPreferredSize(new Dimension(120, 35));
        btnLogin.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLogin.addActionListener(this::loginAction);
        panel.add(btnLogin);
    }

    private void loginAction(ActionEvent evt) {
        String usuario = txtUsuario.getText();
        String password = new String(txtPassword.getPassword());

        if (usuario.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(panel,
                    "Usuario y contraseña son obligatorios",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ControladorUsuarios ctrlUsuarios = new ControladorUsuarios();
        if (ctrlUsuarios.autenticarUsuario(usuario, password) != null) {
            MainContainer.getInstance().showMainMenu();
        } else {
            JOptionPane.showMessageDialog(panel,
                    "Usuario o contraseña incorrectos",
                    "Error de autenticación",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}