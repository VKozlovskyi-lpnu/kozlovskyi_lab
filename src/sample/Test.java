package sample;

public class Test {
    public static void main(String[] args) {
        Boolean isEncoded = false;
//        Boolean isEncoded = false;--шифруємо
//        Boolean isEncoded = true;-розшифровуємо
//        String text = "ED INTO MONUMENTS FROM THE OLD KINGDOM OF EGYPT CIRCA BC. THESE ARE NOT THOUGHT TO BE SERIOUS ATTEMPTS AT SECRET COMMUNICATIONS, HOWEVER, BUT RATHER TO HAVE WAS PROBABLY RELIGIOUSLY MOTIVATED TEXTUAL ANALYSIS OF THE QUR'AN WHICH LED TO THE INVENTION OF THE FREQUENCY ANALYSIS TECHNIQUE FOR BREAKING MONOALPHABETIC SUBSTITUTION CIPHERS, BY AL-KINDI, AN ARAB MATHEMATICIAN, SOMETIME AROUND AD. IT WAS THE MOST FUNDAMENTAL CRYPTANALYTIC ADVANCE UNTIL WWII. AL-KINDI WROTE A BOOK ON CRYPTOGRAPHY ENTITLED RISALAH FI ISTIKHRAJ AL-MU'AMMA MANUSCRIPT FOR THE DECIPHERING CRYPTOGRAPHIC MESSAGES, IN WHICH HE DESCRIBED THE FIRST CRYPTANALYSIS TEC";
//        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ .,;-'";
//        String key = "KULPAMYKOLA";
        String text = "Hello world again";
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ .,;-'";
        String key = "KULPAMYKOLA";
        text = text.toLowerCase();
        key = key.toLowerCase();
        alphabet = alphabet.toLowerCase();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            int textCharIndex = alphabet.indexOf(text.charAt(i));
            int keyCharIndex = alphabet.indexOf(key.charAt(i % key.length()));
            if (textCharIndex < 0 || keyCharIndex < 0) {
                builder.append(text.charAt(i));
                text = text.substring(0, i) + text.substring(i + 1);
                i--;
                continue;
            }
            int encodedCharIndex;
            if (isEncoded) {
                encodedCharIndex = (textCharIndex - keyCharIndex + alphabet.length()) % alphabet.length();
            } else {
                encodedCharIndex = (textCharIndex + keyCharIndex) % alphabet.length();
            }
            char encodedChar = alphabet.charAt(encodedCharIndex);
            builder.append(encodedChar);
        }
        System.out.println(text);
        System.out.println(builder);
    }
}
