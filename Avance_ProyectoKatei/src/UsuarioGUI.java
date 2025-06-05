import javax.swing.*;

public class UsuarioGUI {
    private JPanel pUsuario;
    private JTextField textField1;
    private JComboBox<String> comboBox1;
    private JButton agregarIntegranteButton;

    private GestionGeneral gestion;

    public UsuarioGUI(GestionGeneral gestion) {
        this.gestion = gestion;
        if (agregarIntegranteButton == null) {
            System.err.println("UsuarioGUI CRÍTICO: agregarIntegranteButton es null. Revisa 'field name' en UsuarioGUI.form.");
        } else {
            agregarIntegranteButton.addActionListener(e -> {
                String nombre = textField1.getText().trim();
                String rol = (String) comboBox1.getSelectedItem();

                if (!nombre.isEmpty() && rol != null && !rol.isEmpty()) {
                    boolean existe = this.gestion.getNombresIntegrantes().stream()
                            .anyMatch(nombreExistente -> nombreExistente.equalsIgnoreCase(nombre));
                    if (existe) {
                        JOptionPane.showMessageDialog(pUsuario, "El integrante '" + nombre + "' ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        this.gestion.agregarIntegrante(nombre, rol);
                        JOptionPane.showMessageDialog(pUsuario, "Integrante '" + nombre + "' agregado.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        textField1.setText("");
                        // comboBox1.setSelectedIndex(0); // Opcional
                    }
                } else {
                    JOptionPane.showMessageDialog(pUsuario, "Nombre y rol son obligatorios.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
                }
            });
        }
    }

    public JPanel getPanel() {
        if (pUsuario == null) {
            JPanel errorPanel = new JPanel();
            errorPanel.add(new JLabel("Error al cargar panel de UsuarioGUI."));
            return errorPanel;
        }
        return pUsuario;
    }
}