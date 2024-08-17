import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.io.*;

/**
 * Program to test Compression methods
 * @author Suzanne Balik
 * @author Michelle Glatz
 * @author Alondra Valdes
 */
public class CompressionTest {

    /** single word with two "re"'s and one "ing" character sequence */
    public static final String WORD1 = "regretting";

    /** single word with one of two "re"'s and one "ing" replaced by characters */
    public static final String WORD1_COMPRESSED = "$grett@";

    /** multiword line with multiple character sequences */
    public static final String LINE1 = "the action had an equal and opposite reaction.";
    
    /** multiword line with multiple replacement characters */
    public static final String LINE1_COMPRESSED = "& act# had ~ equal ~d opposite $act#.";

    /** empty string */
    public static final String WORD2 = "";

    /** empty string compressed */
    public static final String WORD2_COMPRESSED = "";

    /** string with all numbers */
    public static final String WORD3 = "1234567890";

    /** string with all numbers compressed */
    public static final String WORD3_COMPRESSED = "1234567890";

    /** all character sequences */
    public static final String WORD4 = "theanioningtismenre";

    /** all character sequences compressed */
    public static final String WORD4_COMPRESSED = "&~#@%+$";

    /** one word line */ 
    public static final String LINE2 = "thesis";

    /** one word line compressed */
    public static final String LINE2_COMPRESSED = "&sis";

    /** empty string line */
    public static final String LINE3 = "";

    /** empty string line compressed */
    public static final String LINE3_COMPRESSED = "";

/**
 * Tests compression of normal word
 * regretting
 * $grett@
 */
    @Test
    public void testCompressWord1() {
        String description = "Compress Word 1";
        String actual = Compression.compressWord(WORD1);
        assertEquals(WORD1_COMPRESSED, actual, description);
    }

/**
 * Tests compression of empty string
 * Should output empty string
 */
    @Test
    public void testCompressWord2() {
        String description = "Compress Word 2";
        String actual = Compression.compressWord(WORD2);
        assertEquals(WORD2_COMPRESSED, actual, description);
    }

/**
 * Tests compression of all numbers on keyboard
 * 1234567890 (doesn't change)
 */
    @Test
    public void testCompressWord3() {
        String description = "Compress Word 3";
        String actual = Compression.compressWord(WORD3);
        assertEquals(WORD3_COMPRESSED, actual, description);
    }

/**
 * Tests compression of all character sequences
 * theanioningtismenre
 * &~#@%+$
 */
    @Test
    public void testCompressWord4() {
        String description = "Compress Word 4";
        String actual = Compression.compressWord(WORD4);
        assertEquals(WORD4_COMPRESSED, actual, description);
    }

/**
 * Tests decompression of normal word
 * $grett@
 * regretting
 */
    @Test
    public void testDecompressWord1() {
        String description = "Decompress Word 1";
        String actual = Compression.decompressWord(WORD1_COMPRESSED);
        assertEquals(WORD1, actual, description);
    }

/**
 * Tests decompression of empty string
 * Should output empty string
 */
    @Test
    public void testDecompressWord2() {
        String description = "Decompress Word 2";
        String actual = Compression.decompressWord(WORD2_COMPRESSED);
        assertEquals(WORD2, actual, description);
    }

/**
 * Tests decompression of all numbers on keyboard
 * 1234567890 (doesn't change)
 */
    @Test
    public void testDecompressWord3() {
        String description = "Decompress Word 3";
        String actual = Compression.decompressWord(WORD3_COMPRESSED);
        assertEquals(WORD3, actual, description);
    }

/**
 * Tests decompression of all character sequences
 * &~#@%+$
 * theanioningtismenre
 */
    @Test
    public void testDecompressWord4() {
        String description = "Decompress Word 4";
        String actual = Compression.decompressWord(WORD4_COMPRESSED);
        assertEquals(WORD4, actual, description);
    }

/**
 * Tests compressLine method with multiple words in one line
 * the action had an equal and opposite reaction.
 * & act# had ~ equal ~d opposite $act#.
 */
    @Test
    public void testCompressLine1() {
        String description = "Compress Line 1";
        String actual = Compression.compressLine(LINE1);
        assertEquals(LINE1_COMPRESSED, actual, description);
    }

/**
 * Tests compressLine method with one word line
 * thesis
 * &sis
 */
    @Test
    public void testCompressLine2() {
        String description = "Compress Line 2";
        String actual = Compression.compressLine(LINE2);
        assertEquals(LINE2_COMPRESSED, actual, description);
    }

/**
 * Tests compressLine method with empty string
 * Should output empty string
 */
    @Test
    public void testCompressLine3() {
        String description = "Compress Line 3";
        String actual = Compression.compressLine(LINE3);
        assertEquals(LINE3_COMPRESSED, actual, description);
    }

/**
 * Tests decompressLine method with multiple words in one line
 * & act# had ~ equal ~d opposite $act#.
 * the action had an equal and opposite reaction.
 */
    @Test
    public void testDecompressLine1() {
        String description = "Decompress Line 1";
        String actual = Compression.decompressLine(LINE1_COMPRESSED);
        assertEquals(LINE1, actual, description);
    }

/**
 * Tests decompressLine method with one word line
 * &sis
 * thesis
 */
    @Test
    public void testDecompressLine2() {
        String description = "Decompress Line 2";
        String actual = Compression.decompressLine(LINE2_COMPRESSED);
        assertEquals(LINE2, actual, description);
    }

/**
 * Tests decompressLine method with empty string
 * Should output empty string
 */
    @Test
    public void testDecompressLine3() {
        String description = "Decompress Line 3";
        String actual = Compression.decompressLine(LINE3_COMPRESSED);
        assertEquals(LINE3, actual, description);
    }
  
