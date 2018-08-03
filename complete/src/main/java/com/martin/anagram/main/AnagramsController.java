package com.martin.anagram.main;

import com.martin.anagram.dto.AnagramDTO;
import com.martin.anagram.interfaces.AnagramGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnagramsController {

    @Autowired
    AnagramGenerator anagramGenerator;

    @RequestMapping("/anagram")
    public AnagramDTO anagram(@RequestParam(value="word", defaultValue="Aschheim") String word) {
        return anagramGenerator.createAnagrams(word);
    }
}
