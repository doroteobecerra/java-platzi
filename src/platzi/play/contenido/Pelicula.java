package platzi.play.contenido;

import java.time.LocalDate;

public class Pelicula {
    public String titulo;
    public String descripcion;
    public int duracion;
    public String genero;
    public LocalDate fechaExtreno;
    public double calificacion;
    public boolean disponible;

    public void reproducir(){
        System.out.println("reproduciendo " + titulo);
    }

    public String obtenerLaFichaTecnica(){
        return titulo + " (" + fechaExtreno.getYear() + " )\n" + "Genero: " + genero + "\n"+ "Calificacion: " + calificacion + "/5";
    }

    public void calificar(double calificacion) {
        if (calificacion >= 0 && calificacion <= 5){
            this.calificacion = calificacion;
        }
    }

    public boolean esPopular() {
        return calificacion >= 4;
    }
}
