package com.projet_pojo.pojo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Helper extends SQLiteOpenHelper {

    public Helper(@Nullable Context context) {
        super(context, "BDDAppli", null, 1);
    }

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

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS books");
        onCreate(db);
    }

    public void insertBook(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("author", book.getAuthor());
        cv.put("title", book.getTitle());
        cv.put("publisher", book.getPublisher());
        cv.put("description", book.getDescription());
        cv.put("publishingDate", String.valueOf(book.getPublishingDate()));
        cv.put("price", book.getPrice());

        db.insert("books", null, cv);
        db.close();
    }

    public void deleteBook(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete("books", "_id=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void deleteBook(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete("books", "author=? AND title=? AND publisher=? AND description=? AND publishingDate=? AND price=?", new String[]{book.getAuthor(), book.getTitle(), book.getPublisher(), book.getDescription(), String.valueOf(book.getPublishingDate()), String.valueOf(book.getPrice())});
        db.close();
    }

    public int nbBooksOnBDD() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM books", null);
        int nbBooks = cursor.getCount();
        cursor.close();
        db.close();

        return nbBooks;
    }

    public Book getBook(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM books WHERE _id=?", new String[]{String.valueOf(id)});
        Book book = new Book();
        if (cursor.moveToFirst()) {
            book.setAuthor(cursor.getString(1));
            book.setTitle(cursor.getString(2));
            book.setPublisher(cursor.getString(3));
            book.setDescription(cursor.getString(4));
            book.setPublishingDate(new Date(cursor.getString(5)));
            book.setPrice(cursor.getDouble(6));
        }
        cursor.close();
        db.close();

        return book;
    }

    public int getBookId(Book book) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM books WHERE author=? AND title=? AND publisher=? AND description=? AND publishingDate=? AND price=?", new String[]{book.getAuthor(), book.getTitle(), book.getPublisher(), book.getDescription(), String.valueOf(book.getPublishingDate()), String.valueOf(book.getPrice())});
        int id = -1;
        if (cursor.moveToFirst()) {
            id = cursor.getInt(0);
        }
        cursor.close();
        db.close();

        return id;
    }

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
        db.close();

        return books;
    }
}