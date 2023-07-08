/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jeudesfourmis.vue;


import java.util.Random;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import jeudesfourmis.model.Fourmiliere;
import javafx.concurrent.Task;
import javafx.scene.input.MouseButton;
import javafx.scene.shape.Circle;
import jeudesfourmis.model.Fourmi;


/**
 *
 * @author arthur1haye
 */
public class Plateau extends GridPane {
   
    // VARIABLE
    private static final int BOARD_SIZE = 45;
    private static final int CELL_SIZE = 10;
    private static final int MIN_BOARD_PIXEL_SIZE = 20;
    private int nbIter = 0;
    // VARIABLE FOURMILIERE 
    private int cellule ;
    private int largeur ;
    private int hauteur ;
    private Rectangle[][] board; // variable privée pour enregistrer les rectangles du plateau de jeu
    private Circle[][] board_f;
    private int speed = 20; 
    
    
    //Fourmiliere 
    private Fourmiliere four ;
    private boolean murs[][];
    private boolean fourmis[][]; 
    private int MaxGraine = 5 ; 
    
    
    // Outils
    private Circle circle = new Circle(5);
    
    
    
    // SIMULATION ou TASK
    private Task tache ;
    private Boolean EtatPlateau = false ;
    private BooleanProperty pauseProperty = new SimpleBooleanProperty();

    
    // Info Plateau 
    private InfoFourmi Infos ;
    
   // CONSTRUCTEUR
    public Plateau() {
        super();
        // Initialise la taille du plateau à BOARD_SIZE
        this.largeur = BOARD_SIZE;
        this.hauteur = BOARD_SIZE;
        this.cellule = CELL_SIZE ;
        // Crée un tableau de rectangles pour représenter la grille
        this.board = new Rectangle[largeur][hauteur];
        this.board_f = new Circle[largeur][hauteur];
        Init_Fourmiliere(this.MaxGraine);
        this.four.ajouteFourmi(20, 25);
        this.four.setQteGraines(20, 25,4);
        this.four.setQteGraines(25, 20,4);
        InitPlateau();
        setInfosFourmi();
        setTache();
    }

    // SETTERS GETTERS
    public int gethauteur(){
        return this.hauteur;
    }
    
    public void sethauteur( int newh){
        if ( newh < MIN_BOARD_PIXEL_SIZE ) {
            System.out.println("La taille du plateau de jeu est inférieure à la taille minimale, valeur changer !");
            this.largeur = BOARD_SIZE ;
            this.hauteur = BOARD_SIZE ;
        }
        else 
        this.hauteur = newh ;
    }
    public int getlargeur(){
         return this.largeur;
    }
    public void setlargeur( int newl){
        if ( newl < MIN_BOARD_PIXEL_SIZE ) {
            System.out.println("La taille du plateau de jeu est inférieure à la taille minimale, valeur changer !");
            this.largeur = BOARD_SIZE ;
            this.hauteur = BOARD_SIZE ;
        }
        this.largeur = newl ;
    }

    public int getcellule(){
         return this.cellule;
    }
    
    public Rectangle[][] getBoardRectangle(){
        return this.board ;
    }
    public void setBoardRectangle( Rectangle[][] new_board ){
        this.board = new_board;
    }
    
    public Circle[][] getBoardCircle(){
        return this.board_f ;
    }
    public void setBoardCircle( Circle[][] new_board ){
        this.board_f = new_board;
    }
    
    public int getMaxGraine(){
        return this.MaxGraine;
    }
    public void setMaxGraine( int newMax ){
        this.MaxGraine = newMax;
    }
    
    public Rectangle[][] getRectangles(){
         return this.board;
    }
    
    public void setRectangles( Rectangle[][] rec ){
        this.board = rec ;
    }
    
    public void reset_Rec_Circl(){
    
        this.board = new Rectangle[largeur][hauteur];
        this.board_f = new Circle[largeur][hauteur];
        
    }
    
    
     public Fourmiliere getFourmilier()
    {
        return this.four ;
    }
    // Setter Getter Thread 
    public Task getTache() {
        return this.tache;
    }

