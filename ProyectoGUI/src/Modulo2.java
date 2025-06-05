import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Modulo2 {
    private JPanel pgeneral;
    private JTable table1listado;
    private JButton crearNuevaButton;

    private GestorTareas gestor;
    private DefaultTableModel modelo;

    public Modulo2(GestorTareas gestor) {
        this.gestor = gestor;

        modelo = new DefaultTableModel(new String[]{"Nombre", "Frecuencia", "Eco", "Encargado","Presupuesto"}, 0);
        table1listado.setModel(modelo);

        cargarTareas();

        crearNuevaButton.addActionListener(e -> cargarTareas()); // recargar desde lista
    }

    private void cargarTareas() {
        modelo.setRowCount(0); // limpiar tabla
        for (Tarea t : gestor.getTareas()) {
            modelo.addRow(new Object[]{
                    t.getNombre(),
                    t.getFrecuencia(),
                    t.isEcofriendly() ? "Sí" : "No",
                    t.getEncargado(),
                    t.getPresupuesto()
            });
        }
    }

    public JPanel getPanel() {
        return pgeneral;
    }
}

