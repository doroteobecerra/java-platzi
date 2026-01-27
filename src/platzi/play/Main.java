package platzi.play;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("hola");

        Scanner scanner = new Scanner(System.in);
        System.out.println("cual es tu  nombre?");
        String nombre = scanner.nextLine();

        System.out.println("Hola " + nombre);
    }


}
