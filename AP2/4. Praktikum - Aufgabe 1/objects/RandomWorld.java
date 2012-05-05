package objects;

import java.util.Random;

/**
 * Ein Feld mit zufaelliger Anfangsbelegung.
 */
class RandomWorld extends AbstractWorld {
	RandomWorld(int nx, int ny) {
		super(nx, ny);
		Random rgen = new Random();
		for (int i = 0; i < nx; i++) 
			for (int j = 0; j < ny; j++) 
				if (rgen.nextInt(7) == 0) set(i, j, BlackToken.instance());
	}
}
