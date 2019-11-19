import java.io.Serializable;
import java.util.Objects;

public class Book implements Comparable {
    private String author;
    private String country;
    private String imageLink;
    private String language;
    private String title;
    private int year;
    private int pages;

    @Override
    public int hashCode() {
        return Objects.hash(author, country, imageLink, language, title, year, pages);
    }

    public Book(String author, String country, String imageLink, String language, String title, int year, int pages) {
        this.author = author;
        this.country = country;
        this.imageLink = imageLink;
        this.language = language;
        this.title = title;
        this.year = year;
        this.pages = pages;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return year == book.year &&
                pages == book.pages &&
                Objects.equals(author, book.author) &&
                Objects.equals(country, book.country) &&
                Objects.equals(imageLink, book.imageLink) &&
                Objects.equals(language, book.language) &&
                Objects.equals(title, book.title);
    }

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", country='" + country + '\'' +
                ", imageLink='" + imageLink + '\'' +
                ", language='" + language + '\'' +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", pages=" + pages +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        Book otherBook = (Book)o;

        if (author.compareTo(otherBook.author) == 0) {
            if (Integer.valueOf(year).compareTo(Integer.valueOf(otherBook.year)) == 0) {
                return title.compareTo(title);
            } else {
                return Integer.valueOf(year).compareTo(Integer.valueOf(otherBook.year));
            }
        } else {
            return author.compareTo(otherBook.author);
        }

    }
}
