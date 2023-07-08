/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jeudesfourmis.vue;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.stage.Stage;


public class ButtonLoupe extends Button {
    private Plateau gridPane;
    private boolean firstClick = true;
    private int columnIndex, rowIndex;
    private int x ;
    private int y ;

    public ButtonLoupe(Plateau gridPane) {
        super("Loupe");
        this.gridPane = gridPane;
        setOnAction(e -> handleZoom());
    }

    private void handleZoom() {
       
            // Créer un ImageView à partir d'une image de la GridPane
            SnapshotParameters sp = new SnapshotParameters();
            sp.setFill(Color.TRANSPARENT);
            Image gridPaneImage = gridPane.snapshot(sp, null);
            ImageView imageView = new ImageView(gridPaneImage);

            // Créer un StackPane pour contenir l'ImageView
            StackPane zoomPane = new StackPane();
            zoomPane.getChildren().add(imageView);

            // Appliquer l'échelle au StackPane
            double scaleFactor = 11.0;
            zoomPane.setScaleX(scaleFactor);
            zoomPane.setScaleY(scaleFactor);

            // Créer une ScrollPane pour permettre de faire défiler le zoomPane
            ScrollPane scrollPane = new ScrollPane(zoomPane);
            scrollPane.setPrefViewportWidth(330);
            scrollPane.setPrefViewportHeight(330);

            // Créer un Slider pour régler l'échelle du zoomPane
            Slider zoomSlider = new Slider(1.0, 20.0, 11.0);
            zoomSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
                double newScaleFactor = newValue.doubleValue();
                zoomPane.setScaleX(newScaleFactor);
                zoomPane.setScaleY(newScaleFactor);
            });

            // Créer un VBox pour contenir le Slider et la ScrollPane
            VBox vbox = new VBox();
            vbox.getChildren().addAll(zoomSlider, scrollPane);

            // Créer une nouvelle fenêtre pour afficher le zoomPane dans la VBox
            Stage zoomStage = new Stage();
            zoomStage.setTitle("Zoom");
            zoomStage.setScene(new Scene(vbox, 330, 380));
            zoomStage.show();

            // Réinitialiser le clic
            firstClick = true;
        
    }
}



        



