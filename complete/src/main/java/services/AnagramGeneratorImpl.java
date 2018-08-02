package services;

import dto.AnagramDTO;
import interfaces.IAnagramGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return anagramDTO; // TODO ???
    }
}
