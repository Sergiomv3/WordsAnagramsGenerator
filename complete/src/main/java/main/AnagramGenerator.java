package main;

import utils.TreeNode;

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
    private int lengthOfWordIntroduced;

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


    public List<String> getListAnagrams() {
        return listAnagrams;
    }



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

    public void generateAnagrams() throws FileNotFoundException {
        listAnagrams = new LinkedList<>();
        root = new TreeNode();
        // Read the file using whitespace as a delimiter (default)
        // so that the input will be split into words
        Scanner file = new Scanner(new File("src\\main\\java\\main\\anagramDic.txt"));

        Set<String> dictionary = new HashSet<>();
        Map<String, List<String>> indexedDictionary = new HashMap<>();
        // For each word in the input
        while (file.hasNext()) {
            // Convert the word to lower case, trim it and insert into the set
            // ¿remove punctuation marks too?
            String readWord = file.next();
            if (readWord.length() > 2) {
                dictionary.add(readWord.trim().toLowerCase());
            }
        }
        System.out.println("Todas las entradas del diccionario:");

        for (String word : dictionary) {
            System.out.println(word);
            //INDEXO EL DICCIONARIO
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);

            if (!indexedDictionary.containsKey(key)) {
                indexedDictionary.put(new String(chars), new LinkedList<String>());
            }
            indexedDictionary.get(key).add(word);
        }
        System.out.println("___________________________________");

        System.out.println("Todas las entrads indexadas:");
        for (String key : indexedDictionary.keySet()) {
            System.out.println("key : " + key);
            System.out.println("words : " + indexedDictionary.get(key));
        }
        System.out.println("___________________________________");

        // WORD TO FIND
        String word = getWord();
        word = word.toLowerCase();
        word = word.replace(" ", "");
        word = word.replaceAll("[^a-zA-Z]+", "");
        lengthOfWordIntroduced = word.length();
        //INDEXO EL DICCIONARIO
        char[] chars = word.toCharArray();
        Arrays.sort(chars);
        String wordIndexed = new String(chars);
        System.out.println("La palabra indexada es:" + wordIndexed);
        System.out.println("__________________________________");

        // TreeNode -> encontrar que keys están deentro de la palabra y hacer la
        // 1ª poda
        addWord(wordIndexed);
        Iterator<Map.Entry<String, List<String>>> itr = indexedDictionary.entrySet().iterator();

        while (itr.hasNext()) {
            Map.Entry<String, List<String>> entry = itr.next();
            if (!exists(entry.getKey())) {
                itr.remove();
            }
        }
        System.out.println("PRIMERA PODA.........");
        for (String key : indexedDictionary.keySet()) {
            System.out.println("key : " + key);
            System.out.println("words : " + indexedDictionary.get(key));
        }
        System.out.println("___________________________________");

        // El diccionario con una poda es mi original, a partir de las siguientes
        //podas, tengo que crear copias
        // El diccionario con una poda es mi original, a partir de las siguientes
        //podas, tengo que crear copias
        for (String key : indexedDictionary.keySet()) {
            // Cojo cada clave, y resto de la palabra input las letras usadas
            // 1º elmino las letras
            String auxWordIndexed = wordIndexed;
            for (char letter : key.toCharArray()) {
                auxWordIndexed = auxWordIndexed.replaceFirst(Character.toString(letter), "");
            }

            System.out.println("PALABRA RECOTARDA: " + auxWordIndexed);
            Map<String, List<String>> auxIndexedDictionary = null;
            while (auxWordIndexed.length() > 3) {
                //copia del diccionario anterior
                if (auxIndexedDictionary == null) {
                    auxIndexedDictionary = new HashMap(indexedDictionary);
                }

                // TreeNode -> encontrar que keys están deentro de la palabra y hacer la
                // 1ª poda
                addWord(auxWordIndexed);
                Iterator<Map.Entry<String, List<String>>> iterator = auxIndexedDictionary.entrySet().iterator();

                while (iterator.hasNext()) {
                    Map.Entry<String, List<String>> entry = iterator.next();
                    if (!exists(entry.getKey())) {
                        iterator.remove();
                    }
                }
                // puede que la palabra sea una clave válida
                //llamar recursivamente al metdo que es la funcionalidad de arriba mientras que el
                // restante seal >= 3
                if (auxIndexedDictionary.get(auxWordIndexed) != null) {
                    List<String> listA = indexedDictionary.get(key);
                    List<String> listB = auxIndexedDictionary.get(auxWordIndexed);
                    for (int i = 0; i < listA.size(); i++) {
                        for (int j = 0; j < listB.size(); j++) {
                            listAnagrams.add(listA.get(i) + " " + listB.get(j));
                            listB.remove(j);

                        }
                    }
                }
                break;
            }

            if (auxWordIndexed.length() == 3) {
                if (indexedDictionary.get(auxWordIndexed) != null) {
                    List<String> listA = indexedDictionary.get(key);
                    List<String> listB = indexedDictionary.get(auxWordIndexed);
                    for (int i = 0; i < listA.size(); i++) {
                        for (int j = 0; j < listB.size(); j++) {
                            listAnagrams.add(listA.get(i) + " " + listB.get(j));
                        }
                    }
                }

            }
        }
        //recursiveAnagrammer(wordIndexed, indexedDictionary); THIS IS THE RECURSIVE METHOD
        System.out.println("=========================");

        Collections.sort(listAnagrams, (s1, s2) -> s1.compareTo(s2));

        for (String s : listAnagrams) {
            System.out.println(s);
        }

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
                addWord(auxLastWordIndexed);
                Iterator<Map.Entry<String, List<String>>> iterator = auxLastDictionary.entrySet().iterator();

                while (iterator.hasNext()) {
                    Map.Entry<String, List<String>> entry = iterator.next();
                    if (!exists(entry.getKey())) {
                        iterator.remove();
                    }
                }
                if ((key.length() + auxLastWordIndexed.length()) == lengthOfWordIntroduced) {
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
