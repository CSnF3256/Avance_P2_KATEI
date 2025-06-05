import javax.swing.*;
import java.util.ArrayList;

public class HogarGUI {

    private JPanel pGeneral;
    private JButton usuariosButton;
    private JButton tareasButton;
    private JButton historialButton;
    private JButton ecoSugerenciasButton;
    private JButton asignarTareaButton;

    private static final GestionGeneral gestion = new GestionGeneral();

    public HogarGUI() {

        if (usuariosButton == null) {
            System.err.println("Error");
        } else {
            usuariosButton.addActionListener(e -> abrirVentanaUsuario());
        }

        if (tareasButton == null) {
            System.err.println("Error");
        } else {
            tareasButton.addActionListener(e -> abrirVentanaTareas());
        }

        if (asignarTareaButton == null) {
            System.err.println("Error");
        } else {
            asignarTareaButton.addActionListener(e -> abrirVentanaAsignacion());
        }

        if (historialButton == null) {
            System.err.println("Error");
        } else {
            historialButton.addActionListener(e -> abrirVentanaHistorial());
        }

        if (ecoSugerenciasButton == null) {
            System.err.println("Error");
        } else {
            ecoSugerenciasButton.addActionListener(e -> mostrarEcoSugerencias());
        }
    }

    private void abrirVentanaUsuario() {
        JFrame usuarioFrame = new JFrame("Gestión de Usuarios");
        UsuarioGUI usuarioGUI = new UsuarioGUI(gestion);
        configurarYMostrarFrame(usuarioFrame, usuarioGUI.getPanel(), "Usuarios");
    }

    private void abrirVentanaTareas() {
        JFrame tareasFrame = new JFrame("Gestión de Tareas");
        TareasGUI tareasGUI = new TareasGUI(gestion);
        configurarYMostrarFrame(tareasFrame, tareasGUI.getPanel(), "Tareas");
    }

    private void abrirVentanaAsignacion() {
        JFrame asignarFrame = new JFrame("Asignar Tarea");
        AsignacionGUI asignacionGUI = new AsignacionGUI(gestion);
        // Para asegurar que los comboboxes están actualizados al abrir:
        asignacionGUI.cargarDatosComboBox();
        configurarYMostrarFrame(asignarFrame, asignacionGUI.getPanel(), "Asignación");
    }

    private void abrirVentanaHistorial() {
        JFrame historialFrame = new JFrame("Historial de Tareas");
        HistorialGUI historialGUI = new HistorialGUI(gestion);
        configurarYMostrarFrame(historialFrame, historialGUI.getPanel(), "Historial");
    }

    private void configurarYMostrarFrame(JFrame frame, JPanel panel, String nombreVentana) {
        if (panel.getComponentCount() > 0 && panel.getComponent(0) instanceof JLabel &&
                ((JLabel) panel.getComponent(0)).getText().startsWith("Error al cargar panel")) {
            JOptionPane.showMessageDialog(pGeneral, "No se pudo cargar la ventana de " + nombreVentana + ".\nRevise la consola.", "Error de Carga", JOptionPane.ERROR_MESSAGE);
        } else {
            frame.setContentPane(panel);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Importante para ventanas secundarias
            frame.pack();
            frame.setLocationRelativeTo(pGeneral); // Centrar relativo a la ventana principal HogarGUI
            frame.setVisible(true);
        }
    }

    private void mostrarEcoSugerencias() {
        ArrayList<String> sugerencias = gestion.getSugerencias();
        StringBuilder mensaje = new StringBuilder("💡 Sugerencias Ecológicas para el Hogar:\n\n");
        if (sugerencias.isEmpty()) {
            mensaje.append("No hay sugerencias disponibles.");
        } else {
            for (String s : sugerencias) {
                mensaje.append(" • ").append(s).append("\n");
            }
        }
        JOptionPane.showMessageDialog(pGeneral, mensaje.toString(), "Eco Sugerencias", JOptionPane.INFORMATION_MESSAGE);
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("HogarGUI");
        frame.setContentPane(new HogarGUI().pGeneral);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

