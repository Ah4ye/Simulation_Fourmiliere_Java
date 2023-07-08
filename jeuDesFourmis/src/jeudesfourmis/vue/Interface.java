/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jeudesfourmis.vue;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
/**
 *
 * @author arthur1haye
 */
public class Interface {
    private final Stage stage;
    private Button button;
    
    
    public Interface(Stage stage) {
        this.stage = stage;
        initUI();
    }

    public void initUI() {
        button = new Button("Cliquez ici");
        BorderPane root = new BorderPane();
       
        
        Plateau p = new Plateau();
       
        
        
        InfoFourmi info = p.getInfosFourmi();
        
        SliderVitesse slider = new SliderVitesse();
        root.setTop(slider);
        slider.getSlider().valueProperty().addListener((observable, oldValue, newValue) -> {
            p.setSpeed(newValue.intValue());
        });
        
       
        ModifParam modifTaille = new ModifParam(p);
        
        p.ajouterFourmiAvecShiftClic();
        
        
        
        ButtonLoupe Loupe = new ButtonLoupe(p);
        QuitBtn quitter = new QuitBtn(p);
        ButtonAlea ale = new ButtonAlea(p);
       
        
        
        VBox conteneur_droit = new VBox();
        VBox conteneur_gauche = new VBox();
        VBox conteneur_centre = new VBox();
        
        HBox ligne = new HBox();
        ligne.getChildren().addAll(new Pane(),p, new Pane());
        HBox.setHgrow(ligne.getChildren().get(0), Priority.ALWAYS);
        HBox.setHgrow(ligne.getChildren().get(2), Priority.ALWAYS);
        
        conteneur_droit.getChildren().addAll(info, new Pane(), quitter.getButton() );
        conteneur_droit.setAlignment(Pos.TOP_CENTER);
        VBox.setVgrow(info, Priority.NEVER);
        VBox.setVgrow(conteneur_droit.getChildren().get(1), Priority.ALWAYS);
        VBox.setVgrow(quitter.getButton(), Priority.NEVER);
        root.setRight(conteneur_droit);
        
        conteneur_gauche.getChildren().addAll(new Pane() , modifTaille, Loupe, new Pane() , ale);
        conteneur_gauche.setSpacing(10);
        VBox.setVgrow(conteneur_gauche.getChildren().get(0), Priority.ALWAYS);
        VBox.setVgrow(conteneur_gauche.getChildren().get(3), Priority.ALWAYS);
        root.setLeft(conteneur_gauche);
        
        conteneur_centre.getChildren().addAll(new Pane(), ligne, new Pane ());
        VBox.setVgrow(conteneur_centre.getChildren().get(0), Priority.ALWAYS);
        VBox.setVgrow(conteneur_centre.getChildren().get(2), Priority.ALWAYS);
        
        root.setCenter(conteneur_centre);
        
               
      
        ButtonStart Start = new ButtonStart(p);
        ButtonReset Reset = new ButtonReset(p);
        Start.pausedProperty().addListener((observable, oldValue, newValue) -> {
            slider.setDisable(newValue);
            conteneur_gauche.setDisable(newValue);
        });

        VBox vBox = new VBox(Reset, Start);
        root.setBottom(vBox);
        BorderPane.setAlignment(quitter.getButton(), Pos.BOTTOM_RIGHT);
        
        
        root.setCenter(p);
         BorderPane.setAlignment(p, Pos.CENTER);
         BorderPane.setMargin(p, new Insets(20, 0, 0, 350));
         
        
         

         
    
        Scene scene = new Scene(root, 1300, 1000);
        stage.setTitle("JeuDesFourmis");
        stage.setScene(scene);
        stage.show();
    }

    public void setOnButtonClickListener(Runnable listener) {
        button.setOnAction(event -> listener.run());
    }
}








