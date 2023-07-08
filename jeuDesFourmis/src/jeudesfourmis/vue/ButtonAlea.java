/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jeudesfourmis.vue;

import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

/**
 *
 * @author arthur1haye
 */
public class ButtonAlea extends Button {
    
    
    private Plateau p ;
    public ButtonAlea( Plateau p ){
        
        
        super("Alea");
      setOnAction( event -> {
          p.ALea();
        });
    }
    
}
