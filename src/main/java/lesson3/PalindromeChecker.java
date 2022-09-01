package lesson3;

public class PalindromeChecker {

    public boolean isPalindrome(String string) {
        String s1, s2;

        if (string == null || string.length() ==0 ) {
            throw new IllegalArgumentException("Строка не должна быть пустой");
        }

        String string1 = string.replaceAll("\\s","");

        if (string1 == null || string1.length() ==0 ) {
            return true;
        }

        int n = string1.length();
        for (int i = 0; i < (n/2); ++i) {
            s1 = String.valueOf(string1.charAt(i));
            s2 = String.valueOf(string1.charAt(n - i - 1));
            if (s1.equalsIgnoreCase(s2) == false) {
                return false;
            }
        }

        return true;
    }

}
