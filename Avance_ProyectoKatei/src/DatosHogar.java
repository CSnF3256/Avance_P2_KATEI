public class DatosHogar {

    public static class NodoIntegrante {
        String nombre;
        String rol;
        NodoIntegrante sig;

        public NodoIntegrante(String nombre, String rol) {
            this.nombre = nombre;
            this.rol = rol;
            this.sig = null;
        }
    }

    public static class NodoTarea {
        String descripcion;
        int prioridad;
        boolean ecofriendly;
        int presupuesto;
        boolean asignada;
        NodoTarea sig;

        public NodoTarea(String descripcion, int prioridad, boolean ecofriendly,
        int presupuesto) {
            this.descripcion = descripcion;
            this.prioridad = prioridad;
            this.presupuesto=presupuesto;
            this.ecofriendly = ecofriendly;
            this.asignada = false;
            this.sig = null;
        }
    }

    NodoIntegrante cabezaIntegrante = null;
    NodoTarea cabezaTarea = null;

    public void agregarIntegrante(String nombre, String rol) {
        NodoIntegrante nuevo = new NodoIntegrante(nombre, rol);
        if (cabezaIntegrante == null) {
            cabezaIntegrante = nuevo;
        } else {
            NodoIntegrante temp = cabezaIntegrante;
            while (temp.sig != null) temp = temp.sig;
            temp.sig = nuevo;
        }
    }

    public void agregarTarea(String descripcion, int prioridad, boolean ecofriendly, int presupuesto) {
        NodoTarea nueva = new NodoTarea(descripcion, prioridad, ecofriendly, presupuesto);
        if (cabezaTarea == null) {
            cabezaTarea = nueva;
        } else {
            NodoTarea temp = cabezaTarea;
            while (temp.sig != null) temp = temp.sig;
            temp.sig = nueva;
        }
    }

    public String obtenerIntegrantesComoTexto() {
        StringBuilder sb = new StringBuilder();
        NodoIntegrante temp = cabezaIntegrante;
        while (temp != null) {
            sb.append(temp.nombre).append(" - ").append(temp.rol).append("\n");
            temp = temp.sig;
        }
        return sb.toString();
    }

    public String obtenerTareasComoTexto() {
        StringBuilder sb = new StringBuilder();
        NodoTarea temp = cabezaTarea;
        while (temp != null) {
            sb.append(temp.descripcion)
                    .append(" - Prioridad: ").append(temp.prioridad)
                    .append(temp.asignada ? " (Asignada)" : "")
                    .append("\n");
            temp = temp.sig;
        }
        return sb.toString();
    }

    public String[] obtenerNombresIntegrantes() {
        int count = contarIntegrantes();
        String[] nombres = new String[count];
        NodoIntegrante temp = cabezaIntegrante;
        for (int i = 0; i < count; i++) {
            nombres[i] = temp.nombre;
            temp = temp.sig;
        }
        return nombres;
    }

    public String[] obtenerTareasNoAsignadas() {
        int count = contarTareasNoAsignadas();
        String[] tareas = new String[count];
        NodoTarea temp = cabezaTarea;
        int i = 0;
        while (temp != null) {
            if (!temp.asignada) {
                tareas[i++] = temp.descripcion;
            }
            temp = temp.sig;
        }
        return tareas;
    }

    public boolean marcarTareaComoAsignada(String descripcion) {
        NodoTarea temp = cabezaTarea;
        while (temp != null) {
            if (temp.descripcion.equals(descripcion)) {
                temp.asignada = true;
                return true;
            }
            temp = temp.sig;
        }
        return false;
    }

    private int contarIntegrantes() {
        int count = 0;
        NodoIntegrante temp = cabezaIntegrante;
        while (temp != null) {
            count++;
            temp = temp.sig;
        }
        return count;
    }

    private int contarTareasNoAsignadas() {
        int count = 0;
        NodoTarea temp = cabezaTarea;
        while (temp != null) {
            if (!temp.asignada) count++;
            temp = temp.sig;
        }
        return count;
    }
}
