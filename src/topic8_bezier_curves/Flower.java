package topic8_bezier_curves;

import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetAnimation;
import mars.drawingx.gadgets.annotations.GadgetDouble;
import mars.drawingx.gadgets.annotations.GadgetInteger;
import mars.geometry.Vector;


public class Flower implements Drawing {

    @GadgetAnimation(start = true)
    double time = 0.0;

    Vector o  = new Vector( 0, -200);  // Koren cveta

    // levi list
    Vector ll1 = new Vector(-150, -100);  // leva ivica - kontrolna tacka 1
    Vector ll2 = new Vector(-100,    0);  // leva ivica - kontrolna tacka 2
    Vector l   = new Vector(-150,  100);  // vrh levog lista
    Vector lr1 = new Vector(-100,   50);  // desna ivica - kontrolna tacka 1
    Vector lr2 = new Vector(   0, -100);  // desna ivica - kontrolna tacka 2

    // stabljika
    Vector s1 = new Vector(   0,    0);   // kontrolna tacka 1
    Vector s2 = new Vector( 100,  100);   // kontrolna tacka 2

    // desni list
    Vector rl1 = new Vector(   0, -100);  // leva ivica - kontrolna tacka 1
    Vector rl2 = new Vector( 200, -100);  // leva ivica - kontrolna tacka 2
    Vector r   = new Vector( 250,    0);  // vrh levog lista
    Vector rr1 = new Vector( 250, -100);  // desna ivica - kontrolna tacka 1
    Vector rr2 = new Vector( 100, -200);  // desna ivica - kontrolna tacka 2

    // cvet
    @GadgetDouble(min = 0, max = 100)
    double rDisk  = 35;                   // poluprecnik

    @GadgetDouble(min = 0, max = 200)
    double lPetal = 100;                  // duzina latice

    @GadgetInteger
    int nPetals = 5;                      // broj latica



    @Override
    public void draw(View view) {
        DrawingUtils.clear(view, Color.gray(0.125));

        //TODO

    }




    public static void main(String[] args) {
        DrawingApplication.launch(600, 600);
    }

}
