/*
 * Author: Brandon Soledad
 * Project: Mozart Waltz Generator
 * Date: 4/19/2019
 * Purpose: Plays 16 measures of minuets and trios consecutively to generate a waltz.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Waltz {
    private static final int minuetROWS = 13;
    private static final int trioROWS = 7;
    private static final int COLS = 17;

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
 
    	// Creates file object to read minuet file.
        File minuetFile = new File("C:\\Users\\Brandizzy\\Downloads\\minuet.txt");
        // Creates file object to read trio file.
        File trioFile = new File("C:\\Users\\Brandizzy\\Downloads\\trio.txt"); 
       
        Waltz a = new Waltz(); // Creates an instance of the class.
        // Calls the method getMinuet takes txt file at parameter "file" and stores the contents in a 2-D array.
        int[][] minuet = a.Minuet(minuetFile); 
        // Calls the method getTrio takes txt file at parameter "file" and stores the contents in a 2-D array.
        int[][] trio = a.Trio(trioFile);
        
        int[] playMinuet = a.getMinuetMeasures(minuet); // Stores 16 measures of minuet in playMinuet

        
        int[] playTrio = a.getTrioMeasures(trio); // Stores 16 measures of trio in playTrio
       
        
        String trio_file = ""; // Creates empty string object to store trio file/measure
        String minuet_file = ""; // Creates empty string object to store minuet file/measure
        
        /*
         * Prints table of minuet measures.
         */
        System.out.println("Table of minuet measures: ");
        System.out.println();
        for(int i = 2; i < minuetROWS; i++){
        	for(int j = 1; j < COLS; j++){
        		System.out.print(minuet[i][j] + " ");
        	}
        	System.out.println();
        }
        System.out.println();
        /*
         * Prints table of trio measures.
         */
        System.out.println("Table of trio measures: ");
        System.out.println();
        for(int i = 1; i < trioROWS; i++){
        	for(int j = 1; j < COLS; j++){
        		System.out.print(trio[i][j] + " ");
        	}
        	System.out.println();
        }
        
        System.out.println();
       
        System.out.println("Minuet measures: " + Arrays.toString(playMinuet));//Prints the random minuet measures.
        System.out.println("Trio measures: " + Arrays.toString(playTrio));//Prints the random trio measures.
        
        /*
         * plays the 16 measures of playMinuet and playTrio consecutively.
         */
        for(int i = 0; i <16; i++){
        	trio_file = "T" + playTrio[i] + ".wav";
        	minuet_file = "M" + playMinuet[i] + ".wav";
        	
        	// Plays given minuet file
        	StdAudio.play(minuet_file);
        	Thread.sleep(1310);
        	// Plays given trio file
        	StdAudio.play(trio_file);
        	Thread.sleep(1310);
        }
     
    }
    /**
     * Stores contents of file 'minuet.txt' in a 2-d array
     * 
     * @param file a file object which takes the file 'minuet.txt' as a parameter
     * @return a 2-d array with the contents of minuet.txt.
     * @throws FileNotFoundException
     */
    public int[][] Minuet(File file) throws FileNotFoundException{
        Scanner sc = new Scanner(file); // Scanner object to read the file
        int[][] minuet = new int[minuetROWS][COLS];// Creates empty 13-by-17 2D array
        while(sc.hasNextInt()){ // While the file has a next integer read the file into the 2D array
            for(int i = 2; i< minuetROWS; i++){
                for(int j = 1; j < COLS; j++){
                	int x = 0;
                	if(sc.hasNextInt()){
                		x = sc.nextInt();
                	}
                    minuet[i][j] = x; // Sets the integer x into a specific index of the array
                }
            }
         }
        sc.close();// Closes the scanner object to prevent leaks
        return minuet; // returns the 2D array
    }
    /**
     * Stores contents of file 'trio.txt' in a 2-d array
     * 
     * @param file1 a file object which takes 'trio.txt' as a parameter
     * @return a 2-d array with the contents of 'trio.txt'.
     * @throws FileNotFoundException
     */
    public int[][] Trio(File file1) throws FileNotFoundException{
        Scanner sc = new Scanner(file1); // Scanner object to read the file
        int[][] trio = new int[trioROWS][COLS];// Creates empty 7-by-17 2D array
        while(sc.hasNextInt()){ // While the file has a next integer read the file into the 2D array
            for(int i = 1; i< trioROWS; i++){
                for(int j = 1; j < COLS; j++){
                	int x = 0;
                	if(sc.hasNextInt()){
                		x = sc.nextInt();
                	}
                    trio[i][j] = x; // Sets the integer x into a specific index of the array
                }
            }
         }
        sc.close();// Closes the scanner object to prevent leaks
        return trio; // returns the 2D array
    }
    /**
     * Generates a random dice roll from [1,6].
     * 
     * @return an integer with a random value from [1,6].
     */
    public int rollDice(){
    	Random rand = new Random(); // Creates random object named rand.
    	int dice = rand.nextInt(6) + 1; // Generates a random integer for dice between 1-6 . 	
    	
    	return dice; // returns dice.
    }
    /**
     * Generates a random integer from [1,16].
     * 
     * @return an integer with a random value from [1,16].
     */
    public int randomNum(){
    	Random rand = new Random(); // Creates random object named rand.
    	int num = rand.nextInt(16) + 1; // Generates random number between 1-16.
    	
    	return num; // returns num.
    	
    }
    /**
     * Generates 16 random measures of minuet given by the contents of 
     * 'minuet.txt' stored in a 2-d array
     * 
     * @param arr a 2-d array in which the elements are the contents of 'minuet.txt'
     * @return a 1-d array that contains 16 random measures of 'minuet.txt'.
     */
    public int[] getMinuetMeasures(int[][] arr){
    	int[] minuet = new int[COLS-1];
    	int x = 0;
    	for(int i = 1; i < COLS; i++){
    		minuet[x] = arr[(rollDice() + rollDice())][randomNum()];
    		if(x < COLS - 1){
    			x++;
    		}
    	}
    	return minuet;
    }
    /**
     * Generates 16 random measures of trio given by the contents of
     * 'trio.txt' stored in a 2-d array.
     * 
     * @param arr arr a 2-d array in which the elements are the contents of 'trio.txt'
     * @return a 1-d array that contains 16 random measures of 'trio.txt'.
     */
    public int[] getTrioMeasures(int[][] arr){
    	int[] trio = new int[COLS-1];
    	int x = 0;
    	for(int i = 1; i < COLS; i++){
    		trio[x] = arr[rollDice()][randomNum()];
    		if(x < COLS - 1){
    			x++;
    		}
    	}
    	return trio;
    }

}
