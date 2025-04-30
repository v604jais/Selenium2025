package org.example;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class testJava {


    public static void main(String[] args) {

        //recursion
        recExamplePrintNum(5);
        //fibonaaci  0,1,1,2,3,5,8
        //fibonacci(7);
        for (int i = 0; i < 7; i++) {
            System.out.print(fibrecursion(i) + " ");
        }

        Date d = new Date();
        LocalDate ld = d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("DD-MM-YY");
         String dfr = df.format(ld);
        //epoch
        System.out.println(d.getTime());
        System.out.println(dfr);

        System.out.println(d);
        System.out.println();
        //remove duplicate from array without changing the order
        int [] a ={5,6,7,3,3,7,5};
        //Arrays.stream(a).boxed().sorted().forEach(n-> System.out.println(n));
        System.out.println("non duplicate");
        new LinkedHashSet<Integer>(Arrays.stream(a).boxed().toList())
        .stream().forEach(n-> System.out.print(n+" "));
        System.out.println(" end of non duplicate");

        // Create a new stack
        Stack<Integer> s = new Stack<>();

        // Push elements onto the stack
        s.push(1);
        s.push(1);
        s.push(3);
        s.push(4);

        // Pop elements from the stack
        while(!s.isEmpty()) {
            System.out.println(s.pop());
        }

    }

    public static void recExamplePrintNum(int num) {
        if (num == 0)
            return;
        System.out.println(num);
        recExamplePrintNum(num - 1);
    }

    public static void fibonacci(int number) {
        int num1 = 0, num2 = 1;
        for (int i = 0; i < number; i++) {
            System.out.print(num1 + " ");
            int next_num = num1 + num2;
            num1 = num2;
            num2 = next_num;
        }
    }

    public static int fibrecursion(int n){
        if (n <= 1)
            return n;
        return fibrecursion(n - 1) + fibrecursion(n - 2);
    }
}