    public void setTache(){
        this.tache = new Task() { 
            @Override
            protected Void call() throws Exception {
                try {
                    while (true) {
                        
                        Thread.sleep(speed);
                        
                        if ( !EtatPlateau){
                            four.evolue();
                            Refresh();
                        Platform.runLater(() -> {Infos.refresh_Info(nbIter, four.getListFourmi().size(), four.getnbGraine());});
                            nbIter ++; }
                    }
                } catch (InterruptedException e) {
                  
                }
                return null;
            }
        };
    
    }
    
   public Boolean isPause(){
        return this.EtatPlateau ;
    }

    public void setPause(boolean pause) {
    this.EtatPlateau = pause;
    pauseProperty.set(pause);
    }
     
    public int getnbIter(){
        return this.nbIter;
    }
    
    public double getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
   
    
    //////////////////////////////////////////////////// 
     public void Init_Fourmiliere( int MaxGraine) {
           // Crée une fourmilière et récupère les murs
        this.four = new Fourmiliere(largeur, hauteur, MaxGraine);
        this.murs = this.four.getAllMur();
        this.fourmis = this.four.getAllFourmi();
        this.MaxGraine = MaxGraine ;
     }
     
     public void InitPlateau(){
         this.murs = this.four.getAllMur();
        this.fourmis = this.four.getAllFourmi();
        
        // Parcourt chaque case de la grille
        for (int row = 0; row < this.largeur; row++) {
            for (int col = 0; col < this.hauteur; col++) {
                // Crée un nouveau rectangle blanc
                Rectangle cell = new Rectangle(this.cellule, this.cellule, Color.WHITE);
                Circle circle = new Circle(5, Color.WHITE);
                circle.setVisible(false);
                // Ajoute une bordure noire à chaque rectangle
                cell.setStroke(Color.BLACK);
                // Ajoute le rectangle à la fenêtre principale
                this.add(cell, row, col);
                this.add(circle, row, col);
                // Si la case correspond à un mur, la colorie en noir
                if (this.murs[row][col] ) {
                    cell.setFill(Color.BLACK);
                    circle.setFill(Color.BLACK);}
                
                if ( this.fourmis[row][col] ){
                    // pos inversé des fcnt (ajoute Fourmi + getFourmi) et (setGraine)
                    Fourmi f = this.four.getFourmi(col, row);
                    if (  f!= null && f.porte() ){
                        circle.setVisible(true);
                        circle.setFill(Color.BLUE);  
                    }
                    else {
                        circle.setVisible(true);
                        circle.setFill(Color.GREEN);
                    }
                }
                if ( this.four.getQteGraines(row, col) > 0)
                {
                   int nbr_graines = this.four.getQteGraines(row, col) ;
                   if (nbr_graines == 1){
                        cell.setFill(Color.PINK);  }
                   else if (nbr_graines == 2){
                        cell.setFill(Color.TOMATO); }
                   else{
                       cell.setFill(Color.RED); }
                }
                // Ajoute le rectangle à la grille
                this.board[row][col] = cell;
                this.board_f[row][col] = circle ;
            }
        }
     }
     
