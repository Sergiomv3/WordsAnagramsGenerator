package com.martin.anagram.services;

import com.martin.anagram.dto.AnagramDTO;
import com.martin.anagram.interfaces.AnagramGenerator;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;

/** Implements the inteface AnagramGenerator and generates an AnagramDTO object
 * @author Sergio Martin
 * @version 1.0
 */
@Service("anagramGenerator")
public class AnagramGeneratorImpl implements AnagramGenerator {

    @Override
    public AnagramDTO createAnagrams(String word) {
        com.martin.anagram.main.AnagramGenerator anagramGenerator = new com.martin.anagram.main.AnagramGenerator(word);
        try {
            anagramGenerator.generateAnagrams();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new AnagramDTO(word, anagramGenerator.getListAnagrams());
    }
}
