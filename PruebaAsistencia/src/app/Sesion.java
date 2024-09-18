
package app;

import java.util.List;


public record Sesion (String idSesion, String nombre, String tipoSesion, List<Asistente>listaAsistentes){
    @Override
    public String toString() {
        return "Sesión: " + nombre + " (ID: " + idSesion + ", Tipo: " + tipoSesion + ")";
    }
}
