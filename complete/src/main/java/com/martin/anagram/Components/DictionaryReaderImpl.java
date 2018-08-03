package com.martin.anagram.Components;

import com.martin.anagram.interfaces.DictionaryReader;
import com.martin.anagram.utils.Constants;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * DictionaryReaderImpl implements the methods to read a dictionary
 *
 * @author Sergio Martin
 * @version 1.0
 */
@Component("dictionaryReader")
public class DictionaryReaderImpl implements DictionaryReader {
    /*The dictionary Set that is read*/
    private Set<String> dictionary;

    public DictionaryReaderImpl() {
        readDictionary();
    }

    @Override
    public Set<String> getDictionary() {
        return dictionary;
    }

    @Override
    public void readDictionary() {
        // Read the file using whitespace as a delimiter (default)
        // so that the input will be split into words
        Scanner file = null;
        try {
            file = new Scanner(new File(Constants.FILE_PATH_OF_DICTIONARY));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Set<String> dictionary = new HashSet<>();
        // For each word in the input
        while (file.hasNext()) {
            // Convert the word to lower case, trim it and insert into the set
            // ¿remove punctuation marks too?
            String readWord = file.next();
            if (readWord.length() > 2) {
                dictionary.add(readWord.trim().toLowerCase());
            }
        }
        this.dictionary = dictionary;
    }
}
