/**
 * Zum Ausprobieren von Vererbung. <code>AbstractShape</code> ist eine
 * Modellklasse für geometrische Objekte, die der Schnittstelle
 * <code>IShape</code> entsprechen. <code>AbstractShape</code> ist eine
 * abstrakte Klasse, weil die Methode <code>getArea()</code> nicht
 * implementiert ist.
 * <p>
 * Konsequenz: es gibt keine Instanzen von AbstractShape, d.h.
 * <code>new AbstractShape("xyz")</code> ist nicht erlaubt und
 * <code>Rectangle</code> und <code>Circle</code> _muessen_ ein richtiges
 * <code>getArea()</code> haben.
 */

public abstract class AbstractShape implements IShape {
    private String name;

    /**
     * Konstruktor. Speichert den Namen des Objekts.
     * 
     * @param name Objektname.
     */
    public AbstractShape(String name) {
        this.name = name;
    }

    /**
     * Gibt den Objektnamen als String zurueck.
     * 
     * @return Objektname.
     */
    public final String getName() {
        return name;
    }

    /*
     * @see IShape#compareTo(IShape)
     */
    public final int compareTo(IShape s) {
        return (int) Math.signum(getArea() - s.getArea());
    }

}