package platzi.play;

import platzi.play.contenido.Genero;
import platzi.play.contenido.Pelicula;
import platzi.play.contenido.ResumenContenido;
import platzi.play.excepcion.PeliculaExistenteException;
import platzi.play.plataforma.Plataforma;
import platzi.play.util.FileUtils;
import platzi.play.util.ScannerUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static final String VERSION = "1.0.0";
    public static final String NOMBRE_VERSION = "Platzi play";
    public static final int AGREGAR = 1;
    public static final int MOSTRAR_TODO = 2;
    public static final int BUSCAR_POR_TITULO = 3;
    public static final int BUSCAR_POR_GENERO = 4;
    public static final int VER_POPULARES = 5;
    public static final int MEJOR_CALIFICADAS = 6;
    public static final int PELICULA_LARGA = 7;
    public static final int REPRODUCIR = 8;
    public static final int ELIMINAR = 9;
    public static final int SALIR = 10;

    public static void main(String[] args) {
        Plataforma plataforma = new Plataforma(NOMBRE_VERSION);
        System.out.println(NOMBRE_VERSION + " v" + VERSION);

        cargarPeliculas(plataforma);

        System.out.println("Mas de " + plataforma.getDuracionTotal() + " minutos de contenido!\n");

        while(true){
            int opcionElegida = ScannerUtils.capturarNumero("""
                    Ingrese una de las siguientes opciones:
                    1. Agregar contenido
                    2. Mostrar títulos
                    3. Buscar por titulo
                    4. Buscar por genero
                    5. Ver populares
                    6. Mejor calificadas
                    7. Película mas larga
                    8. Reproducir
                    9. Eliminar
                    10. Salir
                    """);

            switch (opcionElegida) {
                case AGREGAR -> {
                    String nombre = ScannerUtils.capturarTexto("Nombre del contenido");
                    Genero genero = ScannerUtils.capturarGenero("Genero del contenido");
                    int duracion = ScannerUtils.capturarNumero("Duracion del contenido");
                    double calificacion = ScannerUtils.capturarDecimal("Calificacion del contenido");

                    try{
                        plataforma.agregar(new Pelicula(nombre, duracion, genero, calificacion));
                    }catch (PeliculaExistenteException e){
                        System.out.println(e.getMessage());
                    }

                }
                case MOSTRAR_TODO -> {
                    List<ResumenContenido> contenidosResumidos = plataforma.getResumenes();
                    contenidosResumidos.forEach(resumen -> System.out.println(resumen.titulo()));
                }
                case BUSCAR_POR_TITULO -> {
                    String nombreBuscado = ScannerUtils.capturarTexto("Nombre del contenido a buscar: ");
                    Pelicula pelicula = plataforma.buscarPelicula(nombreBuscado);
                    if( pelicula != null){
                        System.out.println(pelicula.obtenerFichaTecnica());
                    }else {
                        System.out.println(nombreBuscado + " no existe en " + plataforma.getNombre());
                    }
                }
                case BUSCAR_POR_GENERO -> {
                    Genero generoBuscado = ScannerUtils.capturarGenero("Genero del contenido a buscar :");

                    List<Pelicula> contenidoPorGenero = plataforma.buscarPorGenero(generoBuscado);
                    System.out.println(contenidoPorGenero.size() + " encontrados para el genero " + generoBuscado);
                    contenidoPorGenero.forEach(contenido -> System.out.println(contenido.obtenerFichaTecnica()));
                }
                case VER_POPULARES -> {
                    List<Pelicula> contenidosPopulares = plataforma.getPopulares();
                    contenidosPopulares.forEach(contenido -> System.out.println(contenido.obtenerFichaTecnica() + "\n"));
                }
                case MEJOR_CALIFICADAS -> {
                    List<Pelicula> mejorCalificados = plataforma.getMejorCalificadas();
                    mejorCalificados.forEach(contenido -> System.out.println(contenido.obtenerFichaTecnica() + "\n"));
                }
                case PELICULA_LARGA -> {
                    System.out.println(plataforma.getPeliculaMasLarga().obtenerFichaTecnica());
                }
                case REPRODUCIR -> {
                    String nombre = ScannerUtils.capturarTexto("Nombre del contenido a reproducir");
                    Pelicula contenido = plataforma.buscarPelicula(nombre);
                    if(contenido != null){
                        plataforma.reproducir(contenido);
                    }else {
                        System.out.println("La película no existe en la plataforma");
                    }
                }
                case ELIMINAR -> {
                    String nombreEliminar = ScannerUtils.capturarTexto("Titulo a eliminar: ");
                    Pelicula pelicula = plataforma.buscarPelicula(nombreEliminar);
                    if(pelicula != null){
                        plataforma.eliminar(pelicula);
                        System.out.println("Película " + nombreEliminar + " eliminada");
                    }else{
                        System.out.println("No se encuentra la película");
                    }

                }
                case SALIR -> System.exit(0);
            }

        }

//
//        /*// castear calificacion a int
//        int calificacionInt = (int) pelicula.calificacion;
//        System.out.println("calificacion int: " + calificacionInt);
//
//        //castear String a long
//        long numeroDePremios = Long.parseLong("23");
//        System.out.println("Numero de premios: " + numeroDePremios);*/
//
    }

    private static void cargarPeliculas(Plataforma plataforma) {
        plataforma.getContenido().addAll(FileUtils.leerContenido());
    }
}
