import java.util.Stack;
import java.util.Collections;
import java.util.stream.IntStream;

/**
 * A deck of 52 cards
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class DeckOfCards
{
    private final Stack<Card> deckOfCards;
    
    public DeckOfCards()
    {
        this.deckOfCards = initDeck();
    }
    
    private Stack<Card> initDeck()
    {        
        final Stack<Card> deckOfCards = new Stack<>();
        for (final Card.Suit suit : Card.Suit.values()){
            for(final Card.Rank rank : Card.Rank.values()){                
                deckOfCards.push(Card.getCard(rank, suit));
            }
        }
        
        Collections.shuffle(deckOfCards);
        return deckOfCards;
        
    }
    
    public static void main(String[] args)
    {
        final DeckOfCards deck = new DeckOfCards();
        final int numCardsToDeal = 52;
        IntStream.range(0, numCardsToDeal).forEach(value -> System.out.println(deck.deal()));
    }
    
    // Wrap the value in an Optional
    public Card deal(){
        return this.deckOfCards.pop();
    }
   
}
