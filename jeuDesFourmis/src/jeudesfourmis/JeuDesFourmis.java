/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeudesfourmis;

import javafx.application.Application;
import javafx.stage.Stage;

import jeudesfourmis.vue.*;

/**
 *
 * @author ahaye
 */
public class JeuDesFourmis extends Application {

  private Interface vue;

    @Override
    public void start(Stage stage) {
        this.vue = new Interface(stage);
        this.vue.setOnButtonClickListener(() -> {
            System.out.println("Le bouton a été cliqué !");
        });
    }

    public static void main(String[] args) {
            launch(     args);
        }

}