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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import jdk.jfr.events.FileWriteEvent;

public class Lottery {
    private int[] lotteryNumbers = new int[6];
    private int[][] userLines = new int[3][6];
    private ArrayList<int[]> lotterLines = new ArrayList<>();
    private int[] lineCounter;
    private int[] winningCounter;
    
    
    // Generate Lottery Numbers When Object is Created
    public Lottery() {
        generateLotteryNumbers();
    }

    public String runGUIImplementation() {
        //generateLotteryNumbers();
        //printNumbers()
        checkLines();
        return printResults();
        
        //return "ran";
    }
    
    // Returns String of lottery numbers
    public int[] getLotteryResults() {
        return lotteryNumbers;
    }
    
    // Set Lines
    public void setLine(int[] line) {
        this.lotterLines.add(line);
        // test
        //printArray(line);
    }
    
    private String printResults(int[] arr) {
        StringBuilder output = new StringBuilder();
        for(int i = 0; i < arr.length; i++) {
            if((arr.length - 1) == i) {
                output.append(arr[i]);
                System.out.println(i);
                System.out.println(arr.length);
            } else {
                output.append(arr[i]).append(", ");
                System.out.println(i);
                System.out.println(arr.length);
            }
        }
        return output.toString();
    }
    
    // Calculate Winnings
    private void getWinnings() {
        winningCounter = new int[lineCounter.length];
        for(int i = 0; i < lineCounter.length; i++){
            switch (lineCounter[i]) {
                case 6:
                    winningCounter[i] = 4000000;
                    break;
                case 5:
                    winningCounter[i] = 25000;
                    break;
                case 4:
                    winningCounter[i] = 250;
                    break;
                case 3:
                    winningCounter[i] = 50;
                    break;
                default:
                    winningCounter[i] = 0;
                    break;
            }
        }
    }

    /*
        Helper Methods
    
    */
    
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

    
    // Method that creates the lottery numbers
    private void generateLotteryNumbers() {
        // Create new random object
        Random randomNum = new Random();
        int curNum;
        // Loop through array and add new random number to each
        for(int i = 0; i < lotteryNumbers.length; i++) {
            curNum = randomNum.nextInt(46) + 1;
            if(!checkIfNumberAlready(lotteryNumbers, curNum)) {
                lotteryNumbers[i] = curNum;
            } else {
                i--;
            }
        }
    }
    
    // Loops through each line in the arraylist
    private void checkLines() {
        // Sets line counter array to length of lines
        lineCounter = new int[lotterLines.size()];
        
        // Count what line is currently being checked 
        int index = 0;
        
        for(int[] line : lotterLines) {
            //System.out.println(line.toString());
            checkNumber(line, index);
            index += 1;
        }
    }
    
    
    private void checkNumber(int[] line, int index) {
        for(int number = 0; number < line.length; number++) {
            if(checkIfNumberAlready(lotteryNumbers, line[number])) {
                System.out.println("You guessed correctly");
                lineCounter[index] += 1;
            }
        }
    }
            
    // Prints the results and winnings 
    private String printResults() {
        getWinnings();
        int totalWinnings = 0;
        StringBuilder result = new StringBuilder();
        result.append("The Winning Numbers are: ").append(printResults(lotteryNumbers)).append("\n");
        for(int i = 0; i < lineCounter.length; i++){
            result.append("You guessed ").append(lineCounter[i]).append(" numbers on line ").append(i + 1).append(",\n");
            totalWinnings += winningCounter[i];
        }
        if(totalWinnings == 0) {
            result.append("Sorry you didn't win anything AT ALL loser!!!");
        } else {
            result.append("Your total winnings are €").append(totalWinnings).append(". Go on Holidays!!!");
        }
        return result.toString();
    }
    
    /*
        Export Methods
    */
    
    public void exportLotteryResults() {
        try {
            File f = new File("export/lotteryData.txt");
            FileWriter fW = new FileWriter(f);
            BufferedWriter bW = new BufferedWriter(fW);
            bW.write(printResults());
            bW.close();
            System.out.println("File Written");
        } catch (FileNotFoundException e) {
            System.out.println("File wasn't found");
        } catch (IOException e) {
            System.out.println("File Reading failed");
        } catch (Exception e) {
            System.out.println("Something bad happened!");
        }
    }
}