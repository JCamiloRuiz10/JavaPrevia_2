package app;

import javax.swing.*;

public class Principal {
    public static void main(String[] args) {
        Conferencia sistema = Conferencia.getInstance();

        while(true){
            String[] opciones = {
                    "Agregar Sesion",
                    "Inscribir Asistente",
                    "Registrar Asistecia",
                    "Generar Certificado",
                    "Listar Sesiones",
                    "Listar Asistentes",
                    "Listar Certificados",
                    "Guardar Inscripcion",
                    "Cargar Inscripcion",
                    "Salir"
            };
            String seleccion = (String) JOptionPane.showInputDialog(null, "Seleccione una Opcion",
                    "Sistema de Conferencias", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

            if(seleccion == null || seleccion.equalsIgnoreCase("Salir")){
                break;
            }
            switch(seleccion){
                case "Agregar Sesion":
                    sistema.agregarSesion();
                    break;
                case "Inscribir Asistente":
                    sistema.inscribirAsistente();
                    break;
                case "Registrar Asistecia":
                    sistema.registarAsistencia();
                    break;
                case "Generar Certificado":
                    String cedula = JOptionPane.showInputDialog("Ingrese su cedula: ");
                    sistema.generarCertificado(cedula);
                    break;
                case "Listar Sesiones":
                    sistema.listarSesiones();
                    break;
                case "Listar Asistentes":
                    sistema.listarAsistentes();
                    break;
                case "Listar Certificados":
                    sistema.listarCertificados();
                    break;
                case "Guardar Inscripcion":
                    sistema.cargarInscripciones();
                    break;
                case "Cargar Inscripcion":
                    sistema.guardarInscripciones();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opcion no Valida");
            }
        }
        JOptionPane.showMessageDialog(null, "Se ha finalizado el programa");
    }
}
