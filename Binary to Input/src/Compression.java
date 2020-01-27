import com.sun.source.tree.Tree;
import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.lang.reflect.Array;
import java.net.BindException;
import java.util.*;
import java.util.stream.Collectors;

public class Compression {

    public static void main(String[] args) {

    }

    public static String readFromFile(File f) throws FileNotFoundException {
        Scanner scanner = new Scanner(f);
        String s = "";
        while (scanner.hasNext()) {
            s += scanner.nextLine();
        }
        return s;
    }

    private static void writeToFile(File file, String content) throws FileNotFoundException{
        try {
            FileWriter fileWriter = new FileWriter( file.getName() );
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String padString(String toPad, int bits) {

        int toAdd = Math.abs(toPad.length() - bits);
        StringBuilder zeroes = new StringBuilder();
        for (int i = 0; i < toAdd; i++) {
            zeroes.append("0");
        }
        zeroes.append(toPad);
        return zeroes.toString();

    }

    private static String padString(String toPad) {

        int toAdd = Math.abs(toPad.length() - 10);
        StringBuilder zeroes = new StringBuilder();
        for (int i = 0; i < toAdd; i++) {
            zeroes.append("0");
        }
        zeroes.append(toPad);
        return zeroes.toString();

    }

    public static String convertToBinary(String s) {
        StringBuilder binary = new StringBuilder();
        s.chars()
                .mapToObj( i -> (char)i )
                .map(Integer::toBinaryString).forEach(binary::append);
        return binary.toString();
    }

    public static String LZWCompression(String toCompress, int bits) {
        TreeMap<String, Integer> dictionary = new TreeMap<>();
        ArrayList<String> output = new ArrayList<>();
        ArrayList<String> outputBinary = new ArrayList<>();

        String current;
        String next;

        for (int i = 0; i < 128; i++) {
            dictionary.put((char) i + "", (i));
        }

        for (int i = 0; i < toCompress.length(); i++) {
            boolean condition = true;
            current = toCompress.charAt(i) + "";
            next = "";
            if (i + 1 < toCompress.length()) {
                next = toCompress.charAt(i + 1) + "";
            }
            while (dictionary.containsKey(current + next) && i < toCompress.length()) {
//                System.out.println("FOUND IN DICTIONARY----------------------" + (current + next));
                current += next;
                if (i + 1 < toCompress.length()) {
                    next = next + toCompress.charAt(i + 1);
                }
                i++;
                condition = false;
            }

            if (condition) {
//                System.out.println("PUTTING IN DICTIONARY--------------------------" + (current + next));
                dictionary.put(current + next, 127 + i);
            } else if (i + 1 < toCompress.length()) {
//                System.out.println("PUTTING IN DICTIONARY--------------------------" + (current + toCompress.charAt(i + 1)));
                dictionary.put(current + toCompress.charAt(i + 1), 127 + i);
            }
            output.add(current);
        }
//        if (toCompress.length() > ) {
//            output.add(toCompress.charAt(toCompress.length() - 2 ) + "" + toCompress.charAt(toCompress.length() - 1 ));
//        }
        output.forEach(System.out::println);
        System.out.println("-----------------------------------------");
        for (String s : dictionary.keySet()) {
            if (dictionary.get(s) > 127) {
                System.out.println(s + "----------" + dictionary.get(s));
            }
        }

        output.stream().mapToInt(dictionary::get).mapToObj(Integer::toBinaryString).map((e -> padString(e, bits))).forEach(outputBinary::add);
//        outputBinary.forEach(e -> System.out.println(e + "\n"));
        StringBuilder out = new StringBuilder();
        outputBinary.forEach(out::append);
        return out.toString();
    }

    public static double printCompressionRatio(String compressed, String original, double bits) {
        var v = (original.length() * bits) / compressed.length();
        System.out.println("Compression Ratio: " + v);
        return v;
    }

    public static double printCompressionRatio(ArrayList<String> compressed, String original, int bits) {
        StringBuilder stringBuilder = new StringBuilder();
        compressed.forEach(stringBuilder::append);
        try {
            FileWriter fileWriter = new FileWriter("C:\\Users\\danie\\OneDrive\\Documents\\School\\Binary to Input\\src\\compressed.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return printCompressionRatio(stringBuilder.toString(), original, bits);
    }

    public static int bestBits(String original, int startingBitVal) {
        double previous = 0;
        double current = printCompressionRatio(LZWCompression(original, startingBitVal), original, startingBitVal);
        int i = 1;
        int bestBit = startingBitVal;
        while (current > previous) {
            previous = current;
            bestBit = startingBitVal + i;
            current = printCompressionRatio(LZWCompression(original, bestBit), original, bestBit);
            i++;
        }
        return bestBit;
    }

    public static String LZWUncompress(String compressed, int bits) {
        TreeMap<Integer, String> dictionary = new TreeMap<>();
        ArrayList<String> portions = new ArrayList<>();
        ArrayList<String> output = new ArrayList<>();

        String current = "0";
        String next = "0";

        int bitRate = (int)Math.pow(2, bits - 1);

        for (int i = 0; i < bitRate; i++) {
            dictionary.put(i, (char) i + "");
        }

        for (int i = 0; i < compressed.length(); i += bits) {
            if (i + bits <= compressed.length()) {
                portions.add(compressed.substring(i, i + bits));
            }
        }

        for (int i = 0; i < portions.size(); i++) {
            boolean condition = true;
            current = (portions.get(i));
            next = (i + 1 < portions.size()) ? (portions.get(i + 1)) : "0";

            while (dictionary.containsKey(Integer.parseInt(current + next, 2)) && portions.size() > i) {
                current += next;
                if (i + 1 < portions.size()) {
                    next = next + portions.get(i + 1);
                }
                i++;
                condition = false;
            }

            if (condition) {
//                String value = (char)Integer.parseInt(current, 2)
//                        + "" + (char)Integer.parseInt(next, 2);
                current = determineValue(dictionary, Integer.parseInt(current, 2), false);
                next = determineValue(dictionary, Integer.parseInt(next, 2), true);
//                System.out.println("ADD TO DICTIONARY-----------------------------------" + (current + next));
                dictionary.put((bitRate - 1) + i, (current + next));
            } else if (i + 1 < portions.size()) {
                current = determineValue(dictionary, Integer.parseInt(current, 2), false);
                next = determineValue(dictionary, Integer.parseInt(portions.get(i + 1), 2), true);
//                System.out.println("ADD TO DICTIONARY-----------------------------------" + (current + next));
                dictionary.put((bitRate - 1) + i, (current + next));
            }

            output.add((current));
        }

        output.forEach(System.out::print);
//        for (Integer integer : dictionary.keySet()) {
//            System.out.println(integer + "--------------------------" + dictionary.get(integer));
//        }
        StringBuilder out = new StringBuilder();
        output.forEach(out::append);
        return out.toString();
    }

    private static String determineValue(TreeMap<Integer, String> dictionary, int seek, boolean isNext) {
//        System.out.println("LOOKING FOR VALUE-------" + seek);

        if ( seek == 0 ) return "";

        if (dictionary.containsKey(seek)) {
//            System.out.println("FOUND VALUE-----------" + dictionary.get(seek));
            return isNext ? dictionary.get(seek).substring(0, 1) : dictionary.get(seek);
        }

//        System.out.println("FOUND VALUE----------" + (char)seek + "");
        return (char)seek + "";
    }

    public static String uncompressFile(File file) throws FileNotFoundException {
        String s = readFromFile(file);
        Scanner scan = new Scanner(s);
        int bits = scan.nextInt();
        return LZWUncompress(s, bits);
    }




}
