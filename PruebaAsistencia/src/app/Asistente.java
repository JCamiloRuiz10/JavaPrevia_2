
package app;

import java.util.List;


public record Asistente (String nombre, String cedula, List<Sesion>listaSesionesAsistidas){
    @Override
    public String toString() {
        return "Asistente: " + nombre + " (Cédula: " + cedula + ")";
    }
}
    

