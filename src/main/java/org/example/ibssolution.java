package org.example;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.sql.SQLOutput;

public class ibssolution {

    public static void main(String[] args) {
        // Initialize the StringBuilder with "0"
        StringBuilder s = new StringBuilder(" ");

        // Perform 4 iterations
        for (int i = 0; i < 4; i++) {  // Loop runs for 4 iterations
            // Check if the last character is '0' or '1' and append accordingly
            if (i==0){
                s.append("0");
                System.out.println("o --->"+s);
            }else if (i == 1){
                s.append("1");
                System.out.println("1 --->"+s);
            }
            else if (s.charAt(s.length() - 1) == '0') {
                s.append("01");
                System.out.println(i +" --->"+s);
                // If last character is '0', append "01"
            } else {
                s.append("10") ;// If last character is '1', append "10"
                System.out.println(i +" --->"+s);
            }
        }

        // Output the final string after all iterations
        System.out.println("String is: " + s);

        // Output the character at index 4
        int index = 4;
        System.out.println("Character at index " + index + " is: " + s.charAt(index));

        // End of the test
        System.out.println("Test end");


    }

}
