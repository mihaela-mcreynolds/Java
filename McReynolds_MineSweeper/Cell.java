
/**
 * Individual Minesweeper cell
 * Mihaela McReynolds
 * @version 7.0.3
 */
public class Cell
{
    private boolean mine;         //true if a mine is here
    private int neighboringMines; //how many of our neighbors are mines
    //a list of values for the cell state
    private enum CellState {HIDDEN, EXPLODED, MARKED, DISARMED, VISIBLE}
    private CellState state;   

    /**
     * Constructor for objects of class Cell
     */
    public Cell()
    {
        state = CellState.HIDDEN;
        neighboringMines = 0;
        mine = false;
    }
    
    /**
     * Set whether or not this is a mine
     */
    public void setMine(boolean mine) 
    {
        this.mine = mine;
    }
    
    /**
     * Return true if we are a mine.
     */
    public boolean isMine()
    {
        return mine;
    }
    
    /**
     * Set neighbors.
     */
    public void setNeighbors(int neighboringMines) 
    {
        this.neighboringMines = neighboringMines;
    }
    
    /**
     * Display the cell
     */
    public void print()
    {
        switch(state) 
        {
            case HIDDEN:
                System.out.print(".");
                break;
            
            case EXPLODED:
                System.out.print("*");
                break;
            
            case MARKED:
                System.out.print("X");
                break;
                
            case DISARMED:
                System.out.print("#");
                break;
            
            case VISIBLE:
                if(neighboringMines == 0) {
                    System.out.print(" ");
                    isRevealed();
                } else {
                    System.out.print(neighboringMines); // print number of neighboring mines
                }
                break;
                
        }
    }
    
    /**(
     * Mark the cell as maybe having a mine.  (User marks it)
     */
    public void mark()
    {
        state = CellState.MARKED;
    }    
    
    /**
     * Reveal a square after it is selected.
     */
    public void reveal(int row, int col){        
        if(isMine()) {
            state = CellState.EXPLODED;
        }   
        else {
            state = CellState.VISIBLE;
            flood(row, col);
        }
    }
    
    public boolean isRevealed(){

        return true;
    }
    
    public void flood(int row, int col){
        if (row> 0 && col>0 && row<9 && col <'I' && neighboringMines == 0){
            if(!isRevealed()){
                flood(row - 1, col - 1);
                flood(row - 1, col);
                flood(row - 1, col + 1);
                flood(row, col - 1);
                flood(row, col + 1);
                flood(row + 1, col - 1);
                flood(row + 1, col);
                flood(row + 1, col + 1);                
            }
            else if (!isMine() && neighboringMines > 0){
                return;
            }     
           
    }
}  
    public void revealAll(){
        if(isMine()){
            state = CellState.EXPLODED;
        }
        else {
            state = CellState.VISIBLE;
        }
    }
    
    /**
     * Detonate the cell if it is a mine. Or mark as disarmed.
     */
    public void detonate()
    {
        if(!isMine()) {
            state = CellState.VISIBLE;
        }
        else if(state == CellState.MARKED) {
            state = CellState.DISARMED;
        } 
        else {
            state = CellState.EXPLODED; //BOOM
        }
    }
}
