package interfaces;

import dto.AnagramDTO;

import java.util.List;

/**
 * Simple interface for AnagramDAOImpl.
 * @author Sergio Martin
 * @version 1.0
 */
public interface IAnagramDAO {
    /**
     * Perform the generation of anagrams for a given word or phrase
     * @return the given word/s and the generated anagrams
     */
    public AnagramDTO createAnagrams(String word, List<String> generatedAnagrams);

}