    /**
     * Test the PatternMaker methods with invalid values
     */
    @Test
    public void testInvalidMethods() {

        // Invalid test cases are provided for you below - You do NOT
        // need to add additional invalid tests. Just make sure these
        // pass!

        Scanner in = new Scanner("abc");

        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> Compression.processFile(true, null, null), 
            "Compression.processFile(true, null, null)");
        assertEquals("Null input", exception.getMessage(),
                     "Testing Compression.processFile(true, null, null) - exception message");
        
        
        exception = assertThrows(IllegalArgumentException.class,
            () -> Compression.processFile(true, in, null), 
            "Compression.processFile(true, in, null)");
        assertEquals("Null output", exception.getMessage(),
                     "Testing Compression.processFile(true, in, null) - exception message");
                     
        exception = assertThrows(IllegalArgumentException.class,
            () -> Compression.compressLine(null), "Compression.compressLine(null)");
        assertEquals("Null line", exception.getMessage(),
                     "Testing Compression.compressLine(null) - exception message");
                     
        exception = assertThrows(IllegalArgumentException.class,
            () -> Compression.compressWord(null), "Compression.compressWord(null)");
        assertEquals("Null word", exception.getMessage(),
                     "Testing Compression.compressWord(null) - exception message");
                     
        exception = assertThrows(IllegalArgumentException.class,
            () -> Compression.decompressLine(null), "Compression.decompressLine(null)");
        assertEquals("Null line", exception.getMessage(),
                     "Testing Compression.decompressLine(null) - exception message");
        
        exception = assertThrows(IllegalArgumentException.class,
            () -> Compression.decompressWord(null), "Compression.decompressWord(null)");
        assertEquals("Null word", exception.getMessage(),
                     "Testing Compression.decompressWord(null) - exception message");
    }
    
    /**
     * Tests processFile
     * @throws FileNotFoundException if output files cannot be created
     */
    @Test
    public void testProcessFile() throws FileNotFoundException {

        // You do NOT need to add additional processFile tests. Just make sure these
        // pass!
        String simulatedFile = "this is a test\n" +
                                    "of the emergency broadcast\n" +
                                    "system. if this had been an\n" +
                                    "actual emergency, you would\n" +
                                    "have heard official information,\n" +
                                    "news or instructions. this only a\n" +
                                    "test.\n";
        String simulatedCompressedFile = "this is a test\n" +
                                     "of & emergency broadcast\n" +
                                     "system. if this had been ~\n" +
                                     "actual emergency, you would\n" +
                                     "have heard official informat#,\n" +
                                     "news or instruct#s. this only a\n" +
                                     "test.\n";
        //Test processFile compression
        Scanner in = new Scanner(simulatedFile);
        PrintWriter out = new PrintWriter(
                          new FileOutputStream("test-files/processFileCompressionTest.txt"));
        Compression.processFile(true, in, out);
        out.close();
        Scanner actual = 
            new Scanner(new FileInputStream("test-files/processFileCompressionTest.txt"));
        Scanner expected = new Scanner(simulatedCompressedFile);
        while (expected.hasNextLine()) {
            if (!actual.hasNextLine()) {
                fail("processFile: compressed file missing line(s)");
            }
            String exp = expected.nextLine();
            String act = actual.nextLine();
            assertEquals(exp, act, "processFile: compressed file incorrect line");
        }
        if (actual.hasNextLine()) {
            fail("processFile: compressed file has extra line(s)");
        }
        
        //Test processFile decompression
        in = new Scanner(simulatedCompressedFile);
        out = new PrintWriter(new FileOutputStream("test-files/processFileDecompressionTest.txt"));
        Compression.processFile(false, in, out);
        out.close();
        actual = new Scanner(new FileInputStream("test-files/processFileDecompressionTest.txt"));
        expected = new Scanner(simulatedFile);
        while (expected.hasNextLine()) {
            if (!actual.hasNextLine()) {
                fail("processFile: decompressed file missing line(s)");
            }
            String exp = expected.nextLine();
            String act = actual.nextLine();
            assertEquals(exp, act, "processFile: decompressed file incorrect line");
        }
        if (actual.hasNextLine()) {
            fail("processFile: decompressed file has extra line(s)");
        }
    }                            
}
