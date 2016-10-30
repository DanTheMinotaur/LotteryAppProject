/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lotteryapplication;

/**
 *
 * @author x15004091
 */

import java.util.Random;
import java.util.Scanner;

public class Lottery {
    private int[] lotteryNumbers = new int[6];
    private int[] line1 = new int[6];
    private int[] line2 = new int[6];
    private int[] line3 = new int[6];
    private int[][] userLines = new int[3][6];
    private Scanner input = new Scanner(System.in);
    
    public Lottery(int[] l1, int[] l2, int[] l3) {
        this.line1 = l1;
        this.line2 = l2;
        this.line3 = l3;
    }
    
    public Lottery() {}
    
    private void generateLotteryNumbers() {
        Random randomNum = new Random();
        
        for(int i = 0; i < lotteryNumbers.length; i++) {
            lotteryNumbers[i] = randomNum.nextInt(46) + 1;
        }
    }
    
    public void printNumbers() {
        generateLotteryNumbers();
        for(int i = 0; i < lotteryNumbers.length; i++) {
            System.out.println(lotteryNumbers[i]);
        }
    }
    
    // method to check if selected number is in correct range
    // returns TRUE if number is within range
    private boolean checkRange(int num) {
        return num > 0 && num <= 47;
    }
    
    // Returns TRUE if number is in array
    public boolean checkIfNumberAlready(int[] array, int num) {
        for(int i = 0; i < array.length; i++) {
            if(array[i] == num) {
                //System.out.println(array[i] + " == " + num);
                return true;
            }
        }
        return false;
    }
    
    public void getUserNumbers() {
        int choice = 0;
        
        // Start looping through lines
        for(int line = 0; line < userLines.length; line++) {
            System.out.print("Enter line " + (line + 1) + " here: \n");
            // Start looping through each line
            for(int num = 0; num < userLines[line].length; num++) {
                System.out.print("Enter Number[Between 1 and 47] " + (num + 1) + ": ");
                choice = input.nextInt();
                // Check if choice is within correct range.
                if(checkRange(choice)){
                    if(!checkIfNumberAlready(userLines[line], choice)) {
                        userLines[line][num] = choice;
                    } else {
                        System.out.println("Sorry you already picked that number, Try again");
                        num -= 1;
                    }
                } else {
                    System.out.println("Sorry that is not a number between 1 and 47 try again!");
                    // Decrement counter because need to repeat the step in the loop
                    num -= 1;
                }
            }
        }
    }
}
