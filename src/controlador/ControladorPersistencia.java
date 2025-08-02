/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import modelo.Gimnasio;

public class ControladorPersistencia {
    private static final String ARCHIVO_DATOS = "gimnasio.dat";

    public boolean guardarDatos(Gimnasio gimnasio) {
        try (FileOutputStream fos = new FileOutputStream(ARCHIVO_DATOS);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gimnasio);
            return true;
        } catch (IOException e) {
            System.err.println("Error al guardar los datos: " + e.getMessage());
            return false;
        }
    }

    public Gimnasio cargarDatos() {
        try (FileInputStream fis = new FileInputStream(ARCHIVO_DATOS);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (Gimnasio) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar los datos. Se creará un nuevo sistema. " + e.getMessage());
            return new Gimnasio();
        }
    }
}