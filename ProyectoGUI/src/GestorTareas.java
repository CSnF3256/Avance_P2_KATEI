import java.util.ArrayList;
import java.util.List;

public class GestorTareas {
    private List<Tarea> tareas = new ArrayList<>();

    public void agregarTarea(Tarea t) {
        tareas.add(t);
    }

    public List<Tarea> getTareas() {
        return tareas;
    }
}

