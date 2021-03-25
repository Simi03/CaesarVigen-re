import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
// https://www.macronom.de/kryptographie/caesar/verschluesselung.php testing

/**
 * @author Simon Tobler
 * @version 25/02/2021
 */
public class Caesar {

    /*Verschlüsselung
     */
    public static void CaesarVerschluesseln() throws IOException {
        Scanner sc = new Scanner(System.in);
        //   System.out.println("Gebe den zu verschlüsselden Text ein");

        String inputText = new String(Files.readAllBytes(Paths.get("src/main/resources/testRead.txt")), StandardCharsets.UTF_8);

        StringBuilder outputText = new StringBuilder();

        System.out.println("Um wie viel soll der Text verschoben werden? Bitte nur natürliche Zahlen");
        String verschiebungsZahl = sc.nextLine();

        //Verschiebung
        int x = Integer.parseInt(verschiebungsZahl);
        for (int i = 0; i < inputText.length(); i++) {
            char zeichen = inputText.charAt(i);
            if (zeichen != ' ') { // Character.isLetter(zeichen) falls Fragezeichen etc. nicht verschlüsselt werden sollten
                // überprüft ob grossbuchstabe
                if (Character.isUpperCase(zeichen)) {
                    if (zeichen + x > 90) {
                        int rest = zeichen + x - 90; // 90 = Z
                        outputText.append((char) (64 + rest)); // 64 = 1 vor A
                    } else {
                        outputText.append((char) (zeichen + x));
                    }
                } else { // falls es ein klein Buchstaben ist
                    if (zeichen + x > 122) {
                        int rest = zeichen + x - 122; // 122 = z
                        outputText.append((char) (96 + rest)); // 96 = 1 vor a
                    } else {
                        outputText.append((char) (zeichen + x));
                    }
                }
            } else {
                outputText.append((char) (zeichen));
            }
        }
          System.out.println(outputText);
    }

    /*Entschlüsselung
     */
    public static void CaesarEntschluesseln() {
        Scanner sc = new Scanner(System.in);
        System.out.println("gebe den zu entschlüsselnden text ein");
        String inputText = sc.nextLine();
        StringBuilder outputText = new StringBuilder();

        System.out.println("Um wie viel soll der Text verschoben werden? Bitte nur natürliche Zahlen ");
        String verschiebungsZahl = sc.nextLine();

        //Verschiebung
        // x ist die Verschiebungslänge (x=3 d.h. a--> d)
        int x = Integer.parseInt(verschiebungsZahl);
        for (int i = 0; i < inputText.length(); i++) {
            char zeichen = inputText.charAt(i);
            if (zeichen != ' ') {// Character.isLetter(zeichen) falls Fragezeichen etc. nicht verschlüsselt werden sollten
                if (Character.isUpperCase(zeichen)) { // falls Grossbuchstabe
                    if (zeichen - x < 65) {
                        int rest = x + 65 - zeichen; // 65 = A
                        outputText.append((char) (91 - rest)); // 91 = 1 nach Z
                    } else {
                        outputText.append((char) (zeichen - x));
                    }
                } else { //falls kleinbuchstabe
                    if (zeichen - x < 97) {
                        int rest = x + 97 - zeichen; // 97 = a
                        outputText.append((char) (123 - rest)); // 123 = 1 nach z
                    } else {
                        outputText.append((char) (zeichen - x));
                    }
                }
            } else {
                outputText.append((char) (zeichen));
            }
        }
        System.out.println(outputText);

    }

    public static void main(String[] args) throws IOException {
        CaesarVerschluesseln();
        // CaesarEntschluesseln();
    }
}
