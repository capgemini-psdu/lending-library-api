package app.util;

import spark.Request;

public class RequestUtil {

    public static String getParamIsbn(Request request) {
        return request.params("isbn");
    }

    public static boolean clientAcceptsJson(Request request) {
        String accept = request.headers("Accept");
        return accept != null && accept.contains("application/json");
    }
    
}
