/**
 * Circle ist eine konkrete Shape-Klasse.
 *
 * @author Dominik Schilling
 */
public final class Circle extends AbstractShape {
	/**
	 * Speichert den Radius des Kreises.
	 */
	private double radius;

	/**
	 * Konstruktor.
	 *
	 * @param name  Name des Kreisobjekts.
	 * @param radius Radius des Kreises.
	 */
	public Circle( String name, double radius ) {
		super( name );
		this.radius = radius;
	}

	/**
	 * Berechnet die Fläche eines Kreies.
	 * @see IShape#getArea()
	 *
	 * @return Fläche des Kreises
	 */
	public double getArea() {
		return Math.PI * radius * radius;
	}

	/**
	 * Ausgabe des Objektnamens.
	 * @see IShape#toString()
	 *
	 * @return Rectangle. + der Namen des Rechtecks
	 */
	public String toString() {
		return "Circle." + getName();
	}
}
