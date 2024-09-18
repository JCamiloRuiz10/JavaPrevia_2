
package app;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Conferencia {

    private static Conferencia instance;
    private List<Asistente> listaAsistentes;
    private List<Sesion> listaSesiones;
    private List <Certificado> listaCertificados;

    private Conferencia() {
        listaAsistentes = new ArrayList<>();
        listaSesiones = new ArrayList<>();
        listaCertificados = new ArrayList<>();
    }

    public static Conferencia getInstance() {
        if (instance == null) {
            instance = new Conferencia();
        }
        return instance;
    }

    public void agregarSesion(){
       String id = JOptionPane.showInputDialog("Ingrese id de la Sesion: ");
       String nombre = JOptionPane.showInputDialog("Ingrese nombre de la Sesion: ");
       String tipoSesion = JOptionPane.showInputDialog("Ingrese tipo de la Sesion (taller/charla/panel): ");
       List<Asistente>  asistentes = new ArrayList<>();

       Sesion sesion = new Sesion(id,nombre,tipoSesion,asistentes);
       listaSesiones.add(sesion);
       JOptionPane.showMessageDialog(null, "Sesion agregada con exito");
    }

    public void inscribirAsistente(){
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del Asistente");
        String cedula = JOptionPane.showInputDialog("Ingrese la cedula del Asistente");

        List<Sesion> sesiones = new ArrayList<>();

        Asistente asistente = new Asistente(nombre,cedula,sesiones);
        listaAsistentes.add(asistente);
        JOptionPane.showMessageDialog(null, "El Asistente se encuentra Inscrito");
    }


    public void registarAsistencia(){
        String cedula = JOptionPane.showInputDialog("Ingrese la cedula del Asistente");
        String idSesion = JOptionPane.showInputDialog("Ingrese el id del Sesion");

        Optional<Asistente> asistenteOpt = listaAsistentes.stream()
                .filter(a -> a.cedula().equalsIgnoreCase(cedula))
                .findFirst();

        Optional<Sesion> sesionOpt = listaSesiones.stream()
                .filter(s -> s.idSesion().equalsIgnoreCase(idSesion))
                .findFirst();

        if (asistenteOpt.isPresent() && sesionOpt.isPresent()){
            Asistente asistente = asistenteOpt.get();
            Sesion sesion = sesionOpt.get();

            if (!sesion.listaAsistentes().contains(asistente)){
                sesion.listaAsistentes().add(asistente);
                asistente.listaSesionesAsistidas().add(sesion);
                JOptionPane.showMessageDialog(null, "Asistencia registrada con exito");
            }else{
                JOptionPane.showMessageDialog(null, "El Asistente ya encuentra Registrado");
            }
        }else{
            JOptionPane.showMessageDialog(null, "El Asistente o Sesion no existe");
        }
    }

    public void generarCertificado(String cedula){
        Optional<Asistente> asistenteOpt = listaAsistentes.stream()
                .filter(a -> a.cedula().equalsIgnoreCase(cedula))
                .findFirst();

        if (asistenteOpt.isPresent()){
            Asistente asistente = asistenteOpt.get();

            if (asistente.listaSesionesAsistidas().size() >= 2){
                Certificado certificado = new Certificado("Certificado-" + asistente.cedula(), asistente.nombre(), asistente.listaSesionesAsistidas());
                listaCertificados.add(certificado);
                JOptionPane.showMessageDialog(null, "Certificado de" + asistente.nombre() + " registrada con exito");
            }else {
                JOptionPane.showMessageDialog(null, "Sesiones no completadas");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Asistente no encontrado");
        }
    }

    public void listarSesiones(){
        List<Sesion> sesiones = listaSesiones;
        if (sesiones.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay Sesiones");
        }else {
            StringBuilder mensaje = new StringBuilder("Sesiones: \n" );
            for (Sesion s : sesiones) {
                mensaje.append(s).append("\n");
            }
            JOptionPane.showMessageDialog(null, mensaje.toString());
        }
    }

    public void listarAsistentes(){
        List<Asistente> asistentes = listaAsistentes;
        if (asistentes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay Asistentes");
        }else {
            StringBuilder mensaje = new StringBuilder("Asistentes: \n" );
            for (Asistente a : asistentes) {
                mensaje.append(a).append("\n");
            }
            JOptionPane.showMessageDialog(null, mensaje.toString());
        }
    }
    public void listarCertificados(){
        List<Certificado> certificados = listaCertificados;
        if (certificados.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay Certificados");
        }else {
            StringBuilder mensaje = new StringBuilder("Certificados: \n" );
            for (Certificado c : certificados) {
                mensaje.append(c).append("\n");
            }
            JOptionPane.showMessageDialog(null, mensaje.toString());
        }
    }

    public void guardarInscripciones() {

        File archivo = new File("inscripciones.txt");
        if (!archivo.exists()) {
            JOptionPane.showMessageDialog(null, "El Archivo no existe");
            return;
        }

        try (FileWriter escritor = new FileWriter(archivo, true)) {

            for (Sesion sesion : listaSesiones) {
                escritor.write(sesion.toString());
            }
            JOptionPane.showMessageDialog(null, "Archivo completado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al escribir el Archivo: " + e.getMessage());
        }
    }

    public void cargarInscripciones() {
        File archivo = new File("inscripciones.txt");

        if (!archivo.exists()) {
            JOptionPane.showMessageDialog(null, "El Archivo no existe");
            return;
        }

        try {
            StringBuilder contenido = new StringBuilder();
            try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = lector.readLine()) != null) {
                    contenido.append(linea).append("\n").append("\n");
                }
            }
            JOptionPane.showMessageDialog(null, "Contenido del Archivo: \n" + contenido.toString());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al leer el archivo" + e.getMessage());
        }
    }
}
