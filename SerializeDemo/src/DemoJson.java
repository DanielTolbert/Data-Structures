import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;

public class DemoJson {

    public static void main(String[] args) {
        Gson gson = new Gson();
        Book[] books = new Book[0];

        try {
            books = gson.fromJson(new FileReader("C:\\Users\\danie\\OneDrive\\Documents\\School\\Java-Class\\SerializeDemo\\src\\books.json"),
                    Book[].class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Arrays.sort(books);

        for (Book book : books) {
            System.out.println(book);
        }
    }

}
