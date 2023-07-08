/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeudesfourmis.vue;




import javafx.concurrent.Task;
/**
 *
 * @author ahaye
 */
public class Simulation extends Task {
     
         
    Plateau p ;
    //InfoFourmi info ;
     private Boolean EtatPlateau ;
    
    
    public Simulation (Plateau p ){
      this.p = p ;
      //this.info = info ;
       
    }
        
    
    
      @Override
            protected Void call() throws Exception {
                try {
                    boolean running = true;
                    while (running) {
                        
                            
                        Thread.sleep((long) p.getSpeed());
                        if ( EtatPlateau){
                        p.getFourmilier().evolue();
                        p.Refresh();
                        //info.refreshInfo(p);
                        }
                    }
                } catch (InterruptedException e) {
                    System.out.println("La tâche a été interrompue");
                }
                return null;
            }
            
            
            
         // Setter Getter Thread 
    public Task<Void> getTache() {
      
        return this;
    }

    
    
     public Boolean isPause(){
        return this.EtatPlateau ;
    }
    
    public void setPause(Boolean new_etat){
        this.EtatPlateau = new_etat ;
    }       
            
            
}
