package com.martin.anagram.main;

import com.martin.anagram.utils.TreeNode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**  AnagramGenerator contains the logic to find the properly anagrams of given word/s
 * @author Sergio Martin
 * @version 1.0
 */
public class AnagramGenerator {

    /*The word introduced by user (GET method)*/
    private String word;

    /*The list of generated anagrams*/
    private List<String> listAnagrams;

    /*The lenght of the word introduced*/
    private int lengthofIntroducedWord;

    /*The TreeNode object*/
    private TreeNode root = new TreeNode();

    /**
     * Instantiates an AnagramGenerator object
     * @param word the word introduced in the input field (GET method)
     */
    public AnagramGenerator(String word) {
        this.word = word;
    }

    /**
     * Gets the word that have been introduced
     * @return the word introduced
     */
    public String getWord() {
        return word;
    }

    /**
     * Get the list of anagrams
     * @return the list that contains the list of generated anagrams
     */
    public List<String> getListAnagrams() {
        return listAnagrams;
    }


//TODO - Should move the two below methods?
    private void addWord(String value) {
        // Add all characters from the string to the tree.
        root = new TreeNode();
        TreeNode node = root;

        for (int i = 0; i < value.length(); i++) {
            node = node.add(value.charAt(i), node);
        }
        node.setWord(true);
    }

    private boolean exists(String value) {
        // See if a word exists in the tree.
        TreeNode node = root;
        for (int i = 0; i < value.length(); i++) {
            node = node.get(value.charAt(i));
            if (node == null) {
                return false;
            }
        }
        return node.isWord();
    }

    /**
     * Generates all the anagrams possible given word (TODO - replace by recursive method when working)
     * @throws FileNotFoundException if a dictionary isn't found
     */
    public void generateAnagrams() throws FileNotFoundException {
        /**
         * The indexed dictionary (e.g. 'Best Secret' -> 'bceeersstt')
         */
        Map<String, List<String>> indexedDictionary = new HashMap<>();

        listAnagrams = new LinkedList<>();
        root = new TreeNode();

        Set<String> dictionary = readDictionary();

        indexDIctionary(indexedDictionary, dictionary);

        String inputtedWord = getWordWithProperlyFormat();
        lengthofIntroducedWord = inputtedWord.length();

        String indexedInputtedWord = indexWordInputted(inputtedWord);

        //Cut and simplify the dictionary according to the available letters from the inputtedWord
        simplifyDictionaryByInputtedWord(indexedDictionary, indexedInputtedWord);

        //TODO Recursive method should start here

//        for (String key : indexedDictionary.keySet()) {
//            Map<String, List<String>> auxIndexedDictionary = null;
//
//            //Deleting in the indexedInpuutedWord, the letters from every key.
//            String auxIndexedInputtedWord = reduceWordAccordingToKeyLetters(indexedInputtedWord, key);
//
//            while (auxIndexedInputtedWord.length() > 3) {
//                //copy from last dictionary
//                if (auxIndexedDictionary == null) {
//                    auxIndexedDictionary = new HashMap(indexedDictionary);
//                }
//                simplifyDictionaryByInputtedWord(auxIndexedDictionary, auxIndexedInputtedWord);
//
//                //llamar recursivamente TODO recursive point?
//                // restante seal >= 3
//                addAnagramsToList(indexedDictionary, key, auxIndexedDictionary, auxIndexedInputtedWord);
//                break;
//            }
//            //TODO reduce and use addAnagramsToList method
//            if (auxIndexedInputtedWord.length() == 3 && (auxIndexedInputtedWord.length() + key.length()) == lengthofIntroducedWord) {
//                if (indexedDictionary.get(auxIndexedInputtedWord) != null) {
//                    List<String> listA = indexedDictionary.get(key);
//                    List<String> listB = indexedDictionary.get(auxIndexedInputtedWord);
//                    for (int i = 0; i < listA.size(); i++) {
//                        for (int j = 0; j < listB.size(); j++) {
//                            listAnagrams.add(listA.get(i) + " " + listB.get(j));
//                        }
//                    }
//                }
//
//            }
//        }
        recursiveAnagrammer(indexedInputtedWord, indexedDictionary); //TODO - REPLACE BY A WORKING RECURSIVE METHOD

        //Java 8 sorting by lambda
        Collections.sort(listAnagrams, (s1, s2) -> s1.compareTo(s2));


    }

