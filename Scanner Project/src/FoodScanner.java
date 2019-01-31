import java.io.File;
import java.util.Scanner;

public class FoodScanner {


    public FoodScanner() {
        start();
    }


    public static void main(String[] args) {
        new FoodScanner();
//        System.out.println( 10/20 +" " );
    }


    public void start() {

        double highScore = 0.0;
//        double score = 0;
        int timesEaten = 0;
        String winner = "";
        int totalTimes = 0;

        Scanner scan = null;
        try {
            File f = new File( "C:\\Users\\Daniel T\\Desktop\\FRC-Robot\\Scanner Project\\out\\production\\Scanner Project\\Food.txt" );
            scan = new Scanner( f );
            do {
                String name = scan.next();
                scan.nextLine();
                int tempTimes = scan.nextInt();
                double tempScore = scan.nextDouble();
                totalTimes += tempTimes;
                if ( tempScore > highScore ) {
                    winner = name;
                    timesEaten = tempTimes;
                    highScore = tempScore;
                }
            } while ( scan.hasNext() );
double avg = ( ( ( double )timesEaten / totalTimes ) ) * 100;
            System.out.println( "Your favorite food is " + winner + "\n...and you ate it " + Math.round( avg )+ "% of the time!");
            if ( ( double )timesEaten / totalTimes * 100 >= 50 ) {
                System.out.println( "...that's just weird man." );
            }
        } catch ( Exception e ) {
            e.printStackTrace();

        } finally {
            if ( scan != null ) {
                scan.close();
            }
        }

    }

}
