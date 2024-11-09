package co.jmurillo.tarea;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class AdministradorTareas {
    private final ScheduledExecutorService scheduler;
    private final List<Alfanumerico2> tareas;
    private final List<ScheduledFuture<?>> futures;


    public AdministradorTareas(int numHilos) {
        this.scheduler = Executors.newScheduledThreadPool(numHilos);
        this.tareas = new ArrayList<>();
        this.futures = new ArrayList<>();
    }

    public void programarTareaUnica(Tipo tipo, long retraso) {
        Alfanumerico2 tarea = new Alfanumerico2(tipo);
        tareas.add(tarea);

        ScheduledFuture<?> future = scheduler.schedule(
                tarea,
                retraso,
                TimeUnit.SECONDS
        );
        futures.add(future);
    }

    public void programarTareaPeriodica(Tipo tipo, long retrasoInicial, long periodo) {
        Alfanumerico2 tarea = new Alfanumerico2(tipo);
        tareas.add(tarea);

        ScheduledFuture<?> future = scheduler.scheduleAtFixedRate(
                tarea,
                retrasoInicial,
                periodo,
                TimeUnit.SECONDS
        );
        futures.add(future);
    }

    public void detenerTodas() {
        // Detener todas las tareas
        tareas.forEach(Alfanumerico2::detener);

        // Cancelar futures
        futures.forEach(future -> future.cancel(true));

        // Apagar el scheduler
        try {
          scheduler.shutdown();
          if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
              scheduler.shutdownNow();
          }
        } catch (InterruptedException e) {
            scheduler.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
