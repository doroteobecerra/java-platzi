package platzi.play.plataforma;

import platzi.play.contenido.Pelicula;

import java.util.ArrayList;
import java.util.List;

public class Plataforma {
    private final String nombre;
    private List<Pelicula> contenido;

    public Plataforma (String nombre){
        this.nombre = nombre;
        this.contenido = new ArrayList<>();
    }

    public void agregar (Pelicula elemento) {
        this.contenido.add(elemento);
    }

    public String getNombre() {
        return nombre;
    }

    public List<Pelicula> getContenido() {
        return contenido;
    }
}
