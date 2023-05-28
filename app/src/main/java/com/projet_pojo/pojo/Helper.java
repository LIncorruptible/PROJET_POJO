package com.projet_pojo.pojo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Classe permettant les intéractions avec la base de données SQLite
 */
public class Helper extends SQLiteOpenHelper {

    /**
     * Constructeur
     * @param context : contexte de l'application
     */
    public Helper(@Nullable Context context) {
        super(context, "BDDAppli", null, 1);
    }

    /**
     * Création de la base de données
     * @param db : base de données
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE books " +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "author VARCHAR(20) NOT NULL, " +
                "title VARCHAR(20) NOT NULL," +
                "publisher VARCHAR(20) NOT NULL," +
                "description VARCHAR(20) NOT NULL," +
                "publishingDate DATE NOT NULL," +
                "price REAL NOT NULL)");
    }

    /**
     * Mise à jour de la base de données
     * @param db : base de données
     * @param i : ancienne version
     * @param i1 : nouvelle version
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS books");
        onCreate(db);
    }

    /**
     * Méthode d'insertion d'un livre dans la base de données
     * @param book : livre à insérer
     */
    public void insertBook(Book book) {
        SQLiteDatabase db = null;
        try {
            db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();

            cv.put("author", book.getAuthor());
            cv.put("title", book.getTitle());
            cv.put("publisher", book.getPublisher());
            cv.put("description", book.getDescription());
            cv.put("publishingDate", String.valueOf(book.getPublishingDate()));
            cv.put("price", book.getPrice());

            db.insert("books", null, cv);
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    /**
     * Méthode de suppression d'un livre de la base de données
     * @param id : id du livre à supprimer
     */
    public void deleteBook(int id) {
        SQLiteDatabase db = null;
        try {
            db = this.getWritableDatabase();
            db.delete("books", "_id=?", new String[]{String.valueOf(id)});
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    /**
     * Méthode de suppression d'un livre de la base de données
     * @param book : livre à supprimer
     */
    public void deleteBook(Book book) {
        SQLiteDatabase db = null;
        try {
            db = this.getWritableDatabase();
            db.delete("books", "author=? AND title=? AND publisher=? AND description=? AND publishingDate=? AND price=?", new String[]{book.getAuthor(), book.getTitle(), book.getPublisher(), book.getDescription(), String.valueOf(book.getPublishingDate()), String.valueOf(book.getPrice())});
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    /**
     * Méthode de mise à jour d'un livre de la base de données
     * @param oldBook : ancien livre
     * @param newBook : nouveau livre
     */
    public void updateBook(Book oldBook, Book newBook) {
        SQLiteDatabase db = null;
        try {
            db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();

            cv.put("author", newBook.getAuthor());
            cv.put("title", newBook.getTitle());
            cv.put("publisher", newBook.getPublisher());
            cv.put("description", newBook.getDescription());
            cv.put("publishingDate", String.valueOf(newBook.getPublishingDate()));
            cv.put("price", newBook.getPrice());

            db.update("books", cv, "_id=?", new String[]{String.valueOf(getBookId(oldBook))});
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    /**
     * Méthode de récupération de l'id d'un livre
     * @param book : livre dont on veut l'id
     * @return
     */
    public int getBookId(Book book) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM books WHERE author=? AND title=? AND publisher=? AND description=? AND publishingDate=? AND price=?", new String[]{book.getAuthor(), book.getTitle(), book.getPublisher(), book.getDescription(), String.valueOf(book.getPublishingDate()), String.valueOf(book.getPrice())});
        int id = -1;
        if (cursor.moveToFirst()) {
            id = cursor.getInt(0);
        }
        cursor.close();

        return id;
    }

    /**
     * Méthode de récupération de tous les livres de la base de données
     * @return : liste des livres
     */
    public List<Book> getAllBooks() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM books", null);
        List<Book> books = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Book book = new Book();
                book.setAuthor(cursor.getString(1));
                book.setTitle(cursor.getString(2));
                book.setPublisher(cursor.getString(3));
                book.setDescription(cursor.getString(4));
                book.setPublishingDate(new Date(cursor.getString(5)));
                book.setPrice(cursor.getDouble(6));
                books.add(book);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return books;
    }

    /**
     * Méthode de vérification de l'existence d'un livre dans la base de données
     * @param book : livre à vérifier
     * @return
     */
    public boolean isBookExistingInBDD(Book book) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM books WHERE author=? AND title=? AND publisher=? AND description=? AND publishingDate=? AND price=?", new String[]{book.getAuthor(), book.getTitle(), book.getPublisher(), book.getDescription(), String.valueOf(book.getPublishingDate()), String.valueOf(book.getPrice())});
        boolean isBookInBDD = false;
        if (cursor.moveToFirst()) {
            isBookInBDD = true;
        }
        cursor.close();

        return isBookInBDD;
    }
}