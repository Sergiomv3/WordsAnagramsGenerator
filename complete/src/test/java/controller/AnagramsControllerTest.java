package controller;

import com.martin.anagram.components.DictionaryReaderImpl;
import com.martin.anagram.dto.AnagramDTO;
import com.martin.anagram.interfaces.AnagramGenerator;
import com.martin.anagram.interfaces.DictionaryReader;
import com.martin.anagram.services.AnagramGeneratorImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class AnagramsControllerTest {

    AnagramGenerator anagramGenerator;
    DictionaryReader dictionaryReader;

    @Before
    public void setUp(){
        anagramGenerator = new AnagramGeneratorImpl();
        dictionaryReader = new DictionaryReaderImpl();
        anagramGenerator.setDictionaryReader(dictionaryReader);
    }

    @Test
    public void checkIfAnagramsAreCorrect(){

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
        assertTrue((anagramGenerator.createAnagrams("testing")).getListOfAnagrams().equals(anagramDTO.getListOfAnagrams()));
    }
}
