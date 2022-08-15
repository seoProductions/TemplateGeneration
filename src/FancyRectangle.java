import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class FancyRectangle extends Rectangle {

    Rectangle rectangle;
    Color color;

    //Where the rectangle cooresponding to IntField
    //to itizialize generation
    private int x;
    private int y;
    public static int startingX = 0;
    public static int startingY = 0;

    public FancyRectangle() { //instanciate object for static use
    }
    /*Rectangle class with extra Functionality ~ */
    public FancyRectangle(double x, double y, double width, double height, Color c)
    {
        rectangle = new Rectangle(x, y, width, height);
        //set color
        color = c;
        rectangle.setFill(c);
        rectangle.styleProperty().set("-fx-border-color: transparent");
        //Hover listener
        rectangle.hoverProperty().addListener((obs, oldval, newValue) -> {
            if (newValue)
                rectangle.setFill(color.brighter());
            else
            {
                rectangle.setFill(color);
            }
        });;
        //Mouse pressed
        rectangle.setOnMousePressed(event -> {
            //update COORDS to start
            startingX = this.x;
            startingY = this.y;

        });

    }

    public Rectangle getRectangle() { return rectangle; }
    //coords set in TileManager.java inside iterations
    public void setGridX(int x) { this.x = x; }
    public void setGridY(int y) { this.y = y; }


    
    

}   
