/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeudesfourmis.vue;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 *
 * @author blacro04
 */
public class QuitBtn implements EventHandler{
        public Button btn;
        private Plateau p ;
        public QuitBtn( Plateau p){
            this.btn = new Button();
            btn.setText("QUITTER");
            btn.setLayoutX(340);
            btn.setLayoutY(375);
            btn.addEventHandler(ActionEvent.ACTION, this );
            this.p = p ;
        }
        public Button getButton(){
            return this.btn;
        }
        @Override
        public void handle(Event event) {
             this.p.getTache().cancel();
            Platform.exit();}  
}