package net.kopuz.chatapp.entity;

public class Message {
    private String type;
    private String content;
    private String author;

    public Message() {
    }

    public String getAuthor() {
        return author;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
