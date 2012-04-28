package ui;
import book.Addressbook;

/**
 * Main-Klasse f�r eine einfache Adressbuchanwendung im
 */
final class Main {
	private Main() { }

	public static void main(String[] args) {
		new UserInterface(new Addressbook("file.dat"));
	}
}
