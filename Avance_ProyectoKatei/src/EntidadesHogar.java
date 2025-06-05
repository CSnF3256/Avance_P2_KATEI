
public class EntidadesHogar {

    public static class Integrante {
        private String nombre;
        private String rol;

        public Integrante(String nombre, String rol) {
            this.nombre = nombre;
            this.rol = rol;
        }

        public String getNombre() {
            return nombre;
        }

        public String getRol() {
            return rol;
        }

        @Override
        public String toString() {
            return nombre + " (" + rol + ")";
        }
    }

    public static class Tarea {
        private String descripcion;
        private int prioridad; //
        private  int presupuesto;
        private boolean ecofriendly;

        public Tarea(String descripcion, int prioridad, int presupuesto, boolean ecofriendly) {
            this.descripcion = descripcion;
            this.prioridad = prioridad;
            this.ecofriendly=ecofriendly;
            this.presupuesto = presupuesto;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public int getPrioridad() {
            return prioridad;
        }

        @Override
        public String toString() {
            return descripcion + " (Prioridad: " + prioridad + ")";
        }
    }
}