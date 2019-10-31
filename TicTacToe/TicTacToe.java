/**
 * Problem 14.2, page 586 in Java Lang. Write a program that displays
 * a tic-tac-toe board, as shown in Fihure 14.43b. A cell may be X, O,
 * or empty. What to display at each cell is randomly decided. The X
 * and O are the image files x.gif and o.gif.
 * @ Mihaela McReynolds
 */
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

// New class extends Application
public class TicTacToe extends Application {    
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Create a pane and set its properties
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(12, 12, 12, 12));
        pane.setHgap(5);
        pane.setVgap(5);
        
        // Create images for the photos of "X" and "O"
        Image imageX = new Image("x.gif");
        Image imageO = new Image("o.gif");
                
        // Randomly insert one of the three options (X, O, blank) in a GridPane 
        for (int i = 0; i < 3; i++) { // 3 rows
            for (int j = 0; j < 3; j++) {  // 3 columns
                int rand = (int)(Math.random() * 3);
                if (rand == 0) {
                    pane.add(new ImageView(imageX), j, i);     
                }
                else if (rand == 1) {
                    pane.add(new ImageView(imageO), j, i);     
                }        
            }
        }
        
        // Create a scene and place it in the stage
        Scene scene = new Scene(pane);
        primaryStage.setTitle("TicTacToe"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }
  
    /**
     * The main method 
     */
    public static void main(String[] args) {
    launch(args);
    }
} 