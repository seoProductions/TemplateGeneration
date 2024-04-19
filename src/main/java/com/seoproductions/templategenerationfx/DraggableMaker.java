package com.seoproductions.templategenerationfx;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class DraggableMaker {
    
    public void setDraggable(Node node , Stage stage){
        
        node.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                node.setOnMousePressed(pressEvent -> {

                    node.setOnMouseDragged(dragEvent -> {
                        stage.setX(dragEvent.getScreenX() - pressEvent.getSceneX()); //mousePos - start
                        stage.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
                    });
                });
            }
            
        });        
    }
}
