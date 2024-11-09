package co.jmurillo.tarea.main;

import co.jmurillo.tarea.AdministradorTareas;
import co.jmurillo.tarea.Tipo;

public class EjemploAlfanumerico2 {
    public static void main(String[] args) {
        AdministradorTareas admin = new AdministradorTareas(2);

        try {
            // Programar tarea única de números
            admin.programarTareaUnica(Tipo.NUMERO, 2);

            // Programar tarea periódica de letras
            admin.programarTareaPeriodica(Tipo.LETRA, 0, 3);

            // Ejecutar por 20 segundos
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            System.out.println("Programa interrumpido");
        } finally {
            admin.detenerTodas();
        }
    }
}
