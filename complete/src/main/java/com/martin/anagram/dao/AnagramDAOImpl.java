package com.martin.anagram.dao;

import com.martin.anagram.dto.AnagramDTO;
import com.martin.anagram.interfaces.AnagramDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

/** Implements the inteface AnagramDAO and generates an AnagramDTO object
 * @author Sergio Martin
 * @version 1.0
 */
@Repository("iAnagramDao")
public class AnagramDAOImpl implements AnagramDAO {

    @Override
    public AnagramDTO createAnagrams(String word, List<String> generatedAnagrams) {
        AnagramDTO anagramDTO = new AnagramDTO(word, generatedAnagrams);
        return anagramDTO;
    }
}
