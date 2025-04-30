package org.example;

import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collectors;

public class reverseString {

    public static void main(String[] args){

        String name = "MALAYALAM";
        System.out.println((name));
        System.out.println(name.toLowerCase());
        if(!name.equalsIgnoreCase(reverseString(name))){
            System.out.println("iS Not Palindrome");
        }else {
            System.out.println("iS Palindrome");
        }
        int [] a = {12,1,2,3,4,5,6,7,8,9};
        Map<Character,Integer> map = new HashMap<>();
        map.putIfAbsent('c',1);
        map.put('a',2);
        System.out.print("values in map ");
        for(Map.Entry<Character,Integer> map1 : map.entrySet()){

           System.out.print(map1.getValue()+",");
        }
        System.out.println();


        List<Integer> al= Arrays.stream(a).filter(n -> n%2 == 0).sequential().boxed().sorted().toList();
        al.stream().forEach(n -> System.out.print(n+" "));


    }

    public static String reverseString(String name){
        String empty="";
        for(int i = name.length()-1; i>= 0; i--){
            empty=empty.concat(String.valueOf(name.charAt(i)));
        }
        return empty;
    }
}
