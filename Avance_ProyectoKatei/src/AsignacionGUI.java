import javax.swing.*;
import java.util.ArrayList;

public class AsignacionGUI {
    private JComboBox<String> actividadComboBox;
    private JComboBox<String> integranteComboBox;
    private JComboBox<String> diaComboBox;
    private JPanel gneral;
    private JButton agregarButton;

    private GestionGeneral gestion;

    public AsignacionGUI(GestionGeneral gestion) {
        this.gestion = gestion;


        cargarDatosComboBox(); // Cargar datos iniciales

        if (agregarButton == null) {
            System.err.println("AsignacionGUI CRÍTICO: agregarButton es null. Revisa 'field name' en AsignacionGUI.form.");
        } else {
            agregarButton.addActionListener(e -> {
                String tareaSeleccionada = (String) actividadComboBox.getSelectedItem();
                String integranteSeleccionado = (String) integranteComboBox.getSelectedItem();
                String diaSeleccionado = (String) diaComboBox.getSelectedItem();

                if (isNullOrPlaceholder(tareaSeleccionada, "No hay tareas disponibles") ||
                        isNullOrPlaceholder(integranteSeleccionado, "No hay integrantes") ||
                        diaSeleccionado == null || diaSeleccionado.isEmpty() || diaSeleccionado.equals("Seleccione un día")) { // Ajusta el placeholder de día si es necesario
                    JOptionPane.showMessageDialog(gneral, "Todos los campos son obligatorios y válidos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                this.gestion.asignarTarea(diaSeleccionado, tareaSeleccionada, integranteSeleccionado);
                JOptionPane.showMessageDialog(gneral, "Tarea asignada.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarDatosComboBox(); // Recargar para reflejar cambios
            });
        }
    }

    private boolean isNullOrPlaceholder(String value, String placeholder) {
        return value == null || value.isEmpty() || value.equals(placeholder);
    }

    public void cargarDatosComboBox() {
        if (actividadComboBox == null || integranteComboBox == null) {
            System.err.println("Error en el combobox");
            return;
        }

        actividadComboBox.removeAllItems();
        ArrayList<String> tareasDisponibles = gestion.getDescripcionesTareasDisponibles();
        if (tareasDisponibles.isEmpty()) {
            actividadComboBox.addItem("No hay tareas disponibles");
        } else {
            for (String tarea : tareasDisponibles) {
                actividadComboBox.addItem(tarea);
            }
        }

        integranteComboBox.removeAllItems();
        ArrayList<String> nombresIntegrantes = gestion.getNombresIntegrantes();
        if (nombresIntegrantes.isEmpty()) {
            integranteComboBox.addItem("No hay integrantes");
        } else {
            for (String nombre : nombresIntegrantes) {
                integranteComboBox.addItem(nombre);
            }
        }
    }

    public JPanel getPanel() {
        if (gneral == null) {
            System.err.println("AsignacionGUI CRÍTICO: gneral (panel principal) es null. Revisa 'field name' en AsignacionGUI.form.");
            JPanel errorPanel = new JPanel();
            errorPanel.add(new JLabel("Error al cargar panel de AsignacionGUI."));
            return errorPanel;
        }
        return gneral;
    }
}