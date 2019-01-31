import java.time.Clock;
import java.util.Scanner;

public class Circular {
    private static String[] clock = new String[]{"Midnight","One AM", "Two AM", "Three AM", "Four AM", "Five AM", "Six AM", "Seven AM", "Eight AM", "Nine AM", "Ten AM",
                                                    "Eleven AM", "Noon", "One PM", "Two PM", "Three PM", "Four PM", "Five PM", "Six PM", "Seven PM",
            "Eight PM", "Nine PM", "Ten PM", "Eleven PM" };
    


    
    
        public static void Main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("What is the current time as an int? (Midnight = 0) \"Stop\" stops the program");
        String answer = scan.nextLine();
        if (answer.equalsIgnoreCase("stop")) {
            System.out.println("Ended.");
            return;
        }
        int index = Integer.parseInt(answer);


        do {


            System.out.println("The current time is " + clock[index]);
            System.out.println("How much time would you like to add?");
            
            String add = scan.nextLine();
            if (add.equalsIgnoreCase("stop")) {
            System.out.println("Ended.");
            break;
            }
            int toAdd = (Integer.parseInt(add));
            index += toAdd;
            index %= 24;

            if(toAdd < 0) {
                index += 24;
            }


        } while (true);
    }
}