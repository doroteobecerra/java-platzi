package platzi.play;

import platzi.play.contenido.*;
import platzi.play.excepcion.PeliculaExistenteException;
import platzi.play.plataforma.Plataforma;
import platzi.play.util.FileUtils;
import platzi.play.util.ScannerUtils;

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
    public static final int BUSCAR_POR_TIPO = 9;
    public static final int ELIMINAR = 10;
    public static final int SALIR = 11;

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
                    9. Buscar por tipo
                    10. Eliminar
                    11. Salir
                    """);

            switch (opcionElegida) {
                case AGREGAR -> {
                    int tipoDeContenido = ScannerUtils.capturarNumero("Que tipo de contenido quieres agregar? 1. Película\n2. Documental");
                    String nombre = ScannerUtils.capturarTexto("Nombre del contenido");
                    Genero genero = ScannerUtils.capturarGenero("Genero del contenido");
                    int duracion = ScannerUtils.capturarNumero("Duracion del contenido");
                    double calificacion = ScannerUtils.capturarDecimal("Calificación del contenido");

                    try{
                        if (tipoDeContenido == 1){
                            plataforma.agregar(new Pelicula(nombre, duracion, genero, calificacion));
                        }else {
                            String narrador = ScannerUtils.capturarTexto("Narrador del documental");
                            plataforma.agregar(new Documental(nombre, duracion, genero, calificacion, narrador));
                        }
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
                    Contenido contenido = plataforma.buscarPelicula(nombreBuscado);
                    if( contenido != null){
                        System.out.println(contenido.obtenerFichaTecnica());
                    }else {
                        System.out.println(nombreBuscado + " no existe en " + plataforma.getNombre());
                    }
                }
                case BUSCAR_POR_GENERO -> {
                    Genero generoBuscado = ScannerUtils.capturarGenero("Genero del contenido a buscar :");

                    List<Contenido> contenidoPorGenero = plataforma.buscarPorGenero(generoBuscado);
                    System.out.println(contenidoPorGenero.size() + " encontrados para el genero " + generoBuscado);
                    contenidoPorGenero.forEach(contenido -> System.out.println(contenido.obtenerFichaTecnica()));
                }
                case VER_POPULARES -> {
                    List<Contenido> contenidosPopulares = plataforma.getPopulares();
                    contenidosPopulares.forEach(contenido -> System.out.println(contenido.obtenerFichaTecnica() + "\n"));
                }
                case MEJOR_CALIFICADAS -> {
                    List<Contenido> mejorCalificados = plataforma.getMejorCalificadas();
                    mejorCalificados.forEach(contenido -> System.out.println(contenido.obtenerFichaTecnica() + "\n"));
                }
                case PELICULA_LARGA -> {
                    System.out.println(plataforma.getPeliculaMasLarga().obtenerFichaTecnica());
                }
                case REPRODUCIR -> {
                    String nombre = ScannerUtils.capturarTexto("Nombre del contenido a reproducir");
                    Contenido contenido = plataforma.buscarPelicula(nombre);
                    if(contenido != null){
                        plataforma.reproducir(contenido);
                    }else {
                        System.out.println("La película no existe en la plataforma");
                    }
                }
                case BUSCAR_POR_TIPO -> {
                    int tipoDeContenido = ScannerUtils.capturarNumero("Que tipo de contenido quieres agregar?\n 1. Pelicula\n2. Documental");

                    if (tipoDeContenido == 1) {
                        List<Pelicula> peliculas = plataforma.getPeliculas();
                        peliculas.forEach(pelicula -> System.out.println(pelicula.obtenerFichaTecnica() + "\n"));
                    } else {
                        List<Documental> documentales = plataforma.getDocumentales();
                        documentales.forEach(documental -> System.out.println(documental.obtenerFichaTecnica() + "\n"));
                    }
                }
                case ELIMINAR -> {
                    String nombreEliminar = ScannerUtils.capturarTexto("Titulo a eliminar: ");
                    Contenido contenido = plataforma.buscarPelicula(nombreEliminar);
                    if(contenido != null){
                        plataforma.eliminar(contenido);
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
