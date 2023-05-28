package com.projet_pojo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.projet_pojo.R;
import com.projet_pojo.pojo.Book;
import com.projet_pojo.pojo.BookItemAdapter;
import com.projet_pojo.pojo.Helper;

import java.util.ArrayList;
import java.util.List;

public class LibraryActivity extends AppCompatActivity {

    Helper helper;
    Button newBookButton, modifyBookButton, deleteBookButton;
    ListView booksListView;

    int selectedBookId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.library_activity);

        // Initialisation de l'helper
        helper = new Helper(this);

        // Initialisation des champs
        initFields();

        // Rafraichissement de la bibliothèque
        refreshLibrary();

        // Afficher un message si la bibliothèque est vide
        if(helper.getAllBooks().isEmpty()) {
            Toast.makeText(this, "La bibliothèque est vide", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "La bibliothèque contient " + helper.getAllBooks().size() + " livres", Toast.LENGTH_SHORT).show();
        }

        // Sélection d'un livre
        onClickItemSelect(booksListView);

        // Création d'un nouveau livre
        onClickNewBook(newBookButton);

        // Modification d'un livre
        onClickModifyBook(modifyBookButton);

        // Suppression d'un livre
        onClickDeleteBook(deleteBookButton);
    }

    @Override
    public void onBackPressed() {
        // Retour à la bibliothèque
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    public void initFields() {
        newBookButton = findViewById(R.id.new_book);
        modifyBookButton = findViewById(R.id.modify_book);
        deleteBookButton = findViewById(R.id.delete_book);
        booksListView = findViewById(R.id.library_list);
    }

    public void refreshLibrary() {

        // Récupération des livres
        List<Book> books = new ArrayList<>();
        books = helper.getAllBooks();

        // Création d'une liste d'items de livres
        List<Book> booksList = new ArrayList<>();
        for (Book book : books) {
            booksList.add(book);
        }

        // Création d'un adapter pour la liste d'items de livres
        BookItemAdapter bookItemAdapter = new BookItemAdapter(this, booksList);

        // Ajout de l'adapter à la liste de livres
        booksListView.setAdapter(bookItemAdapter);
    }

    public void onClickItemSelect(ListView listView) {
        listView.setOnItemClickListener((parent, view, position, id) -> {
            selectedBookId = position;
        });
    }

    public void onClickNewBook(Button button) {
        button.setOnClickListener(v -> {
            Intent intent = new Intent(this, BookActivity.class);
            startActivity(intent);
            finish();
        });
    }

    public void onClickModifyBook(Button button) {
        button.setOnClickListener(v-> {
            // Si la bibliothèque est vide
            if(helper.getAllBooks().isEmpty()) {
                Toast.makeText(this, "La bibliothèque est vide.", Toast.LENGTH_SHORT).show();
            } else {

                // Si aucun livre n'est sélectionné
                if(selectedBookId == -1) {
                    Toast.makeText(this, "Aucun livre sélectionné.", Toast.LENGTH_SHORT).show();
                } else {

                    // Récupération du livre sélectionné
                    Book book = helper.getAllBooks().get(selectedBookId);

                    // Modification du livre sélectionné
                    Intent intent = new Intent(this, BookActivity.class);

                    // Sérialisation du livre
                    Bundle bookBundle = new Bundle();
                    bookBundle.putSerializable("book", book);

                    intent.putExtra("book", bookBundle);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    public void onClickDeleteBook(Button button) {
        button.setOnClickListener(v -> {

            // Si la bibliothèque est vide
            if(helper.getAllBooks().isEmpty()) {
                Toast.makeText(this, "La bibliothèque est vide.", Toast.LENGTH_SHORT).show();
            } else {

                // Si aucun livre n'est sélectionné
                if(selectedBookId == -1) {
                    Toast.makeText(this, "Aucun livre sélectionné.", Toast.LENGTH_SHORT).show();
                } else {

                    // Suppression du livre sélectionné
                    helper.deleteBook(helper.getAllBooks().get(selectedBookId));

                    // Rafraichissement de la bibliothèque
                    refreshLibrary();

                    // Affichage d'un message de confirmation
                    Toast.makeText(this, "Livre supprimé.", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }
}