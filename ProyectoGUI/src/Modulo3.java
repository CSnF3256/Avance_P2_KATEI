import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Modulo3 {
    private final Modulo1 modulo1;
    private JPanel general;
    private JComboBox comboBox1;
    private JTextField textField1nombre;
    private JTextArea listadodeinetgrantes;
    private JButton añadirUsuarioButton;
    private JTextField textField1;
    private JButton eliminarUsuarioPorNombreButton;
    private GestorUsuarios gestorUsuarios;

    public Modulo3(GestorUsuarios gestorUsuarios, Modulo1 modulo1) {
        this.gestorUsuarios = gestorUsuarios;
        this.modulo1 = modulo1;

        actualizarListado();

        añadirUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = textField1nombre.getText();
                if (!nombre.isEmpty()) {
                    gestorUsuarios.agregarUsuario(new Usuario(nombre));
                    textField1nombre.setText("");
                    actualizarListado();
                    modulo1.actualizarComboEncargado();
                } else {
                    JOptionPane.showMessageDialog(null, "Nombre vacío");
                }
            }
        });

        eliminarUsuarioPorNombreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = textField1.getText();
                gestorUsuarios.eliminarUsuarioPorNombre(nombre);
                textField1.setText("");
                actualizarListado();
            }
        });
    }

    private void actualizarListado() {
        StringBuilder sb = new StringBuilder();
        for (Usuario u : gestorUsuarios.getUsuarios()) {
            sb.append("- ").append(u.getNombre()).append("\n");
        }
        listadodeinetgrantes.setText(sb.toString());
    }

    public JPanel getPanel() {
        return general;
    }
}

