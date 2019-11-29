package sample;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

class Impl {
    Map<Character, Integer> countLetters(String text) {
        Map<Character, Integer> code = new HashMap<>();
        char[] alx = text.toCharArray();
        for (char c : alx) {
            if (!code.containsKey(c)) {
                code.put(c, 0);
            }
        }
        System.out.println(code);
        for (char aChar : alx) {
            if (code.containsKey(aChar)) {
                Integer integer = code.get(aChar);
                integer++;
                code.put(aChar, integer);
            }
        }
        return code;
    }

    Map<String, Integer> countDiagrams(String text, int number, int elementSize) {
        String s = text.toUpperCase().replaceAll("[^A-Za-zА-Яа-я0-9]", " ");
        String yourString = s.replaceAll("\\s+", " ");
        String[] splited = yourString.split(" ");
        int z = 0;
        for (String value : splited) {
            if (value.length() >= number) {
                z++;
            }
        }
        String[] zxc = new String[z];
        int x = 0;
        for (String value : splited) {
            if (value.length() >= number) {
                zxc[x++] = value;
            }
        }
        Map<String, Integer> map = new HashMap<>();
        for (String str : zxc) {
            for (int j = 0, imax = str.length() - (number - 1); j < imax; j++) {
                String substring = str.substring(j, (j + number));
                if (map.containsKey(substring)) {
                    Integer integer = map.get(substring);
                    integer++;
                    map.put(substring, integer);
                } else {
                    map.put((str.substring(j, (j + number))), 1);
                }
            }
        }
        List<Map.Entry<String, Integer>> code = map.entrySet().stream()
                .sorted((o1, o2) -> o2.getValue() - o1.getValue())
                .collect(Collectors.toList())
                .subList(0, elementSize);
        return code.stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
    }

    String encryptCesar(String text, int count, String alphabetx) {
        char[] alphabet = alphabetx.toUpperCase().toCharArray();
        char[] code = text.toCharArray();
        for (int i = 0; i < code.length; i++) {
            for (int j = 0; j < alphabet.length; j++) {
                if (j >= count && code[i] == alphabet[j]) {
                    code[i] = alphabet[j - count];
                    break;
                }
                if (code[i] == alphabet[j] && j < count) {
                    code[i] = alphabet[(alphabet.length - count) + j];
                    break;
                }
            }
        }
        return String.valueOf(code);
    }

    String decryptCesar(String text, int count, String alphabetx) {
        char[] alphabet = alphabetx.toUpperCase().toCharArray();
        char[] code = text.toCharArray();
        for (int i = 0; i < code.length; i++) {
            for (int j = 0; j < alphabet.length; j++) {
                if (j < alphabet.length - count) {
                    if (code[i] == alphabet[j]) {
                        code[i] = alphabet[j + count];
                        break;
                    }
                } else if (code[i] == alphabet[j]) {
                    code[i] = alphabet[j - (alphabet.length - count)];
                }
            }
        }
        return String.valueOf(code);
    }

//ALPHABET
//ABCDEFGHIJKLMNOPQRSTUVWXYZ .,;-'
//KEY
//QWERTYUIOPASDFGHJKLZXCVBNM .,;-'

    String encryptMP(String text, String alphabetx, String key) {
        char[] charsTEXT = text.toUpperCase().toCharArray();
        char[] charsALPA = alphabetx.toUpperCase().toCharArray();
        char[] charsKEY = key.toUpperCase().toCharArray();
        char[] code = new char[text.length()];
        for (int i = 0; i < charsTEXT.length; i++) {
            for (int j = 0; j < charsALPA.length; j++) {
                if (charsTEXT[i] == charsALPA[j]) {
                    code[i] = charsKEY[j];
                }
            }
        }
        return String.valueOf(code);
    }

    String decryptMP(String text, String alphabetx, String key) {
        char[] charsTEXT = text.toUpperCase().toCharArray();
        char[] charsALPA = alphabetx.toUpperCase().toCharArray();
        char[] charsKEY = key.toUpperCase().toCharArray();
        char[] code = new char[text.length()];
        for (int i = 0; i < charsTEXT.length; i++) {
            for (int j = 0; j < charsKEY.length; j++) {
                if (charsTEXT[i] == charsKEY[j]) {
                    code[i] = charsALPA[j];
                }
            }
        }
        return String.valueOf(code);
    }

    String encryptVigenere(String text, String alphabetx, String key) {
        char[] charsTEXT = text.toUpperCase().toCharArray();
        char[] charsALPHABET = alphabetx.toUpperCase().toCharArray();
        char[] charsKEY = key.toUpperCase().toCharArray();
        char[] code = new char[charsTEXT.length];
        for (int i = 0; i < charsTEXT.length; i++) {
            int keyAlphabet = alphabetx.indexOf(charsTEXT[i]);
            int keyCipere = alphabetx.indexOf(charsKEY[i % key.length()]);
            int result = (keyAlphabet + keyCipere) % alphabetx.length();
            code[i] = charsALPHABET[result];
        }
        return String.valueOf(code);
    }

    String decryptVigenere(String text, String alphabetx, String key) {
        char[] charsTEXT = text.toUpperCase().toCharArray();
        char[] charsALPHABET = alphabetx.toUpperCase().toCharArray();
        char[] charsKEY = key.toUpperCase().toCharArray();
        char[] code = new char[charsTEXT.length];
        for (int i = 0; i < charsTEXT.length; i++) {
            int keyAlphabet = alphabetx.indexOf(charsTEXT[i]);
            int keyCipere = alphabetx.indexOf(charsKEY[i % key.length()]);
            int result = (keyAlphabet - keyCipere + alphabetx.length()) % alphabetx.length();
            code[i] = charsALPHABET[result];
        }
        return String.valueOf(code);
    }


}
