package co.jmurillo.tarea.main;

import co.jmurillo.tarea.Alfanumerico;
import co.jmurillo.tarea.Tipo;

public class EjemploAlfanumerico {
    public static void main(String[] args) {

        // Creamos dos tareas de diferentes tipos
        Runnable tareaNumeros = new Alfanumerico(Tipo.NUMERO);
        Runnable tareaLetras = new Alfanumerico(Tipo.LETRA);

        // Creamos los hilos
        Thread hiloNumeros = new Thread(tareaNumeros, "hilo-Números");
        Thread hiloLetras = new Thread(tareaLetras, "hilo-Letras");

        // Iniciemos los hilos
        hiloNumeros.start();
        hiloLetras.start();
    }
}
