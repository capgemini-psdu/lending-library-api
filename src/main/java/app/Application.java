package app;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.debug.DebugScreen.enableDebugScreen;

import app.book.BookController;
import app.book.BookDao;
import app.util.Path;
import app.util.ViewUtil;

public class Application {

    // Declare dependencies
    public static BookDao bookDao;

    public static void main(String[] args) {

        // Instantiate your dependencies
        bookDao = new BookDao();

        // Configure Spark
        port(5678);
        enableDebugScreen();

        // Set up routes
        get(Path.Web.BOOKS,          BookController.fetchAllBooks);
        get(Path.Web.ONE_BOOK,       BookController.fetchOneBook);
        get("*",                     ViewUtil.notFound);
    }

}
