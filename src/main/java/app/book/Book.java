package app.book;

import lombok.*;

@Value// All fields are private and final. Getters (but not setters) are generated (https://projectlombok.org/features/Value.html)
@Builder
public class Book {
    String title;
    String author;
    String isbn;
}
