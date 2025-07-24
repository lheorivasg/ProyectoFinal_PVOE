/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controlador.ControladorUsuarios;

public class LoginView extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private ControladorUsuarios ctrlUsuarios;

    public LoginView() {
        ctrlUsuarios = new ControladorUsuarios();
        initComponents();
    }

    private void initComponents() {
        setTitle("Gimnasio Deportivo - Azcapotzalco: Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblUsuario = new JLabel("Usuario:");
        txtUsuario = new JTextField(15);

        JLabel lblPassword = new JLabel("Contraseña:");
        txtPassword = new JPasswordField(15);

        btnLogin = new JButton("Ingresar");
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                loginAction(evt);
            }
        });

        panel.add(lblUsuario);
        panel.add(txtUsuario);
        panel.add(Box.createVerticalStrut(10));
        panel.add(lblPassword);
        panel.add(txtPassword);
        panel.add(Box.createVerticalStrut(20));
        panel.add(btnLogin);

        add(panel);
    }

    private void loginAction(ActionEvent evt) {
        String usuario = txtUsuario.getText();
        String password = new String(txtPassword.getPassword());

        if (ctrlUsuarios.autenticarUsuario(usuario, password) != null) {
            new MainView().setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos",
                    "Error de autenticación", JOptionPane.ERROR_MESSAGE);
        }
    }
}
