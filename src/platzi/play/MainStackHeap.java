package platzi.play;

import platzi.play.contenido.Pelicula;

public class MainStackHeap {
    public static void main(String[] args) {
        Pelicula reyLeon = new Pelicula("El rey leon", 135, "comedia");
        Pelicula harryPotter = new Pelicula("Harry Potter", 120, "fantasia");

        System.out.println(reyLeon.titulo);
    }
}
