package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import objects.AbstractWorld;

/**
 * Stellt den Zustand des Spiels dar.
 */
public class Panel extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final int SCALE = 5;
    private static final int WIDTH = 5;
    private int nx;
    private int ny;
    private AbstractWorld thisWorld;
    
    /**
     * Stellt die Arena des Game of Life dar.
     * @param thisWorld Verweis auf das logische Spielfeld
     */
    public Panel(AbstractWorld thisWorld) {
        this.thisWorld = thisWorld;
        nx = thisWorld.getNX();
        ny = thisWorld.getNY();
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(nx*SCALE+1, ny*SCALE+1));
    }
    
    @Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	drawGrid(g);
    	g.setColor(Color.DARK_GRAY);
    	for (int i = 0; i < nx; i++)
    		for (int j = 0; j < ny; j++) {
    			if (thisWorld.isBlack(i, j)) {
    				g.fillRect(i*SCALE+1, j*SCALE+1, WIDTH, WIDTH);
    			}
    		}
    }

	private void drawGrid(Graphics g) {
		g.setColor(Color.black);
    	for (int i = 0; i <= SCALE * nx; i += SCALE)
    		g.drawLine(i, 0, i, SCALE * ny);
    	for (int j = 0; j <= SCALE * ny; j += SCALE)
    		g.drawLine(0, j, SCALE * nx, j);
	}
}
