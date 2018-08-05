package components;

import com.martin.anagram.utils.Constants;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * DictionaryReaderImplTest unit tests
 *
 * @author Sergio Martin
 * @version 1.0
 */
public class DictionaryReaderImplTest  {


    @Test
    public void checkThatFileIsNotNull() {
        Scanner file = null;
        ClassLoader classLoader = getClass().getClassLoader();
        try {
            file = new Scanner(new File(classLoader.getResource(Constants.FILE_NAME_OF_DICTIONARY).getFile()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assertNotNull(file);
    }

    @Test
    public void checkFileHasContent(){
        Scanner file = null;
        ClassLoader classLoader = getClass().getClassLoader();
        try {
            file = new Scanner(new File(classLoader.getResource(Constants.FILE_NAME_OF_DICTIONARY).getFile()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assertTrue(file.hasNext());
    }
}
