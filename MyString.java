public class MyString {
    public static void main(String args[]) {
        String hello = "hsdfsdo";
        System.out.println(countChar(hello, 'h'));
        System.out.println(countChar(hello, 'l'));
        System.out.println(countChar(hello, 'z'));
        System.out.println(spacedString(hello));
        System.out.println(subsetOf("sap", "space"));
        System.out.println(spacedString("silt"));
        System.out.println(randomStringOfLetters(3));
        System.out.println(remove("meet", "committee"));
    }

    public static int countChar(String str, char ch) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ch) {
                count++;
            }
        }
        return count;
    }

    public static boolean subsetOf(String str1, String str2) {
        for (int i = 0; i < str1.length(); i++) {
            if (countChar(str2, str1.charAt(i)) < countChar(str1, str1.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static String spacedString(String str) {
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            result += str.charAt(i);
            if (i < str.length() - 1) {
                result += " ";
            }
        }
        return result;
    }

    public static String randomStringOfLetters(int n) {
        String result = "";
        for (int i = 0; i < n; i++) {
            char randomChar = (char) ('a' + (int) (Math.random() * 26));
            result += randomChar;
        }
        return result;
    }

    public static String remove(String str1, String str2) {
        String result = str1;
        for (int i = 0; i < str2.length(); i++) {
            int index = result.indexOf(str2.charAt(i));
            if (index != -1) {
                result = result.substring(0, index) + result.substring(index + 1);
            }
        }
        return result;
    }

    public static String insertRandomly(char ch, String str) {
        int randomIndex = (int) (Math.random() * (str.length() + 1));
        return str.substring(0, randomIndex) + ch + str.substring(randomIndex);
    }
}
