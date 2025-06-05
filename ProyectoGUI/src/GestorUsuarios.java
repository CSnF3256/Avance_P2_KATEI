import java.util.ArrayList;
import java.util.List;

public class GestorUsuarios {
    private static List<Usuario> usuarios = new ArrayList<>();

    public void agregarUsuario(Usuario u) {
        usuarios.add(u);
    }

    public void eliminarUsuarioPorNombre(String nombre) {
        usuarios.removeIf(u -> u.getNombre().equalsIgnoreCase(nombre));
    }

    public static List<Usuario> getUsuarios() {
        return usuarios;
    }
}
