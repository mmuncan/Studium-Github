import java.util.Scanner;

/**
 * Die Klasse erledigt die Tastatureingabe.
 */
public final class Console {
    private final static Scanner in = new Scanner(System.in);

    /**
     * Liest einen String von der Tastatur ein.
     * Zunaechst wird der uebergebene Prompt ausgegeben. Dann
     * wird eine komplette, durch Zeilenende abgeschlossene
     * Zeile eingelesen und zurueckgegeben.
     * 
     * @param prompt Eingabeaufforderung
     * @return eingelesene Zeile
     */
    public static String readLine(String prompt) {
        System.out.print(prompt + " ");
        return in.nextLine();
    }

    /**
     * Liest eine Gleitkommazahl von der Tastatur ein.
     * Zunaechst wird der uebergebene Prompt ausgegeben. Dann
     * wird eine komplette, durch Zeilenende abgeschlossene Zeile
     * eingelesen. Dieser String wird muss eine gueltige Gleitkommazahl
     * sein. Ihr Wert wird zurueckgegeben.
     * <p>
     * Bei fehlerhafter Eingabe wird eine erneute Eingabe angefordert.
     * 
     * @param prompt Eingabeaufforderung
     * @return Wert der eingegebenen Zahl
     */
    public static double readDouble(String prompt) {
        for (;;) {
            String input = readLine(prompt);
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.err.println("keine gueltige Gleitkommazahl");
            }
        }
    }

}
