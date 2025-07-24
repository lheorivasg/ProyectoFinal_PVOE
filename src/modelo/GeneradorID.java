/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.UUID;

public class GeneradorID {

    public static String generarIDAsistente() {
        return "ASIS-" + UUID.randomUUID().toString().substring(0, 8);
    }

    public static String generarIDActividad() {
        return "ACT-" + UUID.randomUUID().toString().substring(0, 8);
    }
}
