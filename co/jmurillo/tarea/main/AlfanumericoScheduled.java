package co.jmurillo.tarea.main;

import co.jmurillo.tarea.Alfanumerico;
import co.jmurillo.tarea.Tipo;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AlfanumericoScheduled {
    public static void main(String[] args) {
        // Creamos un ScheduledExecutorService con 2 hilos
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

        // Programamos la tarea de números para que comience después de 2 segundos
        scheduler.schedule(
                new Alfanumerico(Tipo.NUMERO),
                2,
                TimeUnit.SECONDS
        );

        // Programamos la tarea de letras para que se ejecute cada 3 segundos
        scheduler.scheduleAtFixedRate(
                new Alfanumerico(Tipo.LETRA),
                0, // inicio inmediato
                3, // repetir cada 3 segundos
                TimeUnit.SECONDS
        );

        // Cerramos el scheduler después de 20 segundos
        try {

            Thread.sleep(20000);
            scheduler.shutdown();

        } catch (InterruptedException e) {
            scheduler.shutdown();
        }
    }
}
