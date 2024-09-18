
package app;

import java.util.List;

public record Certificado (String idAsistencia, String asistente, List<Sesion> listaSesionesCompletadas){
    @Override
    public String toString() {
        return "Certificado para " + asistente + ", Ha completado sus sesiones";
    }
}
