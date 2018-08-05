package controller;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class AnagramGeneratorTest {

    @Test
    public void indexWordInputtedTest() {
        char[] chars = "testing".toCharArray();
        Arrays.sort(chars);
        assertEquals(new String(chars),"eginstt");
    }

    @Test
    public void reduceWordAccordingToKeyLettersTest() {
        String auxIndexedInputtedWord = "abecidofu";
        String key = "bcdf";
        for (char letter : key.toCharArray()) {
            auxIndexedInputtedWord = auxIndexedInputtedWord.replaceFirst(Character.toString(letter), "");
        }
        assertEquals(auxIndexedInputtedWord, "aeiou");
    }
    @Test
    public void getWordWithProperlyFormat() {
        String word = "  Test-ing ";
        word = word.toLowerCase();
        word = word.replace(" ", "");
        word = word.replaceAll("[^a-zA-Z]+", "");
        assertEquals(word,"testing");
    }
}
