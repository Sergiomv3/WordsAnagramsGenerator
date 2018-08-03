package com.martin.anagram.interfaces;

import java.io.FileNotFoundException;
import java.util.Set;

/**
 * Interface that declares the necessary methods to read a dictionary.
 * @author Sergio Martin
 * @version 1.0
 */
public interface DictionaryReader {


    Set<String> getDictionary();
    /**
     * Reads and dumps all the words from a dictionary in txt format.
     * @return a Set object, filled with the dictionary words
     */
    void readDictionary();
}
