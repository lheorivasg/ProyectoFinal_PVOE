/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import modelo.Gimnasio;

public class ControladorPrincipal {

    private Gimnasio gimnasio;
    private static ControladorPrincipal instance;

    private ControladorPrincipal() {
        this.gimnasio = new Gimnasio();
    }

    public static ControladorPrincipal getInstance() {
        if (instance == null) {
            instance = new ControladorPrincipal();
        }
        return instance;
    }

    public boolean autenticarUsuario(String username, String password) {
        return gimnasio.autenticarUsuario(username, password) != null;
    }

    public String obtenerNombreUsuario(String username) {
        for (modelo.Usuario usuario : gimnasio.getUsuarios()) {
            if (usuario.getUsername().equals(username)) {
                return usuario.getNombreCompleto();
            }
        }
        return "";
    }

    public Gimnasio getGimnasio() {
        return gimnasio;
    }

    public void setGimnasio(Gimnasio gimnasio) {
        this.gimnasio = gimnasio;
    }
}
