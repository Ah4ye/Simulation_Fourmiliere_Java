/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeudesfourmis.vue;


import javafx.scene.control.Button;


/**
 *
 * @author ahaye
 */
public class ButtonReset extends Button {
    

     public ButtonReset(Plateau p ){
         
         
         super("Reset");
        
        
        setOnAction( event -> {
         p.Reset();
        });
        
     }
        

}
