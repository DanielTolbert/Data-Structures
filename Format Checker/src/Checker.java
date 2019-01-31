import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Checker {

    private static String getFileContents( String filename ) throws IOException {

        return new String( Files.readAllBytes( Paths.get( filename ) ) );

    };

    private static void writeFileContents( String filename, String data ) throws IOException  {

        FileWriter fileWriter = new FileWriter( new File( filename ) );
        fileWriter.write( data );
        fileWriter.close();

    }


    public static void main( String[] args ) {
        Pattern p  = Pattern.compile( "<\\?xml version=\"1.0\" encoding=\"utf-8\"\\?>\n<resources>\n    <string name=\"\\w+\">\\w+</string>\n</resources>\n<\\\\?xml version=\"1.0\" encoding=\"utf-8\"\\?>" );
        try {
            String information = getFileContents( "C:\\Users\\Daniel T\\Desktop\\robotics\\Format Checker\\src\\xml demo" );
            String[] info = information.split( "\r\n" );
            Matcher matcher = p.matcher( information );
            System.out.println( matcher.matches() );
            System.out.println( information );
            System.out.println( p.toString() );
        }catch( IOException io ) {
            io.printStackTrace();
        }

    }

}
