package com.projet_pojo.pojo;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;

/**
 * Classe représentant un livre
 */
public class Book implements Serializable {

    private String
            author,
            title,
            publisher,
            description;

    private Date publishingDate;

    private double price;

    /**
     * Constructeur
     * @param author : auteur du livre
     * @param title : titre du livre
     * @param publisher : éditeur du livre
     * @param description : description du livre
     * @param publishingDate : date de publication du livre
     * @param price : prix du livre
     */
    public Book(String author, String title, String publisher, String description, Date publishingDate, double price) {
        this.author = author;
        this.title = title;
        this.publisher = publisher;
        this.description = description;
        this.publishingDate = publishingDate;
        this.price = price;
    }

    /**
     * Constructeur par défaut
     */
    public Book() {
        this("", "", "", "", new Date(), 0.0);
    }

    /**
     * Récupère l'auteur du livre
     * @return
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Modifie l'auteur du livre
     * @param author : auteur du livre
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Récupère le titre du livre
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     * Modifie le titre du livre
     * @param title : titre du livre
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Récupère l'éditeur du livre
     * @return
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * Modifie l'éditeur du livre
     * @param publisher : éditeur du livre
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * Récupère la description du livre
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * Modifie la description du livre
     * @param description : description du livre
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Récupère la date de publication du livre
     * @return
     */
    public Date getPublishingDate() {
        return publishingDate;
    }

    /**
     * Modifie la date de publication du livre
     * @param publishingDate : date de publication du livre
     */
    public void setPublishingDate(Date publishingDate) {
        this.publishingDate = publishingDate;
    }

    /**
     * Récupère le prix du livre
     * @return
     */
    public double getPrice() {
        return price;
    }

    /**
     * Modifie le prix du livre
     * @param price : prix du livre
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Récupère le livre sous forme de chaîne de caractères
     * @return
     */
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
