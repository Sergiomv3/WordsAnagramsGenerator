package com.martin.anagram.interfaces;

import com.martin.anagram.dto.AnagramDTO;

/**
 * Simple interface for AnagramGeneratorImpl.
 * @author Sergio Martin
 * @version 1.0
 */
public interface AnagramGenerator {
    /**
     * Perform the generation of anagrams for a given word or phrase
     * @return the given word/s and the generated anagrams
     */
    public AnagramDTO createAnagrams(String word);

    DictionaryReader getDictionaryReader();

    void setDictionaryReader(DictionaryReader dictionaryReader);
}
