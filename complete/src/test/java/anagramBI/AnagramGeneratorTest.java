package anagramBI;

import org.junit.Test;

import java.util.*;

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

    @Test
    public void testAddingAnagramsToList() {
        Map<String, List<String>> indexedDictionary = new HashMap<String, List<String>>();
        Map<String, List<String>> auxIndexedDictionary = new HashMap<String, List<String>>();
        List<String> listIndex = new LinkedList<>();
        List<String> listAux = new LinkedList<>();
        listIndex.add("aaa");
        listAux.add("bbb");
        indexedDictionary.put("aaa", listIndex);
        String auxIndexedInputtedWord = "bbb";
        auxIndexedDictionary.put("bbb", listAux);
        List<String> listAnagrams = new LinkedList<>();
        if (auxIndexedDictionary.get(auxIndexedInputtedWord) != null) {
            List<String> listA = indexedDictionary.get("aaa");
            List<String> listB = auxIndexedDictionary.get(auxIndexedInputtedWord);
            for (int i = 0; i < listA.size(); i++) {
                for (int j = 0; j < listB.size(); j++) {
                    listAnagrams.add(listA.get(i) + " " + listB.get(j));
                    listB.remove(j);
                }
            }
        }
        assertEquals(listAnagrams.get(0), "aaa bbb");
    }
}
