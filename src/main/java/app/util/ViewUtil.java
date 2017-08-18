package app.util;

import org.eclipse.jetty.http.HttpStatus;

import spark.Request;
import spark.Response;
import spark.Route;

public class ViewUtil {

    public static Route notAcceptable = (Request request, Response response) -> {
        response.status(HttpStatus.NOT_ACCEPTABLE_406);
        return "Not acceptable";
    };

    public static Route notFound = (Request request, Response response) -> {
        response.status(HttpStatus.NOT_FOUND_404);
        return "Not found";
    };

}
