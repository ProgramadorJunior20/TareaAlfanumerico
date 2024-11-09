package co.jmurillo.tarea;

public class Alfanumerico implements Runnable{
    private final Tipo tipo;
    private static final int PAUSA_MS = 500; // Pausa de medio segundo

    public Alfanumerico(Tipo tipo) {
        this.tipo = tipo;
    }

    @Override
    public void run() {
        String nombreHilo = Thread.currentThread().getName();
        System.out.println("Iniciando" + nombreHilo);

        try {
            switch (tipo) {
                case NUMERO -> {
                    for (int i = 0; i < 10; i++){
                        System.out.println("Número: " + i);
                        Thread.sleep(PAUSA_MS);
                    }
                }
                case LETRA -> {
                    for (char letra = 'A'; letra <= 'Z'; letra++){
                        System.out.println("Letra: " + letra);
                        Thread.sleep(PAUSA_MS);
                    }
                }
            }
        }  catch (InterruptedException e) {
            System.out.println(nombreHilo + "Fue Interrumpido");
            return;
        }

        System.out.println("Finalizando " + nombreHilo);

    }
}
