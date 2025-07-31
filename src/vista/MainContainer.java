/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MainContainer extends JFrame {
    private static MainContainer instance;
    private JPanel currentPanel;
    
    // Registro de tamaños preferidos para cada vista
    private static final Map<Class<?>, Dimension> sizeRegistry = new HashMap<>();
    
    static {
        // Registrar tamaños específicos para cada vista
        sizeRegistry.put(LoginView.class, new Dimension(500, 450));
        sizeRegistry.put(RegistroAsistentesView.class, new Dimension(800, 700));
        sizeRegistry.put(EditarAsistenteView.class, new Dimension(800, 700));
        sizeRegistry.put(ConsultaAsistentesView.class, new Dimension(1000, 750));
        sizeRegistry.put(DetalleAsistenteView.class, new Dimension(850, 650));
        sizeRegistry.put(InscripcionActividadesView.class, new Dimension(900, 600));
        sizeRegistry.put(ReportesView.class, new Dimension(900, 600));
        // Tamaño por defecto para las demás vistas
        sizeRegistry.put(MainView.class, new Dimension(600, 500));
    }

    public MainContainer() {
        initComponents();
    }
    
    private void initComponents() {
        setTitle("Sistema de Gestión de Gimnasio - Azcapotzalco");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Configuración básica de la ventana
        setMinimumSize(new Dimension(400, 300));
        setLocationRelativeTo(null);
        
        showLogin();
    }
    
    public static MainContainer getInstance() {
        if (instance == null) {
            instance = new MainContainer();
        }
        return instance;
    }
    
    private void changePanel(JPanel newPanel) {
        if (currentPanel != null) {
            remove(currentPanel);
        }
        currentPanel = newPanel;
        add(currentPanel, BorderLayout.CENTER);
        
        // Ajustar tamaño basado en el contenido
        adjustWindowSize();
        
        revalidate();
        repaint();
    }
    
    private void adjustWindowSize() {
        // Obtener el tamaño preferido del panel actual
        Dimension preferredSize = currentPanel.getPreferredSize();
        
        // Si no tiene tamaño preferido, usar el registrado
        if (preferredSize == null || preferredSize.width == 0) {
            for (Map.Entry<Class<?>, Dimension> entry : sizeRegistry.entrySet()) {
                if (entry.getKey().isInstance(currentPanel)) {
                    preferredSize = entry.getValue();
                    break;
                }
            }
        }
        
        // Si aún no tenemos tamaño, usar uno por defecto
        if (preferredSize == null) {
            preferredSize = new Dimension(800, 600);
        }
        
        // Ajustar margen adicional
        setSize(new Dimension(
            (int)(preferredSize.width * 1.05), 
            (int)(preferredSize.height * 1.05)
        ));
        
        // Asegurar que no sea más pequeño que el mínimo
        if (getWidth() < getMinimumSize().width) {
            setSize(getMinimumSize().width, getHeight());
        }
        if (getHeight() < getMinimumSize().height) {
            setSize(getWidth(), getMinimumSize().height);
        }
        
        setLocationRelativeTo(null);
    }
    
    // Métodos para mostrar las diferentes vistas
    public void showLogin() {
        LoginView loginView = new LoginView();
        loginView.getPanel().setPreferredSize(sizeRegistry.get(LoginView.class));
        changePanel(loginView.getPanel());
    }
    
    public void showRegistroAsistentes() {
        RegistroAsistentesView registroView = new RegistroAsistentesView();
        registroView.getPanel().setPreferredSize(sizeRegistry.get(RegistroAsistentesView.class));
        changePanel(registroView.getPanel());
    }
    
    public void showMainMenu() {
        MainView mainView = new MainView();
        mainView.getPanel().setPreferredSize(sizeRegistry.get(MainView.class));
        changePanel(mainView.getPanel());
    }
    
    public void showConsultaAsistentes() {
        ConsultaAsistentesView consultaView = new ConsultaAsistentesView();
        consultaView.getPanel().setPreferredSize(sizeRegistry.get(ConsultaAsistentesView.class));
        changePanel(consultaView.getPanel());
    }
    
    public void showDetalleAsistente(String idAsistente) {
        DetalleAsistenteView detalleView = new DetalleAsistenteView(idAsistente);
        detalleView.getPanel().setPreferredSize(sizeRegistry.get(DetalleAsistenteView.class));
        changePanel(detalleView.getPanel());
    }
    
    public void showEditarAsistente(String idAsistente) {
        EditarAsistenteView editarView = new EditarAsistenteView(idAsistente);
        editarView.getPanel().setPreferredSize(sizeRegistry.get(EditarAsistenteView.class));
        changePanel(editarView.getPanel());
    }
    
    public void showInscripcionActividades() {
        InscripcionActividadesView inscripcionView = new InscripcionActividadesView();
        inscripcionView.getPanel().setPreferredSize(sizeRegistry.get(InscripcionActividadesView.class));
        changePanel(inscripcionView.getPanel());
    }
    
    public void showReportes() {
        ReportesView reportesView = new ReportesView();
        reportesView.getPanel().setPreferredSize(sizeRegistry.get(ReportesView.class));
        changePanel(reportesView.getPanel());
    }
    
    // Método para registrar tamaños personalizados
    public static void registerViewSize(Class<?> viewClass, Dimension size) {
        sizeRegistry.put(viewClass, size);
    }
}