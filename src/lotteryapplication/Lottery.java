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
    private int[][] userLines = new int[3][6];
    int[] lineCounter = new int[3];
    int[] winningCounter = new int[3];
    private Scanner input = new Scanner(System.in);
    
    
    public Lottery() {}
    
    public void run() {
        generateLotteryNumbers();
        getUserNumbers();
        checkNumbers();
        getWinnings();
        printNumbers();
        printResults();
    }
    
    private void getWinnings() {
        for(int i = 0; i < lineCounter.length; i++){
            if(lineCounter[i] == 6) {
                winningCounter[i] = 4000000;
            } else if(lineCounter[i] == 5 ) {
                winningCounter[i] = 25000;
            } else if(lineCounter[i] == 4) {
                winningCounter[i] = 250;
            } else if(lineCounter[i] == 3) {
                winningCounter[i] = 50;
            } else {
                winningCounter[i] = 0;
            }
        }
    }
    
    // Prints the results and winnings 
    private void printResults() {
        int totalWinnings = 0;
        for(int i = 0; i < lineCounter.length; i++){
            System.out.println("You guessed " + lineCounter[i] + " numbers on line " + (i + 1) + ",");
            totalWinnings += winningCounter[i];
        }
        if(totalWinnings == 0) {
            System.out.println("Sorry you didn't win anything AT ALL loser!!!");
        } else {
            System.out.println("Your total winnings are €" + totalWinnings + ". Go on Holidays!!!");
        }
    }
    
    // Method that creates the lottery numbers
    private void generateLotteryNumbers() {
        // Create new random object
        Random randomNum = new Random();
        // Loop through array and add new random number to each
        for(int i = 0; i < lotteryNumbers.length; i++) {
            lotteryNumbers[i] = randomNum.nextInt(46) + 1;
        }
    }
    
    // Method that will check if numbers are the same
    private void checkNumbers() {
        // Go through each line
        for(int line = 0; line < userLines.length; line++) {
            // Go through each number
            for(int number = 0; number < userLines[line].length; number++) {
                // checks if number is already in the lottery numbers array and increments the line counter array with a number of correct selections so that winnings can be calculated
                if(checkIfNumberAlready(lotteryNumbers, userLines[line][number])) {
                    lineCounter[line]++;
                }
            }
        }
    }
    
    // Method to print the lottery numbers
    public void printNumbers() {
        System.out.println("And the winning numbers are...");
        for(int i = 0; i < lotteryNumbers.length; i++) {
            System.out.println(lotteryNumbers[i]);
        }
    }

    // Method to get users selections 
    private void getUserNumbers() {
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
                    // Checks if user has already selected this number
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
    
    // method to check if selected number is in correct range
    // returns TRUE if number is within range
    private boolean checkRange(int num) {
        return num > 0 && num <= 47;
    }
    
    // Method to check if a certain number is in an array
    // Returns TRUE if number is in array
    private boolean checkIfNumberAlready(int[] array, int num) {
        for(int i = 0; i < array.length; i++) {
            if(array[i] == num) {
                //System.out.println(array[i] + " == " + num);
                return true;
            }
        }
        return false;
    }
    
}