/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeudesfourmis.vue;


import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;

public class SliderVitesse extends VBox {

    private final int DEFAULT_VALUE = 40;
    private final Slider slider;
    private final Label value;
    

    public SliderVitesse() {

        // Créer le Slider
        slider = new Slider(10, 150, DEFAULT_VALUE);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(25);
        slider.setMinorTickCount(5);
        slider.setBlockIncrement(10);
        slider.setMaxSize(500, 50);

        // Créer le Label
        value = new Label(String.valueOf(DEFAULT_VALUE));
        value.setAlignment(Pos.CENTER);
        
      
        
        // Ajouter un Listener pour mettre à jour la valeur du Label lorsque le Slider est modifié
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            value.setText(Integer.toString(newValue.intValue()));
        });

        // Ajouter les composants à la VBox
        this.getChildren().addAll(slider, value);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
    }

    public Slider getSlider() {
        return slider;
    }

    public Label getValue() {
        return value;
    }
}
