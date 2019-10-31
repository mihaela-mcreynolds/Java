import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;
import java.util.Random;
import javafx.scene.shape.Shape;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Color;
import javafx.animation.AnimationTimer;
import javafx.scene.text.Text;
import java.time.*;

/**
 * Write a program that displays a circle of radius 10 pixels filled with 
 * a random color at a random location on a pane, as shown in figure 15.29b.
 * When you click the circle, it disappears and a new random color circle is
 * displayed at another random location. After 20 circles are clicked, display
 * the time spent in the pane, as shown in figure 15.29c.
 * 
 * @author Mihaela McReynolds
 * @version 1
 */
public class Hand_Eye_Coordination extends Application
{
    // We keep track of the number of clicks through count
    private int count = 0;    
    @Override
    public void start(Stage stage) 
    {
        Pane myPane = new Pane();
        double width = 600;
        double height = 400;
        
        BorderPane bp = new BorderPane();
                
        Circle c = new Circle(10);
        changeProperties(c, width, height);
	myPane.getChildren().add(c);

	// Time stamp the moment that the window is opened and the game starts
        Instant before = Instant.now(); 
        
        // When the mouse clicks on the circle, it will change location and color
        c.setOnMouseClicked(e -> {
            // Counts number of button clicks and changes the properties of the circle
            if (count < 19){ 
                myPane.getChildren().clear();
                changeProperties(c, width, height);
                myPane.getChildren().add(c);
                count++;
            }
            else{
                // Hide the circle
                myPane.getChildren().remove(c);
                // Timestamp the end of the game
                Instant after = Instant.now();
                // Display the elapsed time in milliseconds
                myPane.getChildren().add(new Text(width / 2, height / 2, "Time spent is " +
                            Duration.between(before, after).toMillis() + " milliseconds"));
            }
        });
        
        // JavaFX must have a Scene (window content) inside a Stage (window)
        Scene scene = new Scene(myPane, width,height);
        stage.setTitle("Click the circle");
        stage.setScene(scene);

        // Show the Stage (window)
        stage.show();
    }
    
    /**
     * Method to change the location and color of the circle, randomly, when it is clicked
     */
    private void changeProperties(Circle c, double width, double height) {
	c.setFill(Color.color(Math.random(), Math.random(), Math.random()));
	c.setCenterX(c.getRadius() + Math.random() * 
		(width - c.getRadius() * 2));
	c.setCenterY(c.getRadius() + Math.random() * 
		(height - c.getRadius() * 2));
    }

}
