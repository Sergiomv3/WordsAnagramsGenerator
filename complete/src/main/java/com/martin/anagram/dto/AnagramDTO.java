package com.martin.anagram.dto;

import java.util.List;

/** Represents the set of anagrams and also contains the given word/s
 * @author Sergio Martin
 * @version 1.0
 */
public class AnagramDTO {
    private String word;
    private List<String> listOfAnagrams;

    public AnagramDTO(String word, List<String> listOfAnagrams) {
        this.word = word;
        this.listOfAnagrams = listOfAnagrams;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public List<String> getListOfAnagrams() {
        return listOfAnagrams;
    }

    public void setListOfAnagrams(List<String> listOfAnagrams) {
        this.listOfAnagrams = listOfAnagrams;
    }

    @Override
    public String toString() {
        return "AnagramDTO{" +
                "word='" + word + '\'' +
                ", listOfAnagrams=" + listOfAnagrams +
                '}';
    }
}
