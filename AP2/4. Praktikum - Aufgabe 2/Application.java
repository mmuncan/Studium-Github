import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Application {
    private List<IShape> liste = new ArrayList<IShape>();

    /**
     * Erzeugt ein Array von IShape-Objekten, entsprechend der
     * Eingabe.
     *
     * @return Array mit IShape-Objekten
     */
    public void start() {
        Menu menu = new Menu("Auswahl der Klasse");
        menu.add("Rechteck erzeugen", 'r', new Command() {
            public void execute() {
                String name = Console.readLine("Name:");
                double laenge = Console.readDouble("Laenge:");
                double breite = Console.readDouble("Breite:");
                liste.add(new Rectangle(name, laenge, breite));           
            }
        });
        menu.add("Kreis erzeugen", 'k', new Command() {
            public void execute() {
                String name = Console.readLine("Name:");
                double radius = Console.readDouble("Radius:");
                liste.add(new Circle(name, radius));
            }
        });
        menu.add("nach Fläche sortiert ausgeben", 'f', new Command() {
        	public void execute() {
        		Collections.sort(liste);
        		printList("nach Fläche sortiert: ");
        	}
        });
        menu.add("nach Name sortiert ausgeben", 'n', new Command() {
        	public void execute() {
                Collections.sort(liste, new ShapesAlphabetisch());
                printList("nach Name sortiert: ");
        	}
        });
        menu.add("Gesamtfläche ausgeben", 'g', new Command() {
        	public void execute() {
                double summe = 0.0;
                for (IShape f : liste)
                    summe += f.getArea();  
                System.out.printf("Gesamte Flaeche: %.2f%n", summe);
        	}
        });
        menu.add("Ende", 'e', null);
        menu.runloop();             
    }

	/**
	 * Gibt alle Daten eines IShape-Feldes aus.
	 * 
	 * @param title Ueberschrift über die Ausgabe.
	 */
	private void printList(String title) {
	    System.out.println("\n" + title);
	    for (IShape s : liste)
	        System.out.printf("    %s, Flaeche: %.2f%n", s, s.getArea());
	}
}
