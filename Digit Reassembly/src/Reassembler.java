import java.util.Scanner;

import static java.lang.Math.pow;

public class Reassembler {



    public static void main( String[] args ) {
       Scanner scanner = new Scanner( System.in );
       long sum = 0;
        do {
           String num = scanner.next();
           int times = scanner.nextInt();
           long tt = (long)times;
           String[] stuff = num.split( "(?!^)" );
           for ( int i = 0; i < stuff.length - tt; i++ ) {

               StringBuilder toAdd = new StringBuilder(  );
                   for ( int j = i; i < tt; i++) {
                       toAdd.append( stuff[j] );
                   }
               sum += Long.parseLong( toAdd.toString() );
           }
       }while( scanner.hasNext() );

        System.out.println( sum );
    }

}
