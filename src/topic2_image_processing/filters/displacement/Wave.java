package topic2_image_processing.filters.displacement;

import mars.geometry.Vector;
import topic2_image_processing.filters.DisplacementFilter;


/**
 * Pravi sliku talasastom pomerajuci kolone piksela gore-dole u skladu sa talasnom funkcijom zadate
 * amplitude i talasne dužine.
 */
public class Wave extends DisplacementFilter {
	final double amplitude, wavelength;
	
	
	public Wave(double amplitude, double wavelength) {
		this.amplitude = amplitude;
		this.wavelength = wavelength;
	}


	@Override
	public Vector source(Vector dst, Vector dim) {
		// Na y koordinatu dst pozicije dodajemo pomeraj po x osi koji nam zavisi od x koordinate, x koordinata ostaje nepromenjena.
		// Pomeraj po y je sinusna funkcija, samo joj parametar moramo skalirati da perioda bude wavelength umesto 2*pi.
		return new Vector (dst.x, dst.y + amplitude * Math.sin(dst.x / wavelength * 2 * Math.PI));
	}
	
}
