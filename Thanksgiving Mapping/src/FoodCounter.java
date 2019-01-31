import java.awt.*;
import java.io.File;
import java.util.*;

public class FoodCounter {


    public static Map<String, Integer> hm = new HashMap<String, Integer>();
    public static Map<String, Integer> tm = new TreeMap<String, Integer>();

    public static Map<Integer, TreeSet<String>> htm = new HashMap<>();
    public static Map<Integer, TreeSet<String>> ttm = new TreeMap<>();


    private static void firstThing() {
        Scanner s = null;
        int elements = 0;
        try {
            File f = new File( "C:\\Users\\Daniel T\\Desktop\\robotics\\Thanksgiving Mapping\\src\\FoodDoc.txt" );
            s = new Scanner( f );
        } catch ( Exception e ) {
            System.err.print( "NO FILE FOUNDDDD" );
            s.close();
        }

        do {
            String ss = s.nextLine();
            if ( !tm.containsKey( ss ) ) {
                tm.put( ss, 1 );
                hm.put( ss, 1 );
            } else {
                tm.put( ss, 1 + tm.get( ss ) );
                hm.put( ss, 1 + hm.get( ss ) );
            }
        }while ( s.hasNext() );
//        elements++;System.out.printf( "%s total elements", elements );
        s.close();

    }

    private static void printResults( Map<String,Integer> tm ) {

        //TODO
        for ( String ss : tm.keySet() ) {
            System.out.printf( "%-25s%s\n", ss, tm.get( ss ) );
        }



    }

    private static void secondThing() {

        firstThing();

        for ( String key : tm.keySet() ) {

            Integer many = hm.get( key );

            if ( !ttm.containsKey( many ) ) {
                ttm.put( many, new TreeSet<>(  ) );
                ttm.get( many ).add( key );

                htm.put( many, new TreeSet<>(  ) );
                htm.get( many ).add( key );
            } else {
                ttm.get( many ).add( key );
                htm.get( many ).add( key );
            }

        }

    }

    private static void printResultsTwo( Map<Integer, TreeSet<String>> map ) {

        for ( Integer i : map.keySet() ) {
            System.out.printf( "%s:\t", i );
            StringBuilder sb = new StringBuilder(  );
            sb.append( "[" );
            for ( String thing : map.get( i ) ) {
                sb.append( thing + ", " );
            }
            sb.deleteCharAt( sb.lastIndexOf( "," ) );
            sb.deleteCharAt( sb.lastIndexOf( " " ) );
            sb.append( "]" );
            System.out.println( sb.toString() );
        }

    }

    public static void main ( String[] args ) {

       secondThing();
       printResultsTwo( ttm );
        firstThing();
        printResults( tm );

    }


}

