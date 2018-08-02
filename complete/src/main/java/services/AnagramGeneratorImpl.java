package services;

import dto.AnagramDTO;
import interfaces.IAnagramGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/** Implements the inteface IAnagramGenerator and generates an AnagramDTO object
 * @author Sergio Martin
 * @version 1.0
 */
@Service("iAnagramGenerator")
public class AnagramGeneratorImpl implements IAnagramGenerator{

    @Autowired
    AnagramDTO anagramDTO;

    @Override
    public AnagramDTO createAnagrams() {
        String word = "TEST";
        List<String> listTest = new LinkedList<>();
        listTest.add("This is a value test 1");
        listTest.add("This is a value test 2");
        return new AnagramDTO(word, listTest);
    }
}
