package org.example;

public class selection {

    public static void main(String[] args){

        int [] a ={1,2,4,3,5,6,7};

        for(int i =0; i <a.length -1;i++){
            int smallest = i;
           for (int j=i+1; j <a.length ;j++) {

               if (a[smallest] > a[j]){
                   smallest = j;
                }
           }
           int temp = a[smallest];
           a[smallest] = a[i];
           a[i] = temp;
        }
        for(int n:a)
        System.out.print(n+" ");
    }
}