    private void addAnagramsToList(Map<String, List<String>> indexedDictionary, String key, Map<String, List<String>> auxIndexedDictionary, String auxIndexedInputtedWord) {
        if (auxIndexedDictionary.get(auxIndexedInputtedWord) != null) {
            List<String> listA = indexedDictionary.get(key);
            List<String> listB = auxIndexedDictionary.get(auxIndexedInputtedWord);
            for (int i = 0; i < listA.size(); i++) {
                for (int j = 0; j < listB.size(); j++) {
                    listAnagrams.add(listA.get(i) + " " + listB.get(j));
                    listB.remove(j);

                }
            }
        }
    }

    private String reduceWordAccordingToKeyLetters(String indexedInputtedWord, String key) {
        String auxIndexedInputtedWord = indexedInputtedWord;
        for (char letter : key.toCharArray()) {
            auxIndexedInputtedWord = auxIndexedInputtedWord.replaceFirst(Character.toString(letter), "");
        }
        return auxIndexedInputtedWord;
    }

    private void simplifyDictionaryByInputtedWord(Map<String, List<String>> indexedDictionary, String indexedInputtedWord) {
        addWord(indexedInputtedWord);

        Iterator<Map.Entry<String, List<String>>> itr = indexedDictionary.entrySet().iterator();

        while (itr.hasNext()) {
            Map.Entry<String, List<String>> entry = itr.next();
            if (!exists(entry.getKey())) {
                itr.remove();
            }
        }
    }

    private String indexWordInputted(String wordInputted) {
        char[] chars = wordInputted.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    private String getWordWithProperlyFormat() {
        String word = getWord();
        word = word.toLowerCase();
        word = word.replace(" ", "");
        word = word.replaceAll("[^a-zA-Z]+", "");
        return word;
    }

    private void indexDIctionary(Map<String, List<String>> indexedDictionary, Set<String> dictionary) {
        for (String word : dictionary) {
            //INDEXO EL DICCIONARIO
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);

            if (!indexedDictionary.containsKey(key)) {
                indexedDictionary.put(new String(chars), new LinkedList<String>());
            }
            indexedDictionary.get(key).add(word);
        }
    }

    private Set<String> readDictionary() throws FileNotFoundException {
        // Read the file using whitespace as a delimiter (default)
        // so that the input will be split into words
        Scanner file = new Scanner(new File("src\\main\\java\\com\\martin\\anagram\\main\\anagramDic.txt"));

        Set<String> dictionary = new HashSet<>();
        // For each word in the input
        while (file.hasNext()) {
            // Convert the word to lower case, trim it and insert into the set
            // ¿remove punctuation marks too?
            String readWord = file.next();
            if (readWord.length() > 2) {
                dictionary.add(readWord.trim().toLowerCase());
            }
        }
        return dictionary;
    }

    // TODO THIS RECURSIVE METHOD
    public  void recursiveAnagrammer(String lastWordIndexed, Map<String, List<String>> lastDictionary) {
        Map<String, List<String>> auxLastDictionary = null;
        if (lastWordIndexed.length() >= 3) {
            for (String key : lastDictionary.keySet()) {
                // Cojo cada clave, y resto de la palabra input las letras usadas
                // 1º elmino las letras
                String auxLastWordIndexed = lastWordIndexed;
                for (char letter : key.toCharArray()) {
                    auxLastWordIndexed = auxLastWordIndexed.replaceFirst(Character.toString(letter), "");

                    //copia del diccionario anterior
                    auxLastDictionary = new HashMap(lastDictionary);

                }

                // TreeNode -> encontrar que keys están deentro de la palabra y hacer la
                // 1ª poda
                simplifyDictionaryByInputtedWord(auxLastDictionary, auxLastWordIndexed);
                if ((key.length() + auxLastWordIndexed.length()) == lengthofIntroducedWord) {
                    List<String> listA = lastDictionary.get(key);
                    List<String> listB = auxLastDictionary.get(auxLastWordIndexed);
                    if (listA != null && listB != null) {
                        for (int i = 0; i < listA.size(); i++) {
                            for (int j = 0; j < listB.size(); j++) {
                                listAnagrams.add(listA.get(i) + " " + listB.get(j));
                                listB.remove(j);
                            }
                        }
                    }
                }
                if (!auxLastDictionary.isEmpty() && auxLastWordIndexed.length() >= 3) {
                    recursiveAnagrammer(auxLastWordIndexed, auxLastDictionary);
                } else {
                    if (auxLastDictionary.get(auxLastWordIndexed) != null && auxLastWordIndexed.length() == 3) {
                        List<String> listA = lastDictionary.get(key);
                        List<String> listB = auxLastDictionary.get(auxLastWordIndexed);
                        for (int i = 0; i < listA.size(); i++) {
                            for (int j = 0; j < listB.size(); j++) {
                                listAnagrams.add(listA.get(i) + " " + listB.get(j));
                                listB.remove(j);

                            }
                        }
                    }
                }
            }
        }

    }
}
