package org.example;

import java.util.Arrays;
import java.util.List;

public class bubbleSort {


    public static void main(String[] args){


        List<Integer> input1 = Arrays.asList(1,2,3,4,5);


        List <Integer> even =input1.stream().sorted().filter(n -> n % 2 == 0).toList();
        int product_sum = input1.stream()
                        .map(n -> n*n).reduce(0,Integer :: sum);

        System.out.println(even+" :: "+product_sum);


        int[] a = {2,3,1,4,5};
        int tmp;
       // outer loop pass
        for (int i = 0; i < a.length - 1; i++) {
         // inner loop for compare and swap
         for(int j =0 ; j < a.length -1 - i; j++){
             //swap
             if(a[j] > a[j+1]){
                 tmp    = a[j];
                 a[j]   = a[j+1];
                 a[j+1] = tmp;
             }

         }
        }
         for( int i : a) {
             System.out.print(i + " ");
         }



    }
}
