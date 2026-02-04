package extra.green_blobs_from_outer_space.full;

import javafx.scene.paint.Color;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;


public class Background {
	
	private BackgroundParticleSystem ps = new BackgroundParticleSystem();
	

	public void draw(View view, double time) {
		DrawingUtils.clear(view, Color.hsb(0, 0, 0.1));
		ps.draw(view, time);
	}
	
	
	public void update(double time) {
		ps.updateInterval(time);
	}
}
