package co.speechat.data;

import java.util.Date;

/**
 * TODO: Document me
 * Author: Iurii Lytvynenko
 */
public class Message {
    private final String author;
    private final String message;
    private final Date date;

    public Message(String message, String author) {
        this.date = new Date();
        this.message = message;
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public String getMessage() {
        return message;
    }

    public Date getDate() {
        return date;
    }
}
