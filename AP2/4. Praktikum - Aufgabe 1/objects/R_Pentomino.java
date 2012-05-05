package objects;

/**
 * Ein Feld mit der Anfangsbelegung
 * <pre>
 *     xx
 *    xx
 *     x
 * </pre>
 */
class R_Pentomino extends AbstractWorld {
	R_Pentomino(int nx, int ny) {
		super(nx, ny);
		set(75, 75, BlackToken.instance());
		set(76, 75, BlackToken.instance());
		set(74, 74, BlackToken.instance());
		set(75, 74, BlackToken.instance());
		set(75, 73, BlackToken.instance());
	}
}
