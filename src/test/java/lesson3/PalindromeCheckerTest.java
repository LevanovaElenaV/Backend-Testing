package lesson3;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PalindromeCheckerTest {
    private static PalindromeChecker palindromeChecker;

    @BeforeAll
    static void beforeAll() {
        palindromeChecker = new PalindromeChecker();
    }

    @Test
    @DisplayName("null сценарий")
    void test00() {
        assertThatThrownBy(() -> palindromeChecker.isPalindrome(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Строка не должна быть пустой");
    }

    @Test
    @DisplayName("пробел сценарий")
    void test0() {
        Assertions.assertThat(palindromeChecker.isPalindrome("      ")).isTrue();
    }

    @Test
    @DisplayName("/n сценарий")
    void test0n() {
        Assertions.assertThat(palindromeChecker.isPalindrome("      \n")).isTrue();
    }

    @Test
    @DisplayName("/n сценарий неверный")
    void test0nFalse() {
        Assertions.assertThat(palindromeChecker.isPalindrome("      /n")).isFalse();
    }

    @Test
    @DisplayName("abba сценарий")
    void test1() {
        Assertions.assertThat(palindromeChecker.isPalindrome("abba")).isTrue();
    }

    @Test
    @DisplayName("aba сценарий")
    void test2() {
        Assertions.assertThat(palindromeChecker.isPalindrome("aba")).isTrue();
    }

    @Test
    @DisplayName("a  aab a aa сценарий")
    void test3() {
        Assertions.assertThat(palindromeChecker.isPalindrome("a  aab a aa")).isTrue();
    }

    @Test
    @DisplayName("a  aab a a сценарий")
    void test4() {
        Assertions.assertThat(palindromeChecker.isPalindrome("a  aab a a")).isFalse();
    }
    @Test
    @DisplayName("abbc сценарий")
    void test5() {
        Assertions.assertThat(palindromeChecker.isPalindrome("abbc")).isFalse();
    }

    @Test
    @DisplayName("Ab bB a сценарий")
    void test6() {
        Assertions.assertThat(palindromeChecker.isPalindrome("Ab bB a")).isTrue();
    }



}
