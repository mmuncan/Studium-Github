/**
 * Rectangle ist eine konkrete Shape-Klasse.
 */
public final class Rectangle extends AbstractShape {
	/**
	 * Speichert die L�nge und Breite des Rechtecks.
	 */
	private double length, width;

	/**
	 * Konstruktor.
	 *
	 * @param name  Name des Rechteckobjekts.
	 * @param length L�nge des Rechtecks.
	 * @param width Breite des Rechtecks.
	 */
	public Rectangle( String name, double length, double width ) {
		super(name);
		this.length = length;
		this.width = width;
	}

	/**
	 * Berechnet die Fl�che eines Rechtecks.
	 * @see IShape#getArea()
	 *
	 * @return Fl�che des Rechtecks
	 */
	public double getArea() {
		return length * width;
	}

	/**
	 * Ausgabe des Objektnamens.
	 * @see IShape#toString()
	 *
	 * @return Rectangle. + der Namen des Rechtecks
	 */
	public String toString() {
		return "Rectangle." + getName();
	}
}
