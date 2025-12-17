package topic3_rasterization;

import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetAnimation;
import mars.drawingx.gadgets.annotations.GadgetInteger;
import mars.drawingx.utils.camera.CameraSimple;
import mars.geometry.Vector;
import mars.input.InputEvent;
import mars.input.InputState;
import mars.utils.Numeric;

import java.util.function.Predicate;


public class Antialiasing implements Drawing {

    @GadgetAnimation(start = true)
    double time = 0.0;

    @GadgetInteger(min = 1)
    int subdivision = 3; // Svaki piksel će biti podeljen na subdivision*subdivision subpixela.
    // Sto je subdivision veci, to je antialias bolji, ali sporiji.

    @GadgetInteger(min = 0, max = 16)
    int imageIndex = 0;

    CameraSimple camera = new CameraSimple();



    /**
     * Crta antialiasovanu sliku implicitno zadatog oblika.
     *
     * @param inside Funkcija: Vector -> Boolean. Vraca da li je zadata pozicija unutar oblika. Vektor (0, 0) je u sredini slike.
     * @param sizeX Sirina slike.
     * @param sizeY Visina slike.
     * @param subdivision Svaki piksel se deli na subdivison^2 subpiksela koji se koriste za antialiasing.
     * @return Antialiasovana slika zadatog oblika.
     */
    Image antialiasedShape(Predicate<Vector> inside, int sizeX, int sizeY, int subdivision) {
        WritableImage image = new WritableImage(sizeX, sizeY);
        PixelWriter pw = image.getPixelWriter();

        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                // Pixel (x, y)

                int count      = 0; // Koliko subpiksela je unutar oblika.
                int countTotal = 0; // Ukupan broj testiranih subpiksela.

                for (int sy = 0; sy < subdivision; sy++) {
                    for (int sx = 0; sx < subdivision; sx++) {
                        // Subpiksel (sx, sy) piksela (x, y).

                        // Vektor p je polozaj centra subpiksela.
                        Vector p = new Vector(
                                x - sizeX / 2.0 + (sx + 0.5) / subdivision,
                                sizeY / 2.0 - y + (sy + 0.5) / subdivision
                        );

                        if (inside.test(p)) { // Ako je posmatrani subpixel u obliku, povecavamo brojac.
                            count++;
                        }
                        countTotal++;
                    }
                }

                double k = 1.0 * count / countTotal; // Udeo subpixela koji su u obliku.
                pw.setColor(x, y, Color.gray(1.0, k));
            }
        }

        return image;
    }

    //Vezbe

    // Prava x=y
    Predicate<Vector> line45(double thickness) {
        return p -> Math.abs(p.y - p.x) <= thickness / 2.0;
    }


    // Poluravan
    Predicate<Vector> halfPlane(double coefficient) {
        return p -> false;
    }


    // Pravougaonik (axis-aligned) dimenzija axb
    Predicate<Vector> rectangle(double a, double b) {
        return p -> false;
    }


    // Kvadrat rotiran za 45 stepeni, stranice s
    Predicate<Vector> rotatedSquare45(double s) {
        double h = s / Math.sqrt(2);
        return p -> false;
    }


    // Kruznica
    Predicate<Vector> circle(double radius, double thickness) {
        return p -> false;
    }


    // Grafik sinusoide
    Predicate<Vector> sineGraph() {
        return p -> false;
    }


    // Wavy stripe
    Predicate<Vector> wavyStripe() {
        return p -> false;
    }


    // Elipsa
    Predicate<Vector> ellipse(double a, double b) {
        return p -> false;
    }


    // Sahovska tabla - dimenzije polja dxd
    Predicate<Vector> chessboard(double d) {
        return p -> {



            return false;
        };
    }


    // Koncentricni krugovi
    Predicate<Vector> concentricCircles(double d) {
        return p -> {



            return false;
        };
    }


    // Srce
    Predicate<Vector> heart(double coefficient) {
        return p -> {



            return false;
        };
    }




    // Dodatni primeri

    // [*] 5-point flower
    Predicate<Vector> flower() {
        return p -> {
            double r = p.norm();
            double a = Math.atan2(p.y, p.x);
            return r <= 80 * Math.cos(5 * a);
        };
    }

    // [*] Perspective checkerboard
    Predicate<Vector> perspectiveCheckerboard(double d) {
        return p -> {
            double px = p.x * (d / (d / 2 - p.y));
            double py = d * (d / (d / 2 - p.y) - 1);
            return (Numeric.mod(px, 100) < 50)
                    ^ (Numeric.mod(py, 100) < 50);
        };
    }

    // [*] Kinder Bueno
    Predicate<Vector> kinderBueno() {
        return p -> (Math.cos(2 * Math.PI * p.x / 80) *
                Math.cos(2 * Math.PI * p.y / 80))  >
                Math.sin(time + (p.x + p.y) / 100);
    }

    @Override
    public void init(View view) {
        view.setImageSmoothing(false);
    }


    @Override
    public void draw(View view) {
        view.setTransformation(camera.getTransformation());
        DrawingUtils.clear(view, Color.gray(0.125));

        Image image = antialiasedShape(line45(2), 200, 200, subdivision);

//      Image image = antialiasedShape(halfPlane(0.173), 200, 200, subdivision);

//      Image image = antialiasedShape(rectangle(200, 100), 250, 250, subdivision);

//      Image image = antialiasedShape(rotatedSquare45(140), 200, 200, subdivision);

//		Image image = antialiasedShape(circle(100,3), 250, 250, subdivision);

//		Image image = antialiasedShape(sineGraph(), 300, 300, subdivision);

//      Image image = antialiasedShape(wavyStripe(), 300, 200, subdivision);

//      Image image = antialiasedShape(ellipse(100.0, 50.0), 300, 200, subdivision);

//      Image image = antialiasedShape(chessboard(20), 278, 278, subdivision);

//      Image image = antialiasedShape(concentricCircles(10), 278, 278, subdivision);

//      Image image = antialiasedShape(heart(80.0), 278, 278, subdivision);


//      Image image = antialiasedShape(flower(), 278, 278, subdivision);

//      Image image = antialiasedShape(perspectiveCheckerboard(283), 283, 283, subdivision);

//		Image image = antialiasedShape(kinderBueno(), 200, 200, subdivision);

        view.drawImageCentered(Vector.ZERO, image);
    }



    @Override
    public void receiveEvent(View view, InputEvent event, InputState state, Vector pointerWorld, Vector pointerViewBase) {
        camera.receiveEvent(view, event, state, pointerWorld, pointerViewBase);
    }



    public static void main(String[] args) {
        DrawingApplication.launch(800, 800);
    }


}
