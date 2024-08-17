import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Files;


/**
 * Stores files using smaller disk space
 * Transfers them more quickly
 * @author Alondra Valdes
 */
public class Compression {
/**
 * Starts program
 * @param args command line arguments
 */
    public static void main(String[] args) {
        Scanner user = new Scanner(System.in);
        char space = ' ';
        int spacesCounted = 0;
        if(args.length != 3) {
            System.out.println("Usage: java -cp bin Compression {-C|-D} infile outfile");
            System.exit(1);
        }
        String flag = args[0];
        String filename = args[1];
        String outputFile = args[2];
        if(!(flag.equals("-C")) && !(flag.equals("-D"))) {
            System.out.println("Usage: java -cp bin Compression {-C|-D} infile outfile");
            System.exit(1);
        }
        String overwrite = "";
        Scanner in = null;
        PrintWriter output = null;
        try {
            FileInputStream fileInput = new FileInputStream(filename);
            in = new Scanner(fileInput);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to access input file: " + filename);
            System.exit(1);
        } 
        Path path = Path.of(outputFile);
        if(Files.exists(path)) {
            System.out.print(outputFile + " exists - OK to overwrite (y,n)?: ");
            overwrite = user.next();
            if(overwrite.equalsIgnoreCase("Y")) {
                try {
                    FileOutputStream fileOutput = new FileOutputStream(outputFile);
                    output = new PrintWriter(fileOutput);
                    if(flag.equals("-C")) {
                        processFile(true, in, output);
                    } else if (flag.equals("-D")) {
                        processFile(false, in, output);
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("Cannot create output file");
                    System.exit(1);
                }
            } else {
                System.exit(1);
            }
        } else {
            try {
                FileOutputStream fileOutput = new FileOutputStream(outputFile);
                output = new PrintWriter(fileOutput);
                if(flag.equals("-C")) {
                    processFile(true, in, output);
                } else {
                    processFile(false, in, output);
                }
            } catch (FileNotFoundException e) {
                System.out.println("Cannot create output file");
                System.exit(1);
            }
        }
        in.close();
        output.close();
    }
/**
 * If compress is true, read text from input scanner then compress the text and write
 * the compressed text to output
 * If compress is false, read text from input scanner then decompress the text and 
 * write the decompressed text to output
 * @param compress if compress is true or false
 * @param input text from input scanner
 * @param output write the compressed/decompressed text to output
 * @throws IllegalArgumentException if input or output is null
 */

    public static void processFile (boolean compress, Scanner input, PrintWriter output){
        if(input == null) {
            throw new IllegalArgumentException("Null input");
        }
        if(output == null) {
            throw new IllegalArgumentException("Null output");
        }
        while(input.hasNextLine()) {
            String line = input.nextLine();
            if(compress == true) {
                output.println(compressLine(line));
            } else if(compress == false) {
                output.println(decompressLine(line));
            }
        }
        output.close();
    }
/**
 * Returns string containing compressed line
 * @param line compressed line
 * @return string containing the compressed line
 * @throws IllegalArgumentException if line is null
 */

    public static String compressLine(String line){
        if(line == null) {
            throw new IllegalArgumentException("Null line");
        }
        String compressedLine = "";
        String word = "";
        char wordSpace = ' ';
        for(int i = 0; i < line.length(); i++) {
            if(line.charAt(i) != wordSpace) {
                word += line.charAt(i);
            } else if(line.charAt(i) == wordSpace) {
                compressedLine += compressWord(word) + " ";
                word = "";
                continue;
            }
            if(i == line.length() - 1) {
                compressedLine += compressWord(word);
            }
        }
        return compressedLine;
    }
/**
 * Returns string containing compressed word (token)
 * @param word compressed word
 * @return String with compressed word
 * @throws IllegalArgumentException if word is null
 */

    public static String compressWord(String word) { 
        if(word == null) {
            throw new IllegalArgumentException("Null word");
        }
        int theIndex = word.indexOf("the");
        if(theIndex != -1) {
            word = word.substring(0, theIndex) + "&" + word.substring(theIndex + 3);
        }
        int anIndex = word.indexOf("an");
        if(anIndex != -1) {
            word = word.substring(0, anIndex) + "~" + word.substring(anIndex + 2);
        }
        int ionIndex = word.indexOf("ion");
        if(ionIndex != -1) {
            word = word.substring(0, ionIndex) + "#" + word.substring(ionIndex + 3);
        }
        int ingIndex = word.indexOf("ing");
        if(ingIndex != -1) {
            word = word.substring(0, ingIndex) + "@" + word.substring(ingIndex + 3);
        }
        int tisIndex = word.indexOf("tis");
        if(tisIndex != -1) {
            word = word.substring(0, tisIndex) + "%" + word.substring(tisIndex + 3);
        }
        int menIndex = word.indexOf("men");
        if(menIndex != -1) {
            word = word.substring(0, menIndex) + "+" + word.substring(menIndex + 3);
        }
        int reIndex = word.indexOf("re");
        if(reIndex != -1) {
            word = word.substring(0, reIndex) + "$" + word.substring(reIndex + 2);
        }
        return word;      
    }
/**
 * Returns string containing decompressed line
 * @param line decompressed line
 * @return string with decompressed line
 * @throws IllegalArgumentException if line is null
 */

    public static String decompressLine(String line){
        if(line == null) {
            throw new IllegalArgumentException("Null line");
        }
        String decompressedLine = "";
        String word = "";
        char wordSpace = ' ';
        for(int i = 0; i < line.length(); i++) {
            if(line.charAt(i) != wordSpace) {
                word += line.charAt(i);
            } else if(line.charAt(i) == wordSpace) {
                decompressedLine += decompressWord(word) + " ";
                word = "";
                continue;
            }
            if(i == line.length() - 1) {
                decompressedLine += decompressWord(word);
            }
        }
        return decompressedLine;
    }
/**
 * Returns string containing decompressed word (token)
 * @param word decompressed word
 * @return string with decompressed word
 * @throws IllegalArgumentException if word is null
 */

    public static String decompressWord(String word) {
        if(word == null) {
            throw new IllegalArgumentException("Null word");
        }
        int theSymbolIndex = word.indexOf("&");
        if(theSymbolIndex != -1) {
            word = word.substring(0, theSymbolIndex) + "the" + word.substring(theSymbolIndex + 1);
        }
        int anSymbolIndex = word.indexOf("~");
        if(anSymbolIndex != -1) {
            word = word.substring(0, anSymbolIndex) + "an" + word.substring(anSymbolIndex + 1);
        }
        int ionSymbolIndex = word.indexOf("#");
        if(ionSymbolIndex != -1) {
            word = word.substring(0, ionSymbolIndex) + "ion" + word.substring(ionSymbolIndex + 1);
        }
        int ingSymbolIndex = word.indexOf("@");
        if(ingSymbolIndex != -1) {
            word = word.substring(0, ingSymbolIndex) + "ing" + word.substring(ingSymbolIndex + 1);
        }
        int tisSymbolIndex = word.indexOf("%");
        if(tisSymbolIndex != -1) {
            word = word.substring(0, tisSymbolIndex) + "tis" + word.substring(tisSymbolIndex + 1);
        }
        int menSymbolIndex = word.indexOf("+");
        if(menSymbolIndex != -1) {
            word = word.substring(0, menSymbolIndex) + "men" + word.substring(menSymbolIndex + 1);
        }
        int reSymbolIndex = word.indexOf("$");
        if(reSymbolIndex != -1) {
            word = word.substring(0, reSymbolIndex) + "re" + word.substring(reSymbolIndex + 1);
        }
        return word;
    }
}
