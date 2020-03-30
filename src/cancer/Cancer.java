/*
 * Peter Horne-Deus
 * This program searches for cancer withing a inputed grid
 * Cancer.java
 * March 30, 2020
 */
package cancer;

import java.io.*;
/**
 *
 * @author Peter
 */
public class Cancer {
    
    //Public Variables
    public static String[][] grid= new String[15][15];
    public static int start = 0;
    public static int cancerCells = 0;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Creating file variables
        File dataFile = new File("cancer.txt");
        FileReader in;
        BufferedReader readFile;
        //Variables
        String temp = "";
        int row = 0;
        int coloum = 0;
        
        try{
            //Setting files to variables
            in = new FileReader(dataFile);
            readFile = new BufferedReader(in);
            
            //Inputting the initial grid from the text file
            for(int i = 0; i < 15; i++){
                row = i;
                temp = readFile.readLine();
                for(int k = 0; k < 15; k++){
                    coloum = k; 
                    grid[row][coloum] = Character.toString(temp.charAt(k));
                    //Outputing the grid to the user
                    System.out.print(grid[row][coloum]);
                }
                System.out.println("");
            }
            
            //Setting up the searching algorithm
            row = 0;
            coloum = 0;
            cancerSearch(row,coloum);
            
            //Output the new updated grid
            System.out.println("\n\n");
            System.out.println("There were "+ cancerCells+ " number of cancer cells");
            for(int i = 0; i < 15; i++){
                row = i;
                for(int k = 0; k < 15; k++){
                    coloum = k; 
                    System.out.print(grid[row][coloum]);
                }
                System.out.println("");
            }

        }
        
        //Catching errors
        catch (FileNotFoundException e) {
            System.out.println("File does not exist or could not be found.");
            System.err.println("FileNotFoundException: " + e.getMessage());
        } 
        catch (IOException e) {
            System.out.println("Problem reading file.");
            System.err.println("IOException: " + e.getMessage());
    	}
    }
    
    /**
     * This method searches for cancerous cells
     * @param row
     * @param coloum 
     */
    public static void cancerSearch(int row, int coloum){
        
        //Searching for a random point on the grid
        if(start == 0){
            start = 1;
            row = (int)(Math.random()* 13 + 1);
            coloum = (int)(Math.random()* 13 + 1);
        }
        //Checking if given point if cancerous
        if(grid[row][coloum].equals("-")){
            //Clears cancer
            grid[row][coloum] = " ";
            
            //Searches all 8 around the point for cancer
            cancerSearch(row + 1,coloum);
            cancerSearch(row - 1,coloum);
            cancerSearch(row,coloum + 1);
            cancerSearch(row,coloum - 1);
            cancerSearch(row + 1,coloum + 1);
            cancerSearch(row + 1,coloum - 1);
            cancerSearch(row - 1,coloum + 1);
            cancerSearch(row - 1,coloum - 1);
            
            //Adds values to the cancer cell counter
            cancerCells ++;
        }
        //Checking if the cell if healthy
        else if (grid[row][coloum].equals("+")){
            //Setting up to search for more cancerous cells
            start = 0;
            cancerSearch(row,coloum);
            
        }
                
        
           
        
        
            
    }
    
}
