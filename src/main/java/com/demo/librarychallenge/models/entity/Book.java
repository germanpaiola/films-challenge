package com.demo.librarychallenge.models.entity;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    private String title;
    private String author;
    private String country;
    private String releaseDate;
    private String publisher;
    private String type;


    @Override
    public int hashCode() {
        int result = 17;
        if (title != null) {
            result = 31 * result + title.hashCode();
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Book))
            return false;
        Book other = (Book) o;
        return (this.title == null && other.title == null)
                || (this.title != null && this.title.equals(other.title));
    }
}
