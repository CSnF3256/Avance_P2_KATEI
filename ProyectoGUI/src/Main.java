import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        GestorTareas gestorTareas = new GestorTareas();
        GestorUsuarios gestorUsuarios = new GestorUsuarios();

        JFrame frame = new JFrame("Sistema de Gestión del Hogar");
        Modulo1 modulo1 = new Modulo1(gestorTareas, gestorUsuarios);
        Modulo3 modulo3 = new Modulo3(gestorUsuarios, modulo1);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Usuarios del Hogar", modulo3.getPanel());
        tabs.addTab("Registrar Tareas", modulo1.getPanel());
        tabs.addTab("Ver Tareas", new Modulo2(gestorTareas).getPanel());


        frame.setContentPane(tabs);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setVisible(true);
    }
}

