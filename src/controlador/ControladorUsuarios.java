/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import modelo.Usuario;
import modelo.Gimnasio;

public class ControladorUsuarios {

    private Gimnasio gimnasio;

    public ControladorUsuarios() {
        this.gimnasio = ControladorPrincipal.getInstance().getGimnasio();
    }

    public Usuario autenticarUsuario(String username, String password) {
        return gimnasio.autenticarUsuario(username, password);
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
