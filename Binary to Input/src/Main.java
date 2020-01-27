import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

    }

    private static void questionOne() {
        Scanner scan = new Scanner(System.in);
        System.out.println("What would you like to convert to binary?");
        String a = scan.nextLine();
        System.out.println(Compression.convertToBinary(a));
    }

    private static void questionTwo() {
        Scanner scan = new Scanner(System.in);
        System.out.println("What would you like to compress?");
        String answer = scan.nextLine();
        Compression.printCompressionRatio(Compression.LZWCompression(answer, 10), answer, 10);
    }

    private static void questionThree() throws FileNotFoundException {
        File f = new File("C:\\Users\\danie\\OneDrive\\Documents\\School\\Java-Class\\Binary to Input\\src\\ToCompress.txt");
        Compression.LZWCompression(Compression.readFromFile(f), 10);
    }

    private static void questionFour() throws FileNotFoundException {
        Compression.bestBits(Compression.readFromFile(new File("C:\\Users\\danie\\OneDrive\\Documents\\School\\Java-Class\\Binary to Input\\src\\ToCompress.txt")), 8);
    }

    private static void questionFive() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter in binary to be uncompressed");
        String a = scanner.nextLine();
        System.out.println("And the bits it was compressed with");
        int s = scanner.nextInt();
        System.out.println(Compression.LZWUncompress(a, s));
    }

    private static void questionSix() throws FileNotFoundException {
        Compression.uncompressFile(new File("C:\\Users\\danie\\OneDrive\\Documents\\School\\Java-Class\\Binary to Input\\src\\compressed.txt"));
    }



}
