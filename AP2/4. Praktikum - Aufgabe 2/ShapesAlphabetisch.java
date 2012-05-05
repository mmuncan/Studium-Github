import java.util.Comparator;

/**
 * <code>ShapesAlphabetisch</code> vergleicht die die Namen zweier
 * Shape-Objekte mittels compareTo für Strings.
 *
 * @author Dominik Schilling
 */
public class ShapesAlphabetisch implements Comparator<IShape> {

	/**
	 * Vergleicht die Namen zweier Objektnamen.
	 *
	 * @param obj1 Erstes Shape-Objekt
	 * @param obj2 Zweites Shape-Objekt
	 */
	@Override
	public int compare( IShape obj1, IShape obj2 ) {
		return obj1.getName().compareTo( obj2.getName() );
	}
}
