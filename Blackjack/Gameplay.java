
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.image.Image;
import javafx.scene.layout.ColumnConstraints;
import javafx.geometry.Pos;

/**
 * Write a description of JavaFX class Gameplay here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */


public class Gameplay extends Application
{
    
    // We keep track of the count, and label displaying the count:
    private int playerCount = 0;
    private int dealerCount = 0;
    private Label playerScore = new Label("0");
    private Label dealerScore = new Label("0");
    
    // is the game still playing or is it game over?
    private boolean gameOver = false;
    // flags for when each player is finished hitting
    boolean playerDone = false;
    boolean dealerDone = false;
    
    // Initialize the shuffled deck
    DeckOfCards theDeck = new DeckOfCards();
    
    // Initialize the players
    Player player = new Player("Player");
    Player dealer = new Player("Dealer");
    @Override
    public void start(Stage stage) throws Exception
    {
        // Create a Button or any control item
        Button deal = new Button("Deal");
        Button stand = new Button("Stand");

        // Create the table
        GridPane table = new GridPane();
        table.setPadding(new Insets(10,10,10,10));
        table.setPrefSize(800,600);
        table.setStyle("-fx-background-color: DARKSEAGREEN;");
        
        Image cardBack = new Image("img/b2fv.png");
        
        
        // Create the deck of cards
        Rectangle cardShape = new Rectangle();
        cardShape.setX(50);
        cardShape.setY(50);
        cardShape.setWidth(120);
        cardShape.setHeight(170);
        cardShape.setArcWidth(10);
        cardShape.setArcHeight(10);
        cardShape.setFill(new ImagePattern(cardBack));
    
        // Add the button and label into the pane
        table.add(playerScore, 1, 0);
        table.add(deal, 32, 25);
        table.add(stand, 100, 100);
        table.add(cardShape, 52, 30);
        
        // JavaFX must have a Scene (window content) inside a Stage (window)
        Scene scene = new Scene(table);
        stage.setTitle("Blackjack");
        stage.setScene(scene);
        
        while (!playerDone || dealerDone){
            //player's turn
            if (!playerDone){
                
                // if player hits
                deal.setOnAction(e -> handleDeal(e));
                    
                    // add next card to deck and store whether player is busted
                    playerDone = !player.addCard(theDeck.deal());
                    player.printHand(true);
            }   
            else{
                    playerDone = true;
            }
            
            // dealer's turn
            if (!dealerDone){
                if (dealer.getHandSum() < 17){
                    System.out.println("The dealer hits.\n");
                    dealerDone = !dealer.addCard(theDeck.deal());
                    dealer.printHand(false);
                }
                else{
                    System.out.println("The dealer stays\n");
                    dealerDone = true;
                }
            }
            System.out.println();
        }
        
        
        
        player.addCard(theDeck.deal());
        dealer.addCard(theDeck.deal());
        player.addCard(theDeck.deal());
        dealer.addCard(theDeck.deal());
        
        // show initial hands
        
        player.printHand(true);
        dealer.printHand(false);
        System.out.println("\n");
        
        //print final hands
        player.printHand(true);
        dealer.printHand(true);
        
        int mySum = player.getHandSum();
        int dealerSum = dealer.getHandSum();
        
        if (mySum > dealerSum && mySum <= 21 || dealerSum > 21){
            System.out.println("You win\n");
        }
        else{
            System.out.println("The house wins!\n");
        }
        
        // Show the Stage (window)
        stage.show();
    }
        
    public void handleDeal(ActionEvent e)
    {
        String buttonValue = ((Button)e.getSource()).getText();
        if(buttonValue.equals("Deal")){
            player.addCard(theDeck.deal());
            //table.add(new Image(cardKey+""));
        }
    }
    
    
}
