/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeudesfourmis.vue;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Button;

/**
 *
 * @author ahaye
 */
public class ButtonStart extends Button {

    private BooleanProperty pausedProperty;
    
    public ButtonStart(Plateau p) {
       
        super("Start");
        
        pausedProperty = new SimpleBooleanProperty(p.isPause());
       
        setOnAction(event -> {
            
            boolean paused = p.isPause();
            pausedProperty.set(!paused);
            if (p.getTache().isRunning()) {
                p.setPause(!paused);
                paused = p.isPause();
                if (paused) {
                    setText("Resume");
                } else {
                    setText("Pause");
                    pausedProperty.set(!paused);
                }
                pausedProperty.set(!paused);
            } else {
                 pausedProperty.set(paused);
                new Thread(p.getTache()).start();
                setText("Pause");
                pausedProperty.set(paused);
            }
        });
    }

    public BooleanProperty pausedProperty() {
        return pausedProperty;
    }

}