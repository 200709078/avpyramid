// made by mADEMatik
import java.io.*;
import java.util.Scanner;

public class avPyramid {
    public static int len;
    public static int[][] matrix;

    public static void main(String[] args) throws IOException {

        matrix = readMatrix();
//        printMatrix();
//        System.out.println();

        matrix = deletePrimes();
//        printMatrix();
//        System.out.println();

        int maxvalue = findWaySum();
//        printMatrix();
//        System.out.println();

        System.out.println("Most expensive way = " + maxvalue);
        System.out.println("made by mADEMatik");

    }

    private static int findWaySum() {

        for (int i = 1; i < len; i++) {
            if (matrix[i][0] != -1 && matrix[i - 1][0] != -1) {
                matrix[i][0] += matrix[i - 1][0];
            }
            for (int j = 1; j < len; j++) {
                if (matrix[i][j] != -1) {
                    matrix[i][j] += Math.max(matrix[i - 1][j - 1], matrix[i - 1][j]);
                }
            }
        }
        int maxvalue = 0;
        for (int j = 0; j < len; j++) {
            if (matrix[len - 1][j] > maxvalue) {
                maxvalue = matrix[len - 1][j];
            }
        }
        return maxvalue;
    }

    private static boolean isPrime(int n) {
        if (n == 1) return false;
        for (int i = (int) Math.sqrt(n); i > 1; i--) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    private static int[][] readMatrix() throws IOException {

        File file = new File("src/pyramid.txt");
        Scanner sc = new Scanner(file);
        len = 0;
        while (sc.hasNextLine()) {
            len++;
            sc.nextLine();
        }

        matrix = new int[len][len];
        Scanner scr = new Scanner(file);
        for (int i = 0; i < len; i++) {
            for (int j = 0; j <= i; j++) {
                matrix[i][j] = scr.nextInt();
            }
        }

        return matrix;
    }

    private static int[][] deletePrimes() {

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j <= i; j++) {
                if (isPrime(matrix[i][j])) {
                    matrix[i][j] = -1;
                }
            }
        }

        return matrix;
    }

    private static void printMatrix() {
        for (int i = 0; i < len; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}