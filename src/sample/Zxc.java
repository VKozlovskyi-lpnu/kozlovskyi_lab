package sample;

public class Zxc {
    public static void main(String[] args) {
        String text = "RYW OGOY'WDEURPIZ";
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ .,;-'";
        String key = "KULPAMYKOLA";
        char[] charsTEXT = text.toUpperCase().toCharArray();
        char[] charsKEY = key.toUpperCase().toCharArray();
        char[] charsALPHABET = alphabet.toUpperCase().toCharArray();
        char[] data = new char[charsTEXT.length];
        for (int i = 0; i < charsTEXT.length; i++) {
            int i1 = alphabet.indexOf(charsTEXT[i]);
            int i2 = alphabet.indexOf(charsKEY[i % key.length()]);
//            int z = (i1 + i2) % alphabet.length();
            int y = (i1 - i2 + alphabet.length()) % alphabet.length();
//            data[i] = charsALPHABET[z];
            data[i] = charsALPHABET[y];
        }
        System.out.println(data);
    }
}

