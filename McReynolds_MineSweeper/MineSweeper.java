import java.util.Scanner;
/**
 * An implementation of the minesweeper game.
 *
 * @author Mihaela McReynolds
 * @version 1.0.2.1.1
 */
public class MineSweeper
{
    private Cell [][] grid;  // The minesweeper grid
    private boolean gameover; // Is true if the game is over    
    /**
     * Constructor for objects of class MineSweeper
     */
    public MineSweeper()
    {
        double prob;
        int mines=0;
        
        // Instantiate the grid
        grid = new Cell[10][10];  //10 rows x 10 columns
        
        // Get a random probability under 60%
        //prob = (Math.random() * 60.0) / 100.0;
        
        prob = 0.2;
        // Populate the grid
        for(int row=0; row<grid.length; row++) { //row loop
            for(int col=0; col<grid[row].length; col++) { //column loop
                // Instantiate the cell
                grid[row][col] = new Cell();
                
                // Set the mine property
                if(Math.random() <= prob) {
                    grid[row][col].setMine(true);
                    mines++;
                }
            }
        }
        
        // Count neighbors for each square
        for(int row=0; row<grid.length; row++) {
            for(int col=0; col<grid[row].length; col++) {
                grid[row][col].setNeighbors(countNeighboringMines(row, col));
            }
        }
        
        System.out.printf("There are %d mines.  Good Luck!\n", mines);
    }  
    
    /**
     * Count the number of neighbors which are mines 
     * @return Number of neighboring mines
     */
    public int countNeighboringMines(int row, int col) {
        
        // Check all 8 neighbors of the cell for mines; keep count of neighboring mines
        int neighboringMines = 0;
            if(row>0 && col>0 && grid[row-1][col-1].isMine()) {
                neighboringMines++;           
            }
            if(row>0 && grid[row-1][col].isMine()){
                neighboringMines++;
            }
            if(row >1 && col < grid[row].length-1 && grid[row-1][col+1].isMine()){
                neighboringMines++;
            }
            if(col>0 && grid[row][col-1].isMine()){
                neighboringMines++;
            }
            if(col < grid[row].length-1&& grid[row][col+1].isMine()){
                neighboringMines++;
            }
            if(row<grid.length-1 && col>0 && grid[row+1][col-1].isMine()){
                neighboringMines++;
            }
            if(row<grid.length-1 && grid[row+1][col].isMine()){
                neighboringMines++;
            }
            if(row<grid.length-1 && col < grid[row].length-1 && grid[row+1][col+1].isMine()){
                neighboringMines++;
            }
       
        return neighboringMines;
    }
    
     /**
     * Print the Grid
     */
    public void printGrid()
    {
        //print the header
        System.out.println();
        System.out.print(" ");
        for(char letter='A'; letter < 'A'+grid[0].length; letter++) {
            System.out.print(letter);
        }
        System.out.println();
        
        //print the rows
        for(int row=0; row<grid.length; row++) {
            //numeric header
            System.out.print(row);
            
            //print cells
            for(int col=0; col<grid[row].length; col++) {
                grid[row][col].print();
            }
            
            //end rows
            System.out.println();
        }
    }
    
    /**
     * Allow the user to make a move
     */
    public void userMove()
    {
        Scanner input = new Scanner(System.in);
        char letter='A';
        int row, col;
        int choice;
                       
        //get the user's input
        System.out.print("Select a cell: ");
        try {
            letter = (char) System.in.read();
        } catch(Exception ex) {
            //ignore all problems
        }
        row = input.nextInt();
        
        //compute the column
        col = letter - 'A';
        
        //get their action
        System.out.println("1.) Mark");
        System.out.println("2.) Reveal");
        System.out.print("Your choice? ");
        choice = input.nextInt();
       
        if(choice == 1) {
            grid[row][col].mark(); // Mark X over the cell
        } else {
            grid[row][col].reveal(row, col); // Reveal method shows whether cell is a mine or a number value for neighboring mines
            if(grid[row][col].isMine()) { // If it is a mine, boolean gameover becomes true
                gameover = true; // End game loop
            }
        }
    }
    
    /** 
     * Print the final grid, all cells visible, when user hits a mine
     */
    public void printFinalGrid(){
    
        // Print rows
            for(int row=0; row<grid.length; row++) {
            //print columns
            for(int col=0; col<grid[row].length; col++) {
                grid[row][col].revealAll(); // Reveal all cells
            }
       
        }
    }
    
    /**
     * Game Loop
     */
    public void playGame(){
        while(!gameover) {
            printGrid();
            userMove();
        }
        
        printFinalGrid();
        printGrid();
   
        System.out.println("BOOOM!!!");
        System.out.println("Game Over");
    }
    
    /** 
     * The main function
     */
    public static void main(String [] args) {
    
        MineSweeper game = new MineSweeper();
        
        game.playGame();
    }    
}
