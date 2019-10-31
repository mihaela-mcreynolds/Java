import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/**
 * Write a description of class Card here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Card 
{
    private final Rank rank;
    private final Suit suit;
    
    private Card(final Rank rank, final Suit suit)
    {
        this.rank = rank;
        this.suit = suit;
    }
    
    private final static Map<String, Card> CARD_CACHE = initCache();

    private static Map<String, Card> initCache() {
        final Map<String, Card> cache = new HashMap<>();
        for (final Suit suit : Suit.values()) {
            for (final Rank rank : Rank.values()) {
                cache.put(cardKey(rank, suit), new Card(rank, suit));
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    static Card getCard(final Rank rank, final Suit suit) {
        final Card card = CARD_CACHE.get(cardKey(rank, suit));
                   return card;
    }

    public Rank getRank() {
        return this.rank;
    }

    public Suit getSuit() {
        return this.suit;
    }

    private static String cardKey(final Rank rank,final Suit suit) {
        return rank + " of " + suit;
    }

    @Override
    public String toString() {
        return String.format("%s of %s", this.rank, this.suit);
    }

    enum Rank
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
       
       
       static int rankValue;
   
       Rank(int rankValue){
           
           rankValue = rankValue;
        }
      
    };
    
    public int getNumber(int rankValue){
        return rankValue;
    }
    
    enum Suit
    {
        DIAMONDS, CLUBS, HEARTS, SPADES 
    }

}