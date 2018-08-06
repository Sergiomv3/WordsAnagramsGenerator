package com.martin.anagram.services;

import com.martin.anagram.dto.AnagramDTO;
import com.martin.anagram.interfaces.AnagramGenerator;
import com.martin.anagram.interfaces.DictionaryReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** Implements the inteface AnagramGenerator and generates an AnagramDTO object
 * @author Sergio Martin
 * @version 1.0
 */
@Service("anagramGenerator")
public class AnagramGeneratorImpl implements AnagramGenerator {

    @Autowired
    DictionaryReader dictionaryReader;

    @Override
    public AnagramDTO createAnagrams(String word) {
        com.martin.anagram.anagramBI.AnagramGenerator anagramGenerator = new com.martin.anagram.anagramBI.AnagramGenerator(word);
        anagramGenerator.generateAnagrams(dictionaryReader.getDictionary());
        return new AnagramDTO(word, anagramGenerator.getListAnagrams());
    }

    public DictionaryReader getDictionaryReader() {
        return dictionaryReader;
    }

    public void setDictionaryReader(DictionaryReader dictionaryReader) {
        this.dictionaryReader = dictionaryReader;
    }
}
