import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;
import javafx.stage.Stage;

public class AppController implements Initializable{

    //panes
    @FXML public Pane centerPane;
    @FXML private Pane rightPane;
    @FXML private Pane topPane;
    @FXML public Pane demoPane1;
    @FXML public Pane demoPane2;

    //nodes
    @FXML private ComboBox<String> comoBox1;
    @FXML private ComboBox<String> comoBox2;

    @FXML private TextField magnitudeTextField;
    @FXML private TextField playbackTextField;
    @FXML private TextField gridSizeTextField;


    @FXML private CheckBox magnitudeCheckBox;
    @FXML private CubicCurve myCurve;
    @FXML private Slider mySlider;

    @FXML private Label sliderLabel;
    @FXML private Label magnitudeLabel;
    @FXML private Label terminalText;

    private boolean checkSelected;
    private int magnitude;
    private int playbackSpeed = 100;
    private int size = 20;
    private String comboValue1 = "not yet selected";
    private String comboValue2 = "not yet selected";

    @FXML
    void close(ActionEvent event) {
        Node node = (Node)event.getSource();
        Scene scene = (Scene) node.getScene();
        Stage stage = (Stage) scene.getWindow();
        stage.close();  
    }
    
    //update boolean , and text color
    @FXML
    void randomChanged(ActionEvent event) {
        if (magnitudeCheckBox.isSelected()) { 
            checkSelected = true; 
            System.out.println("true");

            magnitudeLabel.setTextFill(Color.GRAY);
            magnitudeTextField.setStyle("-fx-text-inner-color: gray;");
            setTerminalText("Random magnitude: Checked!");//terminal
        }
        else {
            checkSelected = false;
            System.out.println("false");

            magnitudeLabel.setTextFill(Color.BLACK); 
            magnitudeTextField.setStyle("-fx-text-inner-color: black;");
            setTerminalText("Random magnitude: UnChecked!");//terminal

            //set to random in App.java             

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTerminalText("");

        //adding items
        comoBox1.getItems().addAll("Maccaroni", "Diagnal", "Curves", "Spiral", "zigZag", "C-uz why not", "Lightning");
        comoBox2.getItems().addAll("Algorithm1", "Algorithm2");      
        
        //Slider listener
        mySlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {

                sliderLabel.setText("" +(int)mySlider.getValue());
                updateCurve();

                setTerminalText("Slider Value: " + (int)mySlider.getValue());//terminal
            }
        });

        //Magnitude listener
        magnitudeTextField.textProperty().addListener((current, oldVal, newVal) -> {
            try {
                if (!checkSelected) { //checkbox off - update magnitude
                    magnitude = Integer.parseInt(newVal);
                    setTerminalText("Magnitude updated: " + magnitude);//terminal
                }

            }
            catch (NumberFormatException e) {
                if (!checkSelected) { //checkbox off - needs magnitude
                 System.out.println("Missing magnitude!:");
                 setTerminalText("Missing magnitude");//terminal
                }
                 } catch (Exception e) { System.out.println(e); }
        });

        //playbackSpeed listener
        playbackTextField.textProperty().addListener((current, oldVal, newval) -> {
            try {
                playbackSpeed = Integer.parseInt(newval);
                setTerminalText("PlaybackSpeed set to: " + playbackSpeed);//terminal

            }
            catch (NumberFormatException e) {
                System.out.println("Missing playbackSpeed!:");
                setTerminalText("Missing PlaybackSpeed!: Default to 1/4 of a second (100)");//terminal
            } catch (Exception e) { System.out.println(e); }
        });

        //combobox listener
        comoBox1.valueProperty().addListener((current, oldVal, newVal) -> {
            comboValue1 = newVal;
        });
        comoBox2.valueProperty().addListener((current, oldVal, newVal) -> {
            comboValue2 = newVal;
        });
    
    }

    //Visual
    private void updateCurve()
    {
        //length across curve <--->
        double distance = myCurve.getControlX1() - myCurve.getStartX();

        //intervals of 90 taken from distance
        double interval = distance/90;
        //get slider value
        double value = mySlider.getValue() % 90;

        myCurve.setEndX(myCurve.getControlX2() - (interval * value)); //grow into x^2
        //set to front if slider all the way
        if (mySlider.getValue() == 180)
            myCurve.setEndX(myCurve.getStartX());
        
    }

    //set
    public int getDegrees() { return (int)mySlider.getValue(); }
    public int getMagnitude() { return magnitude; }
    public int getPlaybackSpeed() { return playbackSpeed; }
    public int getGridSize() { return size; }
    public TextField getPlaybackTextField() { return playbackTextField; }
    public TextField getGridSizeTextField() { return gridSizeTextField; }

    public boolean getCheckSelected() { return checkSelected; }
    public String getComboValue1() { return comboValue1; }
    public String getComboValue2() { return comboValue2; }
    //set
    public void setMagnitude(int n) { magnitudeTextField.setText("" + n); magnitude = n;} //both textfield and value
    public void setPlaybackSpeed(int n) { playbackTextField.setText("" + n); playbackSpeed = n; }
    public void setTerminalText(String s) { terminalText.setText(s); }
    public void setGridSize(int n) { size = n; }
    //Update magnitude when Gridsize changed
    public void updateMagnitude() {
        int x = (int)(5.0 * Math.log10((double)size + 0.5)) + 1; //
        setMagnitude(x); //from 3 - 9
    }


}

