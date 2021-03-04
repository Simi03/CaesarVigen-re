import java.util.Scanner;

/**
 * @author Simon Tobler
 * @version 25/02/2021
 */
public class Caesar {
    //Verschlüsselung
    public static void CaesarVerschluesseln() {
        Scanner sc = new Scanner(System.in);
        System.out.println("gebe den zu verschlüsselden text ein");
        String inputText = sc.nextLine();
        StringBuilder outputText = new StringBuilder();

        System.out.println("Gebe die Anzahl ein um die Buchstaben zu verschlüsseln");
        String verschiebungsZahl = sc.nextLine();

        //Verschiebung
        int x = Integer.parseInt(verschiebungsZahl);
        for (int i = 0; i < inputText.length(); i++) {
            char zeichen = inputText.charAt(i);
            if (zeichen != ' ') {
                if (zeichen + x > 122) {
                    int rest = zeichen + x - 122; // 122 = z
                    outputText.append((char) (96 + rest)); // 96 = 1 vor a
                } else {
                    outputText.append((char) (zeichen + x));
                }
            } else {
                outputText.append((char) (' '));
            }
        }
        System.out.println(outputText);

    }

    //Entschlüsselung
    public static void CaesarEntschluesseln(){
        Scanner sc = new Scanner(System.in);
        System.out.println("gebe den zu entschlüsselnden text ein");
        String inputText = sc.nextLine();
        StringBuilder outputText = new StringBuilder();

        System.out.println("Gebe die Anzahl ein um die Buchstaben wieder zurückzuverschieben");
        String verschiebungsZahl = sc.nextLine();

        //Verschiebung
        int x = Integer.parseInt(verschiebungsZahl);
        for (int i = 0; i < inputText.length(); i++) {
            char zeichen = inputText.charAt(i);
            if (zeichen != ' ') {
                if (zeichen - x < 97) {
                    int rest = x + 97 - zeichen; // 97 = a
                    outputText.append((char) (123 - rest)); // 123 = 1 nach z
                } else {
                    outputText.append((char) (zeichen - x));
                }
            } else {
                outputText.append((char) (' '));
            }
        }
        System.out.println(outputText);

    }

    public static void main(String[] args) {
        //CaesarVerschluesseln();
        //CaesarEntschluesseln();
    }
}
