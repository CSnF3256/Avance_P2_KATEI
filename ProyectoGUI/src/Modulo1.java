import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Modulo1 {

    private JPanel pGeneral;
    private JTextField textField1nombretarea;
    private JComboBox comboBox1frecuencia;
    private JCheckBox siCheckBox;
    private JButton guardarButton;
    private JComboBox comboBox1encargado;
    private JTextField textField1;
    private GestorTareas gestor;
    private final GestorUsuarios gestorUsuarios;


    public Modulo1(GestorTareas gestor, GestorUsuarios gestorUsuarios) {
        this.gestor = gestor;
        this.gestorUsuarios = gestorUsuarios;
        actualizarComboEncargado();

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = textField1nombretarea.getText();
                String frecuencia = (String) comboBox1frecuencia.getSelectedItem();
                boolean eco = siCheckBox.isSelected();
                String encargado = (String) comboBox1encargado.getSelectedItem();
                int presupuesto = Integer.parseInt(textField1.getText());

                if (nombre.isEmpty() || frecuencia == null || encargado == null) {
                    JOptionPane.showMessageDialog(null, "Completa todos los campos.");
                    return;
                }

                Tarea nueva = new Tarea(nombre, frecuencia, eco, encargado, presupuesto);
                gestor.agregarTarea(nueva);
                JOptionPane.showMessageDialog(null, "Tarea guardada.");
                textField1nombretarea.setText("");
            }
        });
    }

    public JPanel getPanel() {
        return pGeneral;
    }
    public void actualizarComboEncargado() {
        comboBox1encargado.removeAllItems();
        for (Usuario u : GestorUsuarios.getUsuarios()) {
            comboBox1encargado.addItem(u.getNombre());
        }
    }
}

