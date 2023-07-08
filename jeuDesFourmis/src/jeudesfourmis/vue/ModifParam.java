/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeudesfourmis.vue;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;



/**
 *
 * @author Baptiste
 */
public class ModifParam extends VBox {
    
    private int MAX = 75;
    private int MIN = 20;
    
    
    
    private final Button plus;
    private final Button moins;
    private final TextField taille;
    private final TextField nbGraines;
    private int nbGrainesValue;
    private int tailleValue = 50;
    private final HBox ligne;
    
    public ModifParam( Plateau p ){
        
        Alert alert = new Alert(AlertType.ERROR);
        alert.setContentText("Taille Incorrecte");
        ligne = new HBox();
        ligne.setSpacing(10);
        
        taille = new TextField(String.valueOf(tailleValue));
        taille.setOnAction(event -> {
        String text = taille.getText();
        if (Integer.parseInt(text) >= MIN && Integer.parseInt(text) <= MAX) {
            tailleValue = Integer.parseInt(text);
        } else alert.showAndWait();
        
        });
               
        plus = new Button("PLUS");
        plus.setOnAction(event -> {
            if (tailleValue + 1 <= MAX) {
        tailleValue++;
        taille.setText(Integer.toString(tailleValue));
           p.Modif_Taille(tailleValue, tailleValue, tailleValue);
           
        } else alert.showAndWait();            
        });
        
        moins = new Button("MOINS");
        moins.setOnAction(event -> { 
            if (tailleValue - 1 >= MIN) {
        tailleValue--;
        taille.setText(Integer.toString(tailleValue));
         p.Modif_Taille(tailleValue, tailleValue, tailleValue);
        
        
        } else  alert.showAndWait();  
        
        });
        
        nbGraines = new TextField(String.valueOf(nbGrainesValue));
        nbGraines.setOnAction(event -> {
        String textnbGraines = nbGraines.getText();
           nbGrainesValue = Integer.parseInt(textnbGraines);
        });
       
        
        ligne.getChildren().addAll(plus, moins, taille);
        Label graine = new Label("Nb Graine : ");
       
        this.getChildren().addAll(ligne, graine, nbGraines);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(10);
    }
    
    public int getTailleValue(){
        return tailleValue;
    }
   
    public ModifParam getVbox(){
        return this;
    }
    
}
