package org.example;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.util.Scanner;
import java.util.SortedMap;

public class Matrix {

    public static void main(String[] args) throws IOException {


        int [][] matrix = new int [3][5];

        System.out.println(" Enter elements of matrix[3][5] ");
        //input
        for(int i = 0; i <3;i++){

            for(int j=0; j<5; j++){

                matrix[i][j] = new Scanner(System.in).nextInt();
            }

        }

        System.out.print(" matrix[3][5] is  ");
        //output
        for(int i = 0; i <3;i++){

            for(int j=0; j<5; j++){

               System.out.print( matrix[i][j] );
            }
            System.out.println();
        }
        System.out.print( "Enter Element to search in matrix : " );
        int number = new Scanner(System.in).nextInt();
        //linear search
        // Linear search in a 2D matrix
        for (int i = 0; i < 3; i++) {
            boolean found = false;

            for (int j = 0; j < 5; j++) {
                if (matrix[i][j] == number) {
                    System.out.println("Found at [ " + i + " ][ " + j + " ] in matrix");
                   found = true;
                    break; // Exit inner loop after finding
                }
            }

            if (!found) {
                System.out.println(number + " not found in row " + i);
            }
        }




         String a= "123";
         int b= Integer.parseInt(a);
         System.out.println(b);
    }
}

