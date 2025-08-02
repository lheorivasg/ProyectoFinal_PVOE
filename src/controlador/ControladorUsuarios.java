/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import modelo.Usuario;
import modelo.Gimnasio;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ControladorUsuarios {
    private Gimnasio gimnasio;
    private static final String ARCHIVO_ADMIN = "administra.txt";

    public ControladorUsuarios() {
        this.gimnasio = ControladorPrincipal.getInstance().getGimnasio();
        cargarUsuariosDesdeArchivo();
    }

    private void cargarUsuariosDesdeArchivo() {
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_ADMIN))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 4) {
                    Usuario usuario = new Usuario(
                            datos[0].trim(),  // username
                            datos[1].trim(),  // password
                            datos[2].trim(),  // nombreCompleto
                            datos[3].trim()   // rol
                    );
                    if (!existeUsuario(usuario.getUsername())) {
                        gimnasio.getUsuarios().add(usuario);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar usuarios: " + e.getMessage());
            // Cargar usuarios por defecto si el archivo no existe
            if (!existeUsuario("admin")) {
                gimnasio.getUsuarios().add(new Usuario("admin", "admin123", "Administrador Principal", "admin"));
            }
        }
    }

    private boolean existeUsuario(String username) {
        for (Usuario u : gimnasio.getUsuarios()) {
            if (u.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public Usuario autenticarUsuario(String username, String password) {
        for (Usuario u : gimnasio.getUsuarios()) {
            if (u.validarCredenciales(username, password)) {
                return u;
            }
        }
        return null;
    }

    public boolean usuarioTienePermisosAdmin(String username) {
        for (Usuario u : gimnasio.getUsuarios()) {
            if (u.getUsername().equals(username)) {
                return u.getRol().equals("admin");
            }
        }
        return false;
    }
}