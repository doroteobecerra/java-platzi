package platzi.play.plataforma;

import platzi.play.contenido.Contenido;
import platzi.play.contenido.Genero;
import platzi.play.contenido.ResumenContenido;
import platzi.play.excepcion.PeliculaExistenteException;
import platzi.play.util.FileUtils;

import java.util.*;

public class Plataforma {
    private final String nombre;
    private List<Contenido> contenido;
    private Map<Contenido, Integer> visualizaciones;


    public Plataforma (String nombre){
        this.nombre = nombre;
        this.contenido = new ArrayList<>();
        this.visualizaciones = new HashMap<>();
    }

    public void agregar(Contenido elemento) {
        Contenido contenido = this.buscarPelicula(elemento.getTitulo());
        if(contenido != null){
            throw new PeliculaExistenteException(elemento.getTitulo());
        }
        FileUtils.escribirContenido(elemento);
        this.contenido.add(elemento);
    }

    public void reproducir(Contenido contenido){
        int conteoActual = visualizaciones.getOrDefault(contenido, 0);
        System.out.println(contenido.getTitulo() + " tiene " + conteoActual + " visualizaciones.");
        this.contarVisualizacion(contenido);
        contenido.reproducir();
    }

    private void contarVisualizacion(Contenido contenido){
        int conteoActual = visualizaciones.getOrDefault(contenido, 0);
        visualizaciones.put(contenido, conteoActual + 1);
    }

    public List<String> getTitulos() {
        return contenido.stream().map(Contenido::getTitulo).toList();
    }

    public List<ResumenContenido> getResumenes (){
        return contenido.stream().map(c -> new ResumenContenido(c.getTitulo(), c.getDuracion(), c.getGenero())).toList();
    }

    public void eliminar(Contenido elemento) {
        this.contenido.remove(elemento);
    }

    public Contenido buscarPelicula (String titulo){
        return contenido.stream().filter(contenido-> contenido.getTitulo().equalsIgnoreCase(titulo))
                .findFirst().orElse(null);
    }

    public List<Contenido> buscarPorGenero(Genero genero) {
        return contenido.stream()
                .filter(contenido -> contenido.getGenero().equals(genero))
                .toList();
    }

    public List<Contenido> getPopulares() {
        return contenido.stream().sorted(Comparator.comparingDouble(Contenido::getCalificacion).reversed()).toList();
    }

    public List<Contenido> getMejorCalificadas() {
        return  contenido.stream().filter(pelicula -> pelicula.getCalificacion() > 4).toList();
    }

    public Contenido getPeliculaMasLarga() {
        return contenido.stream().max(Comparator.comparingInt(Contenido::getDuracion)).orElse(null);
    }

    public int getDuracionTotal() {
        return contenido.stream().mapToInt(Contenido::getDuracion).sum();
    }

    public String getNombre() {
        return nombre;
    }

    public List<Contenido> getContenido() {
        return contenido;
    }
}
