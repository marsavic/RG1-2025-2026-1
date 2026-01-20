package topic5_transformations;

import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetInteger;
import mars.geometry.Transformation;
import mars.geometry.Vector;
import mars.random.RNG;


public class Cards implements Drawing {

    @GadgetInteger(min = 1, max = 16)
    int nCards = 6;

    Vector size = new Vector(150, 200); // velicina karte
    Vector a = new Vector(20, 20); // zakrivljenost ugla karte

    double r = 48; // poluprecnik kruznice

    Vector d = new Vector(16, 16); // distnca izmedju dve karte


    void drawCard(View view) {
        //TODO
    }


    @Override
    public void draw(View view) {
        view.setTransformation(Transformation.translation(new Vector(-120, -120)));
        DrawingUtils.clear(view, Color.hsb(120, 0.5, 0.2));

        RNG rng = new RNG(6380977105788498275l);

        for (int i = nCards - 1; i >= 0; i--) {
            view.stateStore();


            //TODO


            drawCard(view);

            view.stateRestore();
        }
    }


    public static void main(String[] args) {
        DrawingApplication.launch(800, 600);
    }

}