     public void Refresh(){
        // Parcourt chaque case de la grille
        for (int row = 0; row < largeur; row++) {
            for (int col = 0;col < hauteur; col++) {
                // Crée un nouveau rectangle blanc
                Rectangle cell = board[row][col];
                Circle circle = board_f[row][col];
                int nbr_graines = four.getQteGraines(row, col) ;   
                 circle.setVisible(false);
                 
                if ( nbr_graines >= 0){
                       if (nbr_graines == 1){
                            cell.setFill(Color.PINK);
                       }
                       else if (nbr_graines == 2){
                            cell.setFill(Color.TOMATO);
                       }
                       else if (nbr_graines > 2) {
                           cell.setFill(Color.RED);
                       }
                       else  {
                         cell.setFill(Color.WHITE);
                        }
                    } 
                if (murs[row][col]  ) {
                    cell.setFill(Color.BLACK);
                }
                if ( fourmis[row][col] ){

                    Fourmi f = four.getFourmi(col, row);
                    if (  f!= null && f.porte() ){  
                        circle.setVisible(true);
                        circle.setFill(Color.BLUE);  
                    }
                    else {
                        circle.setVisible(true);
                        circle.setFill(Color.GREEN);
                    }
                }
                }
            }
        
        
     }
     
     
     public void Reset(){
        this.four.realloc(hauteur, largeur);
         this.murs = this.four.getAllMur();
        this.fourmis = this.four.getAllFourmi();
        this.MaxGraine = MaxGraine ;
        Platform.runLater(() -> {Infos.refresh_Info(nbIter, four.getListFourmi().size(), four.getnbGraine());});
         Refresh();
     }
     
     
     public void Modif_Taille( int hauteur, int largeur, int tailleValue){
        // Reset Node Plateau 
        this.tache.cancel();
        getChildren().clear();
        // Reset Fourmiliere
        this.four.realloc(hauteur, largeur);
        this.murs = this.four.getAllMur();
        Platform.runLater(() -> {Infos.refresh_Info(nbIter, four.getListFourmi().size(), four.getnbGraine());});
        this.fourmis = this.four.getAllFourmi();
         
        //Reset Parametre Plateau 
        this.sethauteur(tailleValue);
        this.setlargeur(tailleValue);
        this.reset_Rec_Circl();
        this.InitPlateau();
     
        setTache();
        
         
     }
     
     
     public void setInfosFourmi(){
         InfoFourmi infos = new InfoFourmi(this);
         this.Infos = infos ;
     }
     public InfoFourmi getInfosFourmi(){
         return this.Infos ;
     }
     
     
    public void ajouterFourmiAvecShiftClic() {
    this.setOnMousePressed(event -> {
        if (event.isShiftDown() && event.getButton() == MouseButton.PRIMARY) {
            int x = (int) (event.getX() / CELL_SIZE -2);
            int y = (int) (event.getY() / CELL_SIZE-2);
            if (this.fourmis[x][y]){
               Fourmi f = this.four.getFourmi(y, x);
               this.four.getListFourmi().remove(f);
               
                
            }else{ 
                this.four.ajouteFourmi(y, x); 
                
            }

            }
        else if ( event.getButton() == MouseButton.PRIMARY) {
            int x = (int) (event.getX() / CELL_SIZE -2);
            int y = (int) (event.getY() / CELL_SIZE-2);
             if (this.murs[x][y]){
                 this.four.setMur(y, x, false);
             }
             else { this.four.setMur(y, x, true); }

            }
         });
    this.setOnScroll(event -> {
        
            int x = (int) (event.getX() / CELL_SIZE -2);
            int y = (int) (event.getY() / CELL_SIZE-2);
            int delta = (int)event.getDeltaY(); 
            int new_qt = this.four.getQteGraines(y, x);
                // Modifier la taille de la police en fonction de la direction de défilement
                if (delta < 0) {
                    // La souris est défilée vers le haut
                    if ( (new_qt+ delta) <= this.MaxGraine)
                    this.four.setQteGraines(x, y, new_qt+(delta*-1));
                } else {
                    // La souris est défilée vers le bas
                    if ( (new_qt+ delta) >= 0 && new_qt < this.MaxGraine)
                    this.four.setQteGraines(x, y, new_qt-(delta));
                }
          
        });
        
    
    }
    
    
    public void ALea(){
        Random rand = new Random();
         Random rand2 = new Random();
        
         int setfourmiX = rand.nextInt(this.largeur);
         int setfourmiY = rand.nextInt(this.hauteur);
        int setgraineX = rand2.nextInt(this.largeur);
        int setgraineY = rand2.nextInt(this.hauteur);
        
        
        
        this.four.setQteGraines(setgraineX, setgraineY, 4);
        this.four.ajouteFourmi(setfourmiY, setfourmiX);
       
    }
   

}