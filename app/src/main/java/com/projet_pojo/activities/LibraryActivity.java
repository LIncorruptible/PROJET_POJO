package com.projet_pojo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.projet_pojo.R;
import com.projet_pojo.pojo.Book;
import com.projet_pojo.pojo.Helper;

import java.util.ArrayList;
import java.util.List;

public class LibraryActivity extends AppCompatActivity {

    Helper helper = new Helper(this);
    Button newBookButton, ModifyBookButton, DeleteBookButton;
    ListView booksListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.library_activity);

        // Initialisation des champs
        initFields();

        // Rafraichissement de la bibliothèque
        refreshLibrary();

        onClickNewBook();
        onClickModifyBook();
        onClickDeleteBook();
    }

    public void initFields() {
        newBookButton = findViewById(R.id.new_book);
        ModifyBookButton = findViewById(R.id.modify_book);
        DeleteBookButton = findViewById(R.id.delete_book);
        booksListView = findViewById(R.id.library_list);
    }

    public void refreshLibrary() {

        List<Book> books = new ArrayList<>();
        books = helper.getAllBooks();
    }

    public void onClickNewBook(View view) {
        view.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(LibraryActivity.this, BookActivity.class);
                    intent.putExtra("Book", (Parcelable) null);
                    startActivity(intent);
                }
            }
        );
    }

    public void onClickModifyBook() {
        // Récupération de l'id du livre sélectionné
        booksListView.setOnItemClickListener((parent, view, position, id) -> {
            ModifyBookButton.setOnClickListener(v -> {
                Book book = (Book) parent.getItemAtPosition(position);
                Intent intent = new Intent(this, BookActivity.class);
                intent.putExtra("Book", (Parcelable) book);
                startActivity(intent);
            });
        });
    }

    public void onClickDeleteBook() {
        // Récupération de l'id du livre sélectionné
        booksListView.setOnItemClickListener((parent, view, position, id) -> {
            DeleteBookButton.setOnClickListener(v -> {
                Book book = (Book) parent.getItemAtPosition(position);
                helper.deleteBook(book);
                refreshLibrary();
            });
        });
    }
}