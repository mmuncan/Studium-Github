import java.util.ArrayList;
import java.util.List;

/**
 * Einfache Menuestruktur.
 * Die Klasse erlaubt die Definition eines Menues und die Auswahl und
 * Ausfuehrung der Menueaktion.
 * 
 * @author Erich Ehses
 */
public class Menu {
    private static class Item {
        String name;
        char key;
        Command action;

        /**
         * Speichert eine Menuezeile zusammen mit der auszuloesenden Aktion.
         * 
         * @param name lesbarer Name der Menuezeile
         * @param key einzugebender Buchstabe
         * @param action auszufuehrende Aktion
         */
        Item(String name, char key, Command action) {
            this.name = name;
            this.key = key;
            this.action = action;
        }
        
        @Override
        public String toString() {
            return key + " - " + name;
        }
    }

    private String title;
    private List<Item> items = new ArrayList<Item>();
    private boolean allDone;

    /**
     * Erzeugt ein neues Menue.
     * 
     * @param title Titelzeile
     */
    public Menu(String title) {
        this.title = title;
    }

    /**
     * Definiert eine (weitere) Menuezeile. Wenn das <code>action</code>
     * gleich <code>null</code> ist wird bei Auswahl dieses
     * Punktes anstelle einer Aktion die Menue-Schleife beendet.
     * 
     * @param name Beschreibung der Auswahl
     * @param key Kuerzel fuer die Auswahl (ein Buchstabe!)
     * @param action Aktionsobjekt
     * @see runnloop
     */
    public void add(String name, char key, Command action) {
        items.add(new Item(name, Character.toLowerCase(key), action));
    }

    /**
     * Stellt ein Menue dar und fuehrt die gewaehlten Aktionen aus.
     * Zunaechst wird der Menuetext auf den Bildschirm geschrieben.
     * Anschliessend wird auf eine mit Return abgeschlossene Zeile gewartet, die
     * genau ein Zeichen enthaelt. Wenn definiert, wird die zu diesem Zeichen
     * gehoerende Aktion ausgefuehrt (Ansonsten wird nachgefragt). Wenn die
     * keine Aktion fuer den gewaehlten Item definiert ist,
     * wird die Menueabfrage beendet. Anderfalls wird erneut abgefragt.
     */
    public void runloop() {
        allDone = false;
        while (!allDone) {
            printList();
            String input = Console.readLine(": ");
            if (input.length() != 1)
                System.err.println("nur 1 Buchstabe bitte");
            else {
                char c = Character.toLowerCase(input.charAt(0));
                perform(c);
            }
        }
    }

    /**
     * Gibt die Menuepunkte aus.
     */
    private void printList() {
        System.out.println();
        System.out.println(title);
        for (Item item : items)
            System.out.println(item);
    }

    /**
     * Fuehrt die ausgewaehlte Aktion aus.
     * 
     * @param eingegebenerBuchstabe Auswahlzeichen
     * @return true wenn die Menueschleife beendet werden soll.
     */
    private void perform(char eingegebenerBuchstabe) {
        for (Item item : items) {
            if (item.key == eingegebenerBuchstabe) {
                if (item.action != null) 
                    item.action.execute();
                else
                    allDone = true;
                return;
            }
        }
        System.out.println("illegaler Buchstabe");
    }
}