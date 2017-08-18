package app.book;

import static app.Application.bookDao;
import static app.util.JsonUtil.dataToJson;
import static app.util.RequestUtil.clientAcceptsJson;
import static app.util.RequestUtil.getParamIsbn;

import app.util.ViewUtil;
import spark.Request;
import spark.Response;
import spark.Route;

public class BookController {

    public static Route fetchAllBooks = (Request request, Response response) -> {
        if (clientAcceptsJson(request)) {
            return dataToJson(bookDao.getAllBooks());
        }
        return ViewUtil.notAcceptable.handle(request, response);
    };

    public static Route fetchOneBook = (Request request, Response response) -> {
        if (clientAcceptsJson(request)) {
            Book book = bookDao.getBookByIsbn(getParamIsbn(request));
            if (book != null) {
                return dataToJson(book);
            } else {
                return ViewUtil.notFound.handle(request, response);
            }            
        }
        return ViewUtil.notAcceptable.handle(request, response);
    };
}
