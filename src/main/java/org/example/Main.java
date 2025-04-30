package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    protected static int size;
    protected static int [] array ;
    protected static int index =-1;


    public static void main(String[] args) throws IOException {
        //Scanner read = new Scanner(System.in);
        BufferedReader readInputFromConsole = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(" Enter the size of an Array ");
        size = Integer.parseInt (readInputFromConsole.readLine());


        System.out.println(" Enter " + size + " Elements in Array" );
        array= new int[size];
        //initialize the array
        for(int i=0; i <array.length ; i++){
            array[i] = Integer.parseInt(readInputFromConsole.readLine());
        }
        System.out.println(" Enter an element from array for which you want its index ");

        int numberToSearch = Integer.parseInt(readInputFromConsole.readLine());

        int position = new Main().returnIndexIfElementIsPresent(numberToSearch);
        System.out.println(" Element found at  index : " +position);

    }
    public int returnIndexIfElementIsPresent(int num){
        for(int j =0; j < array.length ; j++) {
            //linear search
            if (array[j] == num) {
                index = j;
                break;
            }else{
                System.out.println("Element is not present in Array");
                break;
            }

        }
        return index;
    }
}