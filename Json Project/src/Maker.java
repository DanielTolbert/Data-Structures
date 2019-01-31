import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Maker {

    public static void main(String[] args) {
        try{
            jsonMaker();
        }catch ( Exception e ) {

        }
    }

    public static void jsonMaker() throws Exception{

        File f = new File( "thing.json" );

        BufferedWriter bufferedWriter = new BufferedWriter( new FileWriter( f.getName() ) );

        URL url = new URL( "https://www.baeldung.com/java-write-to-file" );
        Scanner s = new Scanner( System.in );
        String input = s.next();
//        InputStream inputStream = System.in;
        bufferedWriter.write( input );
        bufferedWriter.close();
        f.createNewFile();



    }


    public static void iDidntKnowWhatGsonWasBefore() {



    }


}
