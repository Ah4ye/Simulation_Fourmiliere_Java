package jeudesfourmis.vue;

import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
/**
 *
 * @author blacro04
 */
public class InfoFourmi extends VBox {

    private final Label nbGraine = new Label();

    private final Label nbFourmi = new Label();

    private final Label nbIter = new Label();

    private final IntegerProperty iterProperty;
    private final IntegerProperty fourmisProperty;
    private final IntegerProperty grainesProperty;

    private final Plateau p ;
    
    public InfoFourmi(Plateau p) {
        super();
        this.p = p ;
        this.iterProperty = new SimpleIntegerProperty(p.getnbIter());
        this.fourmisProperty = new SimpleIntegerProperty((int) p.getFourmilier().getListFourmi().stream().count());
        this.grainesProperty = new SimpleIntegerProperty((int) p.getFourmilier().getnbGraine());

        setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(2))));
        nbGraine.textProperty().bind(Bindings.concat("Graine : ", grainesProperty));
        nbFourmi.textProperty().bind(Bindings.concat("Fourmis : ", fourmisProperty));
        nbIter.textProperty().bind(Bindings.concat("Iter : ", iterProperty));
        getChildren().addAll(nbGraine, nbFourmi, nbIter);

    }
    
    public void refresh_Info(int iter , int fourmis , int graines ){
        this.grainesProperty.set(graines);
        this.fourmisProperty.set(fourmis);
        this.iterProperty.set(iter);
    }
    
}
