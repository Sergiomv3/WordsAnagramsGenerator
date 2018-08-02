package dao;

import dto.AnagramDTO;
import interfaces.IAnagramDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

/** Implements the inteface IAnagramDAO and generates an AnagramDTO object
 * @author Sergio Martin
 * @version 1.0
 */
@Repository("iAnagramDao")
public class AnagramDAOImpl implements IAnagramDAO {

    @Override
    public AnagramDTO createAnagrams(String word, List<String> generatedAnagrams) {
        AnagramDTO anagramDTO = new AnagramDTO(word, generatedAnagrams);
        return anagramDTO;
    }
}
