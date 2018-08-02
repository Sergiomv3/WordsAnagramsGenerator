package hello;

import java.io.FileNotFoundException;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnagramsController {


    @RequestMapping("/anagram")
    public Anagrams anagram(@RequestParam(value="word", defaultValue="Aschheim") String word) {
        try {
            return new Anagrams(word);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //iif dictionary does not exist?
        return null;
    }
}
