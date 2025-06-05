import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HistorialGUI {

    private JPanel pHistorial;
    private JTextArea textArea1;
    private JButton mostrarHistorialButton;

    private GestionGeneral gestion;

    public HistorialGUI(GestionGeneral gestion) {
        this.gestion = gestion;

        if (mostrarHistorialButton == null) {
            System.err.println("HistorialGUI CRÍTICO: mostrarHistorialButton es null. Revisa que el 'field name' en HistorialGUI.form sea 'mostrarHistorialButton'.");
        } else {
            mostrarHistorialButton.addActionListener(e -> {
                mostrarTareasAsignadas();
            });
        }

        // Mostrar historial por defecto al abrir, si textArea1 está listo
        if (textArea1 != null) {
            mostrarTareasAsignadas();
        } else {
            // Esto puede pasar si pHistorial es null y por ende textArea1 no se inicializa.
            System.err.println("El historial por defecto no se puede mostrar.");
        }
    }

    private void mostrarTareasAsignadas() {
        if (textArea1 == null) {
            System.err.println("No se puede mostrar el historial.");
            return;
        }
        StringBuilder historialTexto = new StringBuilder("--- HISTORIAL DE TAREAS ASIGNADAS ---\n\n");
        HashMap<String, ArrayList<String>> asignaciones = gestion.getAsignaciones();

        if (asignaciones.isEmpty()) {
            historialTexto.append("Aún no se han asignado tareas.");
        } else {
            for (Map.Entry<String, ArrayList<String>> entry : asignaciones.entrySet()) {
                String dia = entry.getKey();
                ArrayList<String> tareasDelDia = entry.getValue();
                historialTexto.append("Día: ").append(dia).append("\n");
                if (tareasDelDia.isEmpty()) {
                    historialTexto.append("  - Ninguna tarea asignada para este día.\n");
                } else {
                    for (String tareaInfo : tareasDelDia) {
                        historialTexto.append("  -> ").append(tareaInfo).append("\n");
                    }
                }
                historialTexto.append("\n");
            }
        }
        textArea1.setText(historialTexto.toString());
        textArea1.setCaretPosition(0);
    }

    // private void mostrarPuntos() { ... } //se anadira esta logica para el sistema de gamificacion de la aplicacion

    public JPanel getPanel() {
        if (pHistorial == null) {
            System.err.println("Error");
            JPanel errorPanel = new JPanel();
            errorPanel.add(new JLabel("Error al cargar panel de HistorialGUI."));
            return errorPanel;
        }
        return pHistorial;
    }
}