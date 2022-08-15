import java.util.ArrayList;
import java.util.Iterator;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class TileManager {
    //instantiate IntFields, generation , and get values from controller

    AppController controller;

    //controller values
    private boolean checkSelected;
    private String GenTemplate;
    private String GenAlgorithm;
    private int playbackSpeed;
    private int magnitude;
    private int degrees;
    
    private int size;//centerPane
    private int demoSize;//demoPane

    //constructor
    public TileManager(AppController c) 
    {
        //controller first
        setTileManagerController(c);
        updateControllerValues();
        createCheckerBoard();//initialize checkerboard
    }

    /* Pane gets passed in from App.java , both demo panes called */
    public void animateAlgorithm(Pane pane)
    {

        //updating
        pane.getChildren().clear();
        updateControllerValues();

        

        //create animations..
        IntField field1 = new IntField(demoSize, demoSize);
        field1.createNew(1);

        SequentialTransition seqT = new SequentialTransition();
        //initializing pane
        //setting animations
        for (Rectangle r : intFieldtoArrayList_Animation(field1, pane)) {
            //pane
            pane.getChildren().add(r);

            //animation
            FadeTransition f1 = fadeIn(r, playbackSpeed);  //set playbackspeed update******
            FadeTransition f2 = fadeOut(r, playbackSpeed);
            seqT.getChildren().addAll(f1, f2);

        }
        seqT.setCycleCount(Animation.INDEFINITE);
        seqT.play();
    }

    public void createCheckerBoard()
    {
        updateControllerValues();

        //initialize checkerboard
        IntField checkerBoard = new IntField(size, size);
        for (FancyRectangle r : intFieldtoArrayList_CheckerBoard(checkerBoard, controller.centerPane)) {
            //add to pane
            controller.centerPane.getChildren().add(r.getRectangle());
        }
        
    }
    
    //UPDATE VALUES! IMPORTANT
    private void updateControllerValues() {

        checkSelected = controller.getCheckSelected();
        degrees = controller.getDegrees();
        GenTemplate = controller.getComboValue1();
        GenAlgorithm = controller.getComboValue2();
        magnitude = controller.getMagnitude();
        playbackSpeed = controller.getPlaybackSpeed();
        size = controller.getGridSize();
        demoSize = magnitude;//binding magnitude with demoSize


    }

    public void generate()
    {
        updateControllerValues();
        //if comboBox values valid: proceed
        if (!GenTemplate.equals(("not yet selected")) && !GenAlgorithm.equals("not yet selected"))
        {
            
            //reinitializing pane
            controller.centerPane.getChildren().clear();
            createCheckerBoard();

            IntField intField = new IntField(size, size);
            //seting values
            if (checkSelected)
                 intField.setRandomIterations(true);
            else intField.setRandomIterations(false);
            intField.setIterations(magnitude);
            intField.setDegrees(degrees);

            //start pos
            int x = FancyRectangle.startingX;
            int y = FancyRectangle.startingY;

            //now we use generation templates
            GenerationTemplates template = new GenerationTemplates(y, x);
            switch (GenTemplate){
                case "Maccaroni":
                    template.Maccaroni(intField);//row first then column
                    break;
                case "Diagnal":
                    template.diagnal(intField);//row first then column
                    break;
                case "Curves":
                    template.curves(intField);//row first then column
                    break;
                case "Spiral":
                    template.spiral(intField);//row first then column
                    break;
                case "zigZag":
                    template.zigZag(intField, magnitude); //magnitude for-> moving starting pos
                    break;
                case "C-uz why not":
                        template.C(intField, magnitude); //magnitude for-> moving starting pos
                    break;
                default:
                    controller.setTerminalText("Error with Generation Templates:");
                    break;
            }
            //terminal
            controller.setTerminalText("Generating...");   
            System.out.println(intField); 

            //***************Animatimate using timeline*****************
            //hold order of rectangles
            ArrayList<FancyRectangle> arrayList = intFieldtoArraylist1(intField, controller.centerPane);
            Iterator<FancyRectangle> rectangleList = arrayList.iterator();
            //iterate
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(playbackSpeed), frame -> {
                if (rectangleList.hasNext())
                    //add to pane
                    controller.centerPane.getChildren().add(rectangleList.next().getRectangle());                     
            }));
            timeline.setCycleCount(arrayList.size());
            timeline.play(); //and viola 
            timeline.setOnFinished(e -> controller.setTerminalText("Generation Sucess!"));
        }
        else { 
            controller.setTerminalText("Error in starting: Please select Algorithm & Template! ");
        }
        
        //intfield to Arraylist , - and into timline to animate
        //for now , proceed with traverising algorithm
    }
    /****** Algorithm 1*********
     * Returns ONLY rectangles as-> FancyRectangles -> Arraylist
     * Only returns rectangles from intgrid that have been generated by GenerationTemplates.java */
    public ArrayList<FancyRectangle> intFieldtoArraylist1(IntField intField, Pane pane)
    {
        //getting pane length to variable
        double paneWidth = pane.getPrefWidth() / size;  //interval of size
        double paneHeight = pane.getPrefHeight() / size;
        
        ArrayList<FancyRectangle> list = new ArrayList<FancyRectangle>();
        //turn IntField to array
        int[][] grid = intField.getGrid();

        for (int r = 0; r < grid.length; r++)
        {
            for (int c = 0; c < grid[0].length; c++)
            {
                if (grid[r][c] == 1)
                {
                    FancyRectangle rectangle = new FancyRectangle(c * paneWidth, r * paneHeight, paneWidth, paneHeight, Color.valueOf("45413a"));
                    list.add(rectangle);
                }
            }
        }
        return list;
    }
    /*Returns ALL rectangles as-> FancyRectangles -> ArrayList */
    public ArrayList<FancyRectangle> intFieldtoArrayList_CheckerBoard(IntField intField, Pane pane)
    {
        //getting pane length to variable
        double paneWidth = pane.getPrefWidth() / size;  //interval of size
        double paneHeight = pane.getPrefHeight() / size;

        //turn IntField to array
        int[][] grid = intField.getGrid();

        ArrayList<FancyRectangle> list = new ArrayList<FancyRectangle>();
        for (int r = 0; r < grid.length; r++){ //row
            for (int c = 0; c < grid[0].length; c++){//column

                FancyRectangle rectangle;
                //diff color
                if((r + c) % 2 == 0){
                    rectangle = new FancyRectangle(paneWidth * c, paneHeight * r, paneWidth, paneHeight, Color.valueOf("e6dac3"));

                } else {
                    rectangle = new FancyRectangle(paneWidth * c, paneHeight * r, paneWidth, paneHeight, Color.valueOf("8f7f60"));
                }

                rectangle.setGridX(c);//column
                rectangle.setGridY(r);//row
                list.add(rectangle);
                
            }
        }
        return list;
    }
    /*Returns ALL rectangles -> ArrayList */
    public ArrayList<Rectangle> intFieldtoArrayList_Animation(IntField intField, Pane pane)
    {
        //getting pane length to variable
        double paneWidth = pane.getPrefWidth() / demoSize; 
        double paneHeight = pane.getPrefHeight() / demoSize;

        //turn IntField to array
        int[][] grid = intField.getGrid();

        //this is the algorithm--- create bool to choose between 1 and 2 *************
        ArrayList<Rectangle> list = new ArrayList<Rectangle>();
        for (int r = 0; r < grid.length; r++){ //row
            for (int c = 0; c < grid[0].length; c++){//column
                Rectangle rectangle = new Rectangle(paneWidth * c, paneHeight * r, paneWidth, paneHeight);
                rectangle.setFill(Color.valueOf("e6dac3"));
                list.add(rectangle);
            }
        }
        return list;
    }

    //animation stuff
    public FadeTransition fadeOut(Node node, int duration)
    {
        FadeTransition f = new FadeTransition();
        f.setNode(node);
        f.setDuration(Duration.millis(duration));
        f.setFromValue(1);
        f.setToValue(0);
        return f;
    }
    public FadeTransition fadeIn(Node node, int duration)
    {
        FadeTransition f = new FadeTransition();
        f.setNode(node);
        f.setDuration(Duration.millis(duration));
        f.setFromValue(0);
        f.setToValue(1);
        return f;
    }
    
    public void setTileManagerController(AppController controller) { this.controller = controller; }

}
