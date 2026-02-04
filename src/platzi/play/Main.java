package platzi.play;

import platzi.play.contenido.Pelicula;
import platzi.play.plataforma.Usuario;
import platzi.play.util.ScannerUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        System.out.println("hola");
//
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("cual es tu  nombre?");
//        String nombre = scanner.nextLine();
//
//        System.out.println("Hola " + nombre);

        String nombre = ScannerUtils.capturarTexto("Nombre del contenido");
        String genero = ScannerUtils.capturarTexto("Genero del contenido");
        int duracion = ScannerUtils.capturarNumero("Duracion del contenido");
        double calificacion = ScannerUtils.capturarDecimal("Calificacion");




        Pelicula pelicula = new Pelicula();
        pelicula.titulo = nombre;
        pelicula.fechaExtreno = LocalDate.of(2018,10,15);
        pelicula.genero = genero;
        pelicula.duracion = duracion;
        pelicula.calificar(calificacion);

        System.out.println(pelicula.obtenerLaFichaTecnica());

        /*// castear calificacion a int
        int calificacionInt = (int) pelicula.calificacion;
        System.out.println("calificacion int: " + calificacionInt);

        //castear String a long
        long numeroDePremios = Long.parseLong("23");
        System.out.println("Numero de premios: " + numeroDePremios);

//        System.out.println(pelicula.obtenerLaFichaTecnica());
//        System.out.println(pelicula.esPopular());

        Usuario usuario = new Usuario();
        usuario.nombre = "Alejandro";
        usuario.fechaRegistro = LocalDateTime.of(2026,1,23,8,2,0);
        usuario.ver(pelicula);*/
    }
}
