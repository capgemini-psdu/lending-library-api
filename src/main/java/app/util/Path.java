package app.util;

import lombok.Getter;

public class Path {

    public static class Web {
        @Getter public static final String BOOKS = "/books/";
        @Getter public static final String ONE_BOOK = "/books/:isbn";
    }

}
