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

    public Pelicula(String titulo, int duracion, String genero) {
        this.titulo = titulo;
        this.duracion = duracion;
        this.genero =  genero;
        this.fechaExtreno = LocalDate.now();
        this.disponible = true;
    }

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

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getDuracion() {
        return duracion;
    }

    public String getGenero() {
        return genero;
    }

    public LocalDate getFechaExtreno() {
        return fechaExtreno;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public void setFechaExtreno(LocalDate fechaExtreno) {
        this.fechaExtreno = fechaExtreno;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
