package topic7_animation;

import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetAnimation;
import mars.geometry.Vector;

public class BallAndBox implements Drawing {
	
	double dt0 = 1.0;
	double dt1 = 2.0;
	double dt2 = 1.0;
	
	double time0 = 0.0;
	double time1 = time0 + dt0;
	double time2 = time1 + dt1;
	double time3 = time2 + dt2;
	
	
	@GadgetAnimation
	double time = 0.0;
	
	
	double smoothstep(double t) {
		return t * t * (3 - 2 * t);
	}
	
	double tBall(double t) {
		if (t < time1) return 0;
		if (t < time2) return smoothstep((t - time1) / dt1);
		return 1;
	}
	
	
	double tBox(double t) {
		if (t < time0) return 0;
		if (t < time1) return smoothstep((t - time0) / dt0);
		if (t < time2) return 1;
		if (t < time3) return smoothstep((time3 - t) / dt2);
		return 0;
	}
	
	
	void drawBall(View view, double t) {
		Vector p0 = new Vector(0, 150);
		Vector p1 = new Vector(0, -50);
		
		Vector p = Vector.lerp(p0, p1, t);
		view.setFill(Color.hsb(0, 0.6, 1.0));
		view.fillCircleCentered(p, 40);
	}
	
	
	void drawBox(View view, double t) {
		double phi = t * 0.3;
		
		Vector a = new Vector(50, 0);
		Vector b = new Vector(50, -100);
		Vector c = new Vector(-50, -100);
		Vector d = new Vector(-50, 0);
		Vector e = Vector.polar(100, phi).add(d);
		
		view.setFill(Color.hsb(120, 0.5, 0.5));
		view.setStroke(Color.hsb(120, 0.5, 1.0));
		
		view.setLineWidth(4);
		view.setLineJoin(StrokeLineJoin.ROUND);
		view.setLineCap(StrokeLineCap.ROUND);
		
		view.fillPolygon(a, b, c, d);
		view.strokePolyline(a, b, c, d, e);
	}
	
	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.gray(0.125));
	
		drawBall(view, tBall(time));
		drawBox(view, tBox(time));
	}
	
	static void main() {
		DrawingApplication.launch(800, 800);
	}
}
