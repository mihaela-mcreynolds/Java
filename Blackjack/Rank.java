
/**
 * Enumeration class Rank - write a description of the enum class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */
public enum Rank
{
   TWO(2), 
   THREE(3), 
   FOUR(4), 
   FIVE(5), 
   SIX(6), 
   SEVEN(7), 
   EIGHT(8), 
   NINE(9), 
   TEN(10), 
   JACK(10), 
   QUEEN(10), 
   KING(10), 
   ACE(11);
   
   private final int rankValue;
   
   Rank(final int rankValue){
       
       this.rankValue = rankValue;
    }
};
