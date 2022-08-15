import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application{

    AppController controller;
    
    //instance of tile manager -  gets passed in values from controller
    TileManager tileManager; 

    @Override
    public void start(Stage primaryStage) throws Exception {
        //load
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AppGui.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
        controller = loader.getController();

        //Set default values
        controller.updateMagnitude();
        controller.setPlaybackSpeed(100);     
        
        //TileManager
        tileManager = new TileManager(controller);//pass in controller
        
        //animate at the start
        tileManager.animateAlgorithm(controller.demoPane1);
        tileManager.animateAlgorithm(controller.demoPane2);

        //listener to reset demo Animation playback - with different playbackSpeed
        controller.getPlaybackTextField().textProperty().addListener((current, oldVal, newVal) -> {
            try {
                int checkingforInteger = Integer.parseInt(newVal);
                tileManager.animateAlgorithm(controller.demoPane1);
                tileManager.animateAlgorithm(controller.demoPane2);

            } catch (Exception e) { controller.setTerminalText("Error with playbackSpeed: Rendering animation demo");
            }
        });

        //size textfield listener (update gridsize)
        controller.getGridSizeTextField().textProperty().addListener((current, oldVal, newVal) -> {
            try {
                int x = Integer.parseInt(newVal);
                if ((x >= 3 & x <= 50)){
                    /*update grid size, and magnitude (calculated recomended value)
                     * re-render animatinos
                     */
                    controller.setGridSize(x);
                    controller.updateMagnitude();
                    //re-render demoAnimations
                    tileManager.animateAlgorithm(controller.demoPane1);
                    tileManager.animateAlgorithm(controller.demoPane2);
                    controller.setTerminalText("Size set to: " + x + "x" + x);//terminal

                    //re-render grid
                    tileManager.createCheckerBoard();
                }
                else
                controller.setTerminalText("Please input numbers from 3 - 50");

            }
            catch (NumberFormatException e) {
                controller.setTerminalText("Missing Size: Default to 20x20");//terminal
            } catch (Exception e) { System.out.println(e); }
        });

        //when basepane clicked , start generation in TileManager.java
        controller.centerPane.setOnMouseClicked(mouseEvent -> {
            tileManager.generate();
        });
        System.out.println("set magnitude half for recursive");
    }

    public static void main(String[] args) throws Exception { launch(args); }

}
