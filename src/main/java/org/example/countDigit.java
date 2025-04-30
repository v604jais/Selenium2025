package org.example;

import java.util.HashMap;
import java.util.Map;

public class countDigit {

    public static void main(String[] args) {

        String number = "8999006228";
        Map<Character,Integer> digitCount = new HashMap<>();
        for(int i =0 ; i < number.length() ; i++){

            if(!digitCount.containsKey(number.charAt(i))){
                digitCount.put(number.charAt(i), 1);
            }else {
                int count = digitCount.get(number.charAt(i));

                digitCount.put(number.charAt(i),++count);
            }
        }

        for(Map.Entry<Character, Integer> m : digitCount.entrySet()){

            System.out.print(m.getKey() +" came "+m.getValue() + " times, ");
        }
    }
}
