package MiscellaneousPatterns.DaoPattern;

import java.util.ArrayList;
import java.util.List;

public class Dao {
    public static void main(String[] args) {
        BookDao bookDao = new BookDaoImpl();

        bookDao.getAllBooks().forEach(b -> System.out.println("Book ISBN : " + b.getIsbn()));

        Books book = bookDao.getAllBooks().get(1);
        book.setBookName("Algorithms");
        bookDao.saveBook(book);

        bookDao.getAllBooks().forEach(books -> System.out.println(books.getBookName() + " : ISBN-" + books.getIsbn()));

    }
}

class Books {
    private int isbn;
    private String bookName;

    public Books() {
    }

    public Books(int isbn, String bookName) {
        this.isbn = isbn;
        this.bookName = bookName;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}

interface BookDao {
    List<Books> getAllBooks();
    Books getBookByIsbn(int isbn);
    void saveBook(Books book);
    void deleteBook(Books book);
}

class BookDaoImpl implements BookDao {

    private List<Books> books;

    public BookDaoImpl() {
        books = new ArrayList<>();
        books.add(new Books(1, "Java"));
        books.add(new Books(2, "Python"));
        books.add(new Books(3, "Android"));
    }

    @Override
    public List<Books> getAllBooks() {
        return books;
    }

    @Override
    public Books getBookByIsbn(int isbn) {
        return books.get(isbn);
    }

    @Override
    public void saveBook(Books book) {
        books.add(book);
    }

    @Override
    public void deleteBook(Books book) {
        books.remove(book);
    }
}