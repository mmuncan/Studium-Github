package objects;

/**
 * Abstrakte Oberklasse fuer das Feld aller Tokens.
 */
public abstract class AbstractWorld {
	private Token[][] field;
	private final int nx;
	private final int ny;

	/**
	 * Erzeugt ein neues Feld. Die Feldkoordinaten sind (0..nx-1, 0..ny-1).
	 * Fuer die Nachbarschaftsberechnung wird als Topologie ein Torus verwendet.
	 * @param nx Anzahl der Zellen in x-Richtung
	 * @param ny Anzahl der Zellen in y-Richtung
	 * @param perRandom wenn <tt>true</tt> wird der Inhalt durch Zufall erzeugt.
	 * @return neues Feld
	 */
	public static AbstractWorld createWorld(int nx, int ny, boolean perRandom) {
		return perRandom ?
				new RandomWorld(nx, ny) :
				new R_Pentomino(nx, ny);
	}

	/**
	 * Initialisiert ein Objekt.
	 * @param nx Anzahl der x-Zellen
	 * @param ny Anzahl der y-Zellen
	 */
	protected AbstractWorld(int nx, int ny) {
		this.nx = nx;
		this.ny = ny;
		this.field = new Token[nx][ny];
		for (int i = 0; i < getNX(); i++)
			for (int j = 0; j < getNY(); j++)
				field[i][j] = WhiteToken.instance();
		for (int i = 0; i < dx.length; i++) {
			dx[i] += nx;
			dy[i] += ny;
		}
	}

	private static final int[] dx = {0,1,1, 1, 0,-1,-1,-1};
	private static final int[] dy = {1,1,0,-1,-1,-1, 0, 1};

	/**
	 * Ermittelt ein Array mit allen Nachbarn einer Zelle.
	 * @param x x-Koordinate
	 * @param y y-Koordinate
	 * @return Array mit Nachbarobjekten
	 */
	Token[] neighbours(int x, int y) {
		Token[] n = new Token[dx.length];
		for (int i = 0; i < dx.length; i++)
			n[i] = field[(x + dx[i]) % nx][(y + dy[i]) % ny];
		return n;
	}

	/**
	 * Ermittelt anhand der Spielregeln eine neue Belegung des Feldes. Alle
	 * Operationen laufen (quasi) gleichzeitig ab.
	 */
	public void computeNextGeneration() {
		Token[][] nField = new Token[nx][ny];
		for (int i = 0; i < nx; i++)
			for (int j= 0; j < ny; j++)
				nField[i][j] = field[i][j].nextGeneration(neighbours(i, j));
		field = nField;
	}

	/**
	 * Gibt die Groesse in x-Richtung zurueck.
	 * @return Anzahl der Zellen in x-Richtung
	 */
	public int getNX() {
		return nx;
	}

	/**
	 * Gibt die Groesse in y-Richtung zurueck.
	 * @return Anzahl der Zellen in y-Richtung
	 */
	public int getNY() {
		return ny;
	}

	/**
	 * Das Feld an der Stelle x,y ist schwarz.
	 * @param x x-Position
	 * @param y y-Position
	 * @return <tt>true</tt> wenn die Zelle schwarz ist.
	 */
	public boolean isBlack(int x, int y) {
		return field[x][y].isBlack();
	}

	protected void set(int x, int y, Token t) {
		field[x][y] = t;
	}
}
