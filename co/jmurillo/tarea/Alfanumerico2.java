package co.jmurillo.tarea;

import java.util.concurrent.atomic.AtomicBoolean;

public class Alfanumerico2 implements Runnable{
    private final Tipo tipo;
    private final AtomicBoolean ejecutando;
    private static final int PAUSA_MS = 500; // Pausa de medio segundo

    // Contador compartido para sincronización entre hilos
    private static volatile int contadorCompartido = 0;
    private static final Object LOCK = new Object();

    public Alfanumerico2(Tipo tipo) {
        this.tipo = tipo;
        this.ejecutando = new AtomicBoolean(true);
    }

    public void detener(){
        ejecutando.set(false);
    }

    @Override
    public void run() {
        String nombreHilo = Thread.currentThread().getName();
        System.out.println("🚀 Iniciando " + nombreHilo);

        try {
            switch (tipo) {
                case NUMERO -> {
                    procesarNumeros(nombreHilo);
                }
                case LETRA -> {
                    procesarLetras(nombreHilo);
                }
            }
        }  catch (InterruptedException e) {
            System.out.println("⚠️ " + nombreHilo + " fue interrumpido");
            Thread.currentThread().interrupt();
        } catch (Exception e){
            System.out.println("❌ Error en " + nombreHilo + ": " + e.getMessage());
        }

        System.out.println("✅ Finalizando " + nombreHilo);
    }

    private void procesarNumeros(String nombreHilo) throws InterruptedException {
        for (int i = 0; i < 10 && ejecutando.get(); i++){
            synchronized (LOCK){
                contadorCompartido++;
                System.out.printf("🔢 %s -> Número: %d (Total procesado: %d)%n",
                        nombreHilo, i, contadorCompartido);
            }
            Thread.sleep(PAUSA_MS);
        }
    }

    private void procesarLetras(String nombreHilo) throws InterruptedException {
        for (char letra = 'A'; letra <= 'Z' && ejecutando.get(); letra++){
            synchronized (LOCK) {
                contadorCompartido++;
                System.out.printf("📝 %s -> Letra: %c (Total procesado: %d)%n",
                        nombreHilo, letra, contadorCompartido);
            }
            Thread.sleep(PAUSA_MS);
        }
    }
}
