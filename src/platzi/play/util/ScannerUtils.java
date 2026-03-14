package platzi.play.util;

import platzi.play.contenido.Genero;

import java.util.Locale;
import java.util.Scanner;

public class ScannerUtils {

    public static final Scanner SCANNER = new Scanner(System.in);

    public static String capturarTexto(String mensaje) {
        System.out.println(mensaje + ": ");
        return SCANNER.nextLine();
    }

    public static int capturarNumero(String mensaje){
        System.out.println(mensaje + ": ");
        while(!SCANNER.hasNext()){
            System.out.println("Dato no aceptado " +  mensaje + ": ");
            SCANNER.next();
        }
        int data = SCANNER.nextInt();
        SCANNER.nextLine();
        return data;
    }

    public static double capturarDecimal(String mensaje){
        System.out.println(mensaje + ": ");
        while(!SCANNER.hasNextDouble()){
            System.out.println("Dato no aceptado " +  mensaje + ": ");
            SCANNER.next();
        }
        double data = SCANNER.nextDouble();
        SCANNER.nextLine();
        return data;
    }

    public static Genero capturarGenero (String mensaje){
        while (true){
            System.out.println(mensaje + "...Opciones: ");
            for (Genero genero : Genero.values()){
                System.out.println("-" + genero.name());
            }
            System.out.println("Cual quieres?");
            String entrada = SCANNER.nextLine();

            try{
                return Genero.valueOf(entrada.toUpperCase());
            }catch (IllegalArgumentException e){
                System.out.println("Genero no aceptado.");
            }
        }
    }
}
