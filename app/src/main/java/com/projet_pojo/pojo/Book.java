package com.projet_pojo.pojo;

import android.os.Parcelable;

import java.util.Date;

public class Book {

    private String
            author,
            title,
            publisher,
            description;

    private Date publishingDate;

    private double price;

    public Book(String author, String title, String publisher, String description, Date publishingDate, double price) {
        this.author = author;
        this.title = title;
        this.publisher = publisher;
        this.description = description;
        this.publishingDate = publishingDate;
        this.price = price;
    }

    public Book() {
        this("undified", "undified", "undified", "undified", new Date(), 0.0);
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPublishingDate() {
        return publishingDate;
    }

    public void setPublishingDate(Date publishingDate) {
        this.publishingDate = publishingDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", publisher='" + publisher + '\'' +
                ", description='" + description + '\'' +
                ", publishingDate=" + publishingDate +
                ", price=" + price +
                '}';
    }
}
