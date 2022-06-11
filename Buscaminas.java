package Buscaminas;

import java.util.Scanner;
import java.util.Random;

public class Buscaminas {

    public static void main(String[] args) {
        Scanner lea = new Scanner(System.in);
        Random random = new Random();

        String pregunta;

        System.out.print("¿quieres jugar?: ");
        pregunta = lea.nextLine();

        while (pregunta.equalsIgnoreCase("si")) {

            int filas, columnas, fila, columna;
            System.out.print("Digite las filas: ");
            filas = lea.nextInt();
            System.out.print("Digite las columnas: ");
            columnas = lea.nextInt();

            while ((filas < 3 || filas >= 14) || (columnas < 3 || columnas >= 14)) {
                if (filas < 3 || filas >= 14) {
                    System.out.print("digite un rango valido de filas: ");
                    filas = lea.nextInt();
                }
                if (columnas < 3 || columnas >= 14) {
                    System.out.print("digite un rango valido de columnas: ");
                    columnas = lea.nextInt();
                }
            }
            double[][] tablero = new double[filas][columnas];
            String[][] tableroMinas = new String[filas][columnas];
            double flotante = 0.1;
            int minas = (int) (tablero.length * (tablero.length * 0.5)) - 2;
            boolean unaMina = false;
            int randomMina;
            int contador = 1;
            int intentos = tablero.length * tablero.length;

            System.out.println(minas);
            for (int i = 0; i < tableroMinas.length; i++) {
                for (int j = 0; j < tableroMinas[i].length; j++) {
                    tableroMinas[i][j] = "#";
                }
            }

            while (minas > 0) {
                for (int i = 0; i < tableroMinas.length && minas > 0; i++) {
                    unaMina = false;
                    for (int j = 0; j < tableroMinas[i].length && minas > 0; j++) {
                        randomMina = random.nextInt(tableroMinas.length);
                        if (unaMina == false) {
                            tableroMinas[i][randomMina] = "*";
                            minas--;
                        }

                        unaMina = true;

                    }
                }
            }

            minas = (int) (tablero.length * (tablero.length * 0.5)) - 2;

            for (int i = 0; i < tableroMinas.length; i++) {
                for (int j = 0; j < tableroMinas[i].length; j++) {
                    System.out.print(tableroMinas[i][j] + "  ");
                }
                System.out.println("");
            }

            for (int i = 0; i < tablero.length; i++) {
                for (int j = 0; j < tablero[i].length; j++) {
                    tablero[i][j] = (double) ((i + 1) + (j * 0.1 + 0.1));

                    if (j > 8) {
                        tablero[i][j] = (double) (i + 1 + flotante);
                        flotante += 0.01;
                    }
                    if (j == 6) {
                        tablero[0][6] = 1.7;
                    }

                }
                flotante = 0.1;

            }

            String[][] arrayString = new String[tablero.length][tablero.length];

            for (int i = 0; i < tablero.length; i++) {
                for (int j = 0; j < tablero[i].length; j++) {
                    String objeto = String.valueOf(tablero[i][j]);

                    arrayString[i][j] = objeto;
                    arrayString[i][j] = arrayString[i][j].replace(".", ",");
                }
                System.out.println("");
            }

            System.out.println("");

            System.out.println("Este buscaminas funciona apartir de filas y columnas\nes decir, señalas"
                    + " la fila y despues la columna\n");

            for (int i = 0; i < arrayString.length; i++) {

                for (int j = 0; j < arrayString[i].length; j++) {

                    if (j == 9) {
                        System.out.printf("%8s%s", arrayString[i][j], "0");
                    } else {
                        System.out.printf("%8s", arrayString[i][j]);
                    }

                }
                System.out.println("");
            }
            System.out.println("");

            while (contador < intentos) {
                System.out.print("Digite una fila del tablero: ");
                fila = lea.nextInt();
                System.out.println("");
                System.out.print("Digite una columna del tablero: ");
                columna = lea.nextInt();
                System.out.println("");

                if (tableroMinas[fila - 1][columna - 1] == "#") {

                    while (arrayString[fila - 1][columna - 1] == "#") {
                        System.out.println("Ya has digitado esa coordenada, por favor ingresa otras");
                        System.out.print("Digite una fila del tablero: ");
                        fila = lea.nextInt();
                        System.out.println("");
                        System.out.print("Digite una columna del tablero: ");
                        columna = lea.nextInt();
                        System.out.println("");
                    }
                    System.out.println("En esta casilla no hay mina puedes continuar");

                    arrayString[fila - 1][columna - 1] = "#";

                    for (int i = 0; i < arrayString.length; i++) {

                        for (int j = 0; j < arrayString[i].length; j++) {

                            if (j == 9 && arrayString[i][j] != "#") {
                                System.out.printf("%8s%s", arrayString[i][j], "0");

                            } else {
                                System.out.printf("%8s", arrayString[i][j]);
                            }

                        }
                        System.out.println("");
                    }
                } else {
                    System.out.println("Hay una mina y por lo tanto no puedes continuar");
                    arrayString[fila - 1][columna - 1] = "*";
                    for (int i = 0; i < arrayString.length; i++) {

                        for (int j = 0; j < arrayString[i].length; j++) {

                            if (j == 9 && arrayString[i][j] != "#") {
                                System.out.printf("%8s%s", arrayString[i][j], "0");

                            } else {
                                System.out.printf("%8s", arrayString[i][j]);
                            }

                        }
                        System.out.println("");
                    }

                    break;
                }

                if (contador == intentos - minas) {
                    System.out.println("GANASTE");
                    break;
                }

                contador++;

            }
            lea.nextLine();
            System.out.print("¿Quieres seguir jugando?: ");
            pregunta = lea.nextLine();
        }

    }
}
