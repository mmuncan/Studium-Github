package ui;
import book.Addressbook;

/**
 * Main-Klasse für eine einfache Adressbuchanwendung im
 */
final class Main {
	private Main() { }

	public static void main(String[] args) {
		new UserInterface(new Addressbook("file.dat"));
	}
}
