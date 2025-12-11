package topic2_image_processing.filters.color;

import javafx.scene.paint.Color;
import topic2_image_processing.filters.ColorFilter;

/**
 * Akcentuje boju ciji je hue zadat u parametru konstruktora filtera. Sve boje koje imaju hue blizu zadatog zadrzava
 * neizmenjene, dok sve ostale desaturira u sivu.
 */
public class Accent extends ColorFilter {
	final double accentHue;      // Koji hue akcentujemo
	final double delta = 30;     // Koliko odstupanje dozvoljavamo
	
	
	public Accent(double hue) {
		this.accentHue = hue;
	}


	@Override
	public Color processColor(Color input) {
		double dHue = Math.abs(input.getHue() - accentHue);  // Racunamo razliku izmedju dva hue-a.
		if (dHue > 180) {                                      // Ako je razlika veca od 180 stepeni, onda je blize "ici na drugu stranu".
			dHue = 360 - dHue;
		}
		
		if (dHue < delta) {       // Ukoliko je razlika manja od granicne, zadrzavamo originalnu boju
			return input;
		} else {                  // U suprotnom desaturiramo originalnu boju
			return Color.hsb(input.getHue(), 0, input.getBrightness(), input.getOpacity());
		}
	}
	
}