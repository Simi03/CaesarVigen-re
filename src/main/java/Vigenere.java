import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author Simon Lindemann
 * @version 08/04/2021
 */
public class Vigenere {
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

    // This function generates the key in
    // a cyclic manner until it's length isi'nt
    // equal to the length of original text
    static String generateKey(String str, String key)
    {
        int x = str.length();

        for (int i = 0; ; i++)
        {
            if (x == i)
                i = 0;
            if (key.length() == str.length())
                break;
            key+=(key.charAt(i));
        }
        return key;
    }

    // This function returns the encrypted text
// generated with the help of the key
    static String cipherText(String str, String key)
    {
        String cipher_text="";

        for (int i = 0; i < str.length(); i++)
        {
            // converting in range 0-25
            int x = (str.charAt(i) + key.charAt(i)) %26;

            // convert into alphabets(ASCII)
            x += 'A';

            cipher_text+=(char)(x);
        }
        return cipher_text;
    }

    // This function decrypts the encrypted text
// and returns the original text
    static String originalText(String cipher_text, String key)
    {
        String orig_text="";

        for (int i = 0 ; i < cipher_text.length() &&
                i < key.length(); i++)
        {
            // converting in range 0-25
            int x = (cipher_text.charAt(i) -
                    key.charAt(i) + 26) %26;

            // convert into alphabets(ASCII)
            x += 'A';
            orig_text+=(char)(x);
        }
        return orig_text;
    }

    public static void main(String[] args) throws IOException {
        String inputText = new String(Files.readAllBytes(Paths.get("src/main/resources/Klartext.txt")), StandardCharsets.UTF_8);
        String keyword = "SHEEESH";

        String key = generateKey(inputText, keyword);
        String cipher_text = (cipherText(inputText,key));
        createWriteFileEncrypt(cipher_text);
        createWriteFileDecrypt(originalText(cipher_text,key));

        System.out.println("Ciphertext : "
                + cipher_text + "\n");

        System.out.println("Original/Decrypted Text : "
                + originalText(cipher_text, key));
    }

}
