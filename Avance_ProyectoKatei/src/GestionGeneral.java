import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GestionGeneral {
    private ArrayList<EntidadesHogar.Integrante> listaIntegrantes;
    private ArrayList<EntidadesHogar.Tarea> listaTareasDisponibles;
    private HashMap<String, ArrayList<String>> asignaciones;
    private HashMap<String, Integer> desempeno;

    public GestionGeneral() {
        listaIntegrantes = new ArrayList<>();
        listaTareasDisponibles = new ArrayList<>();
        asignaciones = new HashMap<>();
        desempeno = new HashMap<>();
    }

    public void agregarIntegrante(String nombre, String rol) {
        if (listaIntegrantes.stream().noneMatch(i -> i.getNombre().equalsIgnoreCase(nombre))) {
            listaIntegrantes.add(new EntidadesHogar.Integrante(nombre, rol));
            desempeno.put(nombre, 0);
            System.out.println("Integrante agregado: " + nombre);
        } else {
            System.out.println("Integrante ya existe: " + nombre);
        }
    }

    public void agregarTarea(String descripcion, int prioridad, int presupuesto, boolean ecofriendly) {
        if (listaTareasDisponibles.stream().noneMatch(t -> t.getDescripcion().equalsIgnoreCase(descripcion))) {
            listaTareasDisponibles.add(new EntidadesHogar.Tarea(descripcion, prioridad, presupuesto, ecofriendly));
            System.out.println("Tarea agregada: " + descripcion);
        } else {
            System.out.println("Tarea ya existe en la lista de disponibles: " + descripcion);
        }
    }

    public ArrayList<String> getNombresIntegrantes() {
        ArrayList<String> nombres = new ArrayList<>();
        for (EntidadesHogar.Integrante i : listaIntegrantes) {
            nombres.add(i.getNombre());
        }
        return nombres;
    }

    public ArrayList<String> getDescripcionesTareasDisponibles() {
        ArrayList<String> descripciones = new ArrayList<>();
        for (EntidadesHogar.Tarea t : listaTareasDisponibles) {
            descripciones.add(t.getDescripcion());
        }
        return descripciones;
    }

    public void asignarTarea(String dia, String tareaDescripcion, String integranteNombre) {
        EntidadesHogar.Tarea tareaAAsignar = null;
        for (EntidadesHogar.Tarea t : listaTareasDisponibles) {
            if (t.getDescripcion().equalsIgnoreCase(tareaDescripcion)) {
                tareaAAsignar = t;
                break;
            }
        }

        if (tareaAAsignar == null) {
            System.out.println("Error: La tarea '" + tareaDescripcion + "' no está disponible para asignar.");
            return;
        }
        if (listaIntegrantes.stream().noneMatch(i -> i.getNombre().equalsIgnoreCase(integranteNombre))) {
            System.out.println("Error: El integrante '" + integranteNombre + "' no existe.");
            return;
        }

        if (!asignaciones.containsKey(dia)) {
            asignaciones.put(dia, new ArrayList<>());
        }
        String tareaAsignadaInfo = tareaDescripcion + " (Asignada a: " + integranteNombre + ")";
        asignaciones.get(dia).add(tareaAsignadaInfo);
        listaTareasDisponibles.remove(tareaAAsignar);
        registrarPuntos(integranteNombre, 10);
        System.out.println("Tarea '" + tareaDescripcion + "' asignada a '" + integranteNombre + "' el día '" + dia + "'.");
    }

    public void registrarPuntos(String nombreIntegrante, int puntos) {
        desempeno.put(nombreIntegrante, desempeno.getOrDefault(nombreIntegrante, 0) + puntos);
        System.out.println(puntos + " puntos registrados para " + nombreIntegrante + ". Total: " + desempeno.get(nombreIntegrante));
    }

    public Map<String, Integer> getDesempenoIntegrantes() {
        return new HashMap<>(desempeno);
    }

    public HashMap<String, ArrayList<String>> getAsignaciones() {
        return asignaciones;
    }

    public ArrayList<String> getSugerencias() {
        ArrayList<String> sugerencias = new ArrayList<>();
        sugerencias.add("Apaga las luces al salir de una habitación.");
        sugerencias.add("Reutiliza el agua siempre que puedas (ej. para regar plantas).");
        sugerencias.add("Separa adecuadamente los residuos para facilitar el reciclaje.");
        sugerencias.add("Desenchufa aparatos electrónicos que no estés usando.");
        sugerencias.add("Utiliza bombillas de bajo consumo (LED).");
        return sugerencias;
    }
}