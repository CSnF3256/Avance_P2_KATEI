import javax.swing.*;

public class TareasGUI {

    private JPanel pTareas;
    private JTextField textField1;
    private JComboBox<String> comboBox1;
    private JButton agregarTareaButton;
    private JTextField textField2;
    private JCheckBox siCheckBox;

    private GestionGeneral gestion;

    public TareasGUI(GestionGeneral gestion) {
        this.gestion = gestion;

        if (agregarTareaButton == null) {
            System.err.println("TareasGUI CRÍTICO: agregarTareaButton es null. Revisa 'field name' en TareasGUI.form.");
        } else {
            agregarTareaButton.addActionListener(e -> {
                String descripcion = textField1.getText().trim();
                String prioridadStrCompleta = (String) comboBox1.getSelectedItem();
                int presupuesto = Integer.parseInt(textField2.getText());
                boolean ecofriendly = siCheckBox.isBorderPainted();
                int prioridad = -1;

                if (descripcion.isEmpty()) {
                    JOptionPane.showMessageDialog(pTareas, "Descripción no puede estar vacía.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (prioridadStrCompleta == null || prioridadStrCompleta.isEmpty()) {
                    JOptionPane.showMessageDialog(pTareas, "Seleccione una prioridad.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    String prioridadNumStr = prioridadStrCompleta.split("\\s+")[0].replaceAll("[^0-9]", "");
                    if (!prioridadNumStr.isEmpty()) {
                        prioridad = Integer.parseInt(prioridadNumStr);
                    } else {
                        JOptionPane.showMessageDialog(pTareas, "Formato de prioridad no válido.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(pTareas, "Error al leer la prioridad.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                boolean existe = this.gestion.getDescripcionesTareasDisponibles().stream()
                        .anyMatch(descExistente -> descExistente.equalsIgnoreCase(descripcion));
                if (existe) {
                    JOptionPane.showMessageDialog(pTareas, "La tarea '" + descripcion + "' ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    this.gestion.agregarTarea(descripcion, prioridad, presupuesto, ecofriendly);
                    JOptionPane.showMessageDialog(pTareas, "Tarea '" + descripcion + "' agregada.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    textField1.setText("");
                    // comboBox1.setSelectedIndex(0); // Opcional
                }
            });
        }
    }

    public JPanel getPanel() {
        if (pTareas == null) {
            System.err.println("Error");
            JPanel errorPanel = new JPanel();
            errorPanel.add(new JLabel("Error al cargar panel de TareasGUI."));
            return errorPanel;
        }
        return pTareas;
    }
}