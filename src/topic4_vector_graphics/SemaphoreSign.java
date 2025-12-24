package topic4_vector_graphics;

import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.geometry.Vector;


public class SemaphoreSign implements Drawing {



    @Override
    public void draw(View view) {
        DrawingUtils.clear(view, Color.gray(0.25));




    }



    public static void main(String[] args) {
        DrawingApplication.launch(600, 600);
    }
}
