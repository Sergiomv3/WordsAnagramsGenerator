package controller;

import com.martin.anagram.dto.AnagramDTO;
import com.martin.anagram.services.AnagramGeneratorImpl;
import org.junit.Test;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RestController
public class AnagramsControllerTest {

    //TODO review this method
    @Test
    public void checkIfAnagramsAreCorrect(){
        AnagramGeneratorImpl anagramGenerator = new AnagramGeneratorImpl();
        List<String> listAnagrams = new LinkedList<>();
        listAnagrams.add("gent its");
        listAnagrams.add("gets nit");
        listAnagrams.add("gin stet");
        listAnagrams.add("gist net");
        listAnagrams.add("sit gent");
        listAnagrams.add("ten gist");
        listAnagrams.add("test gin");
        listAnagrams.add("tin gets");
        listAnagrams.add("tins get");
        listAnagrams.add("tits gen");
        AnagramDTO anagramDTO = new AnagramDTO("word", listAnagrams);
        assertEquals(anagramGenerator.createAnagrams("testing"), anagramDTO);
    }
}
