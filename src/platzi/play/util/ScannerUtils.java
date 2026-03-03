package platzi.play.util;

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
}
