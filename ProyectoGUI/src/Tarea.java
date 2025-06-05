public class Tarea {
    private String nombre;
    private String frecuencia;
    private boolean ecofriendly;
    private String encargado;
    private int presupuesto;

    public Tarea(String nombre, String frecuencia, boolean ecofriendly, String encargado, int presupuesto) {
        this.nombre = nombre;
        this.frecuencia = frecuencia;
        this.ecofriendly = ecofriendly;
        this.encargado = encargado;
        this.presupuesto = presupuesto;
    }

    public int getPresupuesto() {
        return presupuesto;
    }

    public String getNombre() { return nombre; }
    public String getFrecuencia() { return frecuencia; }
    public boolean isEcofriendly() { return ecofriendly; }
    public String getEncargado() { return encargado; }

    public String toString() {
        return nombre + " (" + frecuencia + ") - " + (ecofriendly ? "Eco" : "Normal");
    }
}

