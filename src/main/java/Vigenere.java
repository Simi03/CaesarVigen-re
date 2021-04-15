import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Vigenere {

    public static char[] crypt(char[] plain, char[] key, int direction) {

        char[] output = new char[plain.length];
        for (int i = 0; i < plain.length; i++) {
            //Verschluesseln
            if (direction == 1) {
                //Umrechnen für ASCII Tabelle
                int result = (plain[i] + key[i % key.length]-194) % 26 ;
                output[i] = (char) (result+97);
            }
            //Entschluesseln
            else if (direction == 0){
                int result;
                if (plain[i] - key[i % key.length] < 0)
                {
                    result = (plain[i]- key[i % key.length]+130) %26 + 97;
                }
                else
                {
                    result = (plain[i] - key[i % key.length]) % 26 +97;
                }
                output[i] = (char) result;
            }
        }
        return output;
    }
    public static void createWriteFileEncrypt(String outputText) {
        // File erstellen
        try {
            File myObj = new File("src/main/resources/Chiffrat.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            //File schreiben
        }
        try {
            FileWriter myWriter = new FileWriter("src/main/resources/Chiffrat.txt");
            myWriter.write(String.valueOf(outputText));
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException io) {
            System.out.println("An error occurred.");
            io.printStackTrace();
        }

    }

    public static void createWriteFileDecrypt(String outputText) {
        // File erstellen
        try {
            File myObj = new File("src/main/resources/DecryptedChiffrat.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            //File schreiben
        }
        try {
            FileWriter myWriter = new FileWriter("src/main/resources/DecryptedChiffrat.txt");
            myWriter.write(String.valueOf(outputText));
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException io) {
            System.out.println("An error occurred.");
            io.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String plaintext = new String(Files.readAllBytes(Paths.get("src/main/resources/Klartext.txt")), StandardCharsets.UTF_8);
        System.out.println("Klartext: " + plaintext);
        char[] plain = plaintext.toLowerCase().toCharArray();

        System.out.println("Schluesselwort eingeben:");
        String keyword = scanner.nextLine();
        keyword = keyword.toLowerCase();
        char[] key = keyword.toCharArray();

        char[] encrypted = crypt(plain, key, 1);
        System.out.println("Verschluesselter Text:");
        System.out.println(encrypted);
        createWriteFileEncrypt(String.valueOf(encrypted));

        char[] decrypted = crypt(encrypted, key, 0);
        System.out.println("Entschluesselter Text:");
        System.out.println(decrypted);
        createWriteFileDecrypt(String.valueOf(decrypted));
        scanner.close();
    }
}