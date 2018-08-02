package hello;

import java.io.FileNotFoundException;
import java.util.concurrent.atomic.AtomicLong;

import dto.AnagramDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import services.AnagramGeneratorImpl;

@RestController
public class AnagramsController {


    @RequestMapping("/anagram")
    public AnagramDTO anagram(@RequestParam(value="word", defaultValue="Aschheim") String word) {
        return new AnagramGeneratorImpl().createAnagrams();
    }
}
