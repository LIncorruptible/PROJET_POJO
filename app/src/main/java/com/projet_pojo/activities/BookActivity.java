package com.projet_pojo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.projet_pojo.R;
import com.projet_pojo.pojo.Book;
import com.projet_pojo.pojo.Helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Activité permettant d'ajouter ou de modifier un livre via un formulaire
 */
public class BookActivity extends AppCompatActivity {

    Helper helper;
    EditText authorEditText, titleEditText, publisherEditText, descriptionEditText, publishingDateEditText, priceEditText;
    Button saveButton, cancelButton;

    /**
     * Méthode appelée à la création de l'activité
     * @param savedInstanceState : état de l'activité
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_activity);

        helper = new Helper(this);

        // Récupération de l'Intent
        Intent intent = getIntent();

        // Initialisation des champs
        initFields();

        if(intent.hasExtra("book")) {
            // Récupération du livre
            Bundle bundle = intent.getBundleExtra("book");

            Book book = (Book) bundle.getSerializable("book");

            // Placement des informations du livre dans les champs
            placeInformations(book);

            // Modification d'un livre
            onClickModify(saveButton, book);
        } else {
            // Création d'un nouveau livre
            onClickSave(saveButton);
        }

        onClickCancel(cancelButton);
    }

    /**
     * Méthode appelée lors de l'appui sur le bouton retour
     */
    @Override
    public void onBackPressed() {
        // Retour à la bibliothèque
        returnToLibrary();
    }

    /**
     * Méthode permettant d'initialiser les champs
     */
    public void initFields() {
        authorEditText = findViewById(R.id.edit_author);
        titleEditText = findViewById(R.id.edit_title);
        publisherEditText = findViewById(R.id.edit_publisher);
        descriptionEditText = findViewById(R.id.edit_description);
        publishingDateEditText = findViewById(R.id.edit_publishing_date);
        priceEditText = findViewById(R.id.edit_price);
        saveButton = findViewById(R.id.save_btn);
        cancelButton = findViewById(R.id.cancel_btn);
    }

    /**
     * Méthode remplissant tous les champs avec les informations du livre
     * @param book : livre
     */
    private void placeInformations(Book book) {
        authorEditText.setText(book.getAuthor());
        titleEditText.setText(book.getTitle());
        publisherEditText.setText(book.getPublisher());
        descriptionEditText.setText(book.getDescription());

        // récupération de la date de publication du livre
        Date publishingDate = book.getPublishingDate();
        // Formatage de la date au format "dd/MM/yyyy"
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = dateFormat.format(publishingDate);
        // Remplissage de EditText
        publishingDateEditText.setText(formattedDate);

        priceEditText.setText(String.valueOf(book.getPrice()));
    }

    /**
     * Méthode d'écoute du bouton "Annuler"
     * @param button : bouton "Annuler"
     */
    public void onClickCancel(Button button) {
        button.setOnClickListener(v -> {
            // Retour à la bibliothèque
            returnToLibrary();
        });
    }

    /**
     * Méthode d'écoute et action du bouton "Enregistrer"
     * @param button : bouton "Enregistrer"
     */
    public void onClickSave(Button button) {

        button.setOnClickListener(v -> {
            Book book = new Book();
            book.setAuthor(authorEditText.getText().toString());
            book.setTitle(titleEditText.getText().toString());
            book.setPublisher(publisherEditText.getText().toString());
            book.setDescription(descriptionEditText.getText().toString());
            book.setPublishingDate(convertEditTextToDate(publishingDateEditText));
            book.setPrice(Double.parseDouble(priceEditText.getText().toString()));

            if (helper.isBookExistingInBDD(book)) {
                Toast.makeText(this, R.string.existing_book, Toast.LENGTH_SHORT).show();
            } else {
                if(allFieldsAreFilled()) {
                    // Ajout du livre dans la base de données
                    helper.insertBook(book);
                    Toast.makeText(this, R.string.book_added, Toast.LENGTH_SHORT).show();
                    // Retour à la bibliothèque
                    returnToLibrary();
                } else {
                    Toast.makeText(this, R.string.complete_all_fields, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * Méthode d'écoute et action du bouton "Modifier"
     * @param button : bouton "Modifier"
     * @param oldBook : ancien livre qui va être modifié
     */
    public void onClickModify(Button button, Book oldBook) {
        button.setOnClickListener(v -> {
            Book book = new Book();
            book.setAuthor(authorEditText.getText().toString());
            book.setTitle(titleEditText.getText().toString());
            book.setPublisher(publisherEditText.getText().toString());
            book.setDescription(descriptionEditText.getText().toString());
            book.setPublishingDate(convertEditTextToDate(publishingDateEditText));
            book.setPrice(Double.parseDouble(priceEditText.getText().toString()));

            if(allFieldsAreFilled()) {
                // Modification du livre dans la base de données
                // si sa modification ne crée pas de doublon
                if (!helper.isBookExistingInBDD(book)) {
                    helper.updateBook(oldBook, book);
                    Toast.makeText(this, R.string.book_modified, Toast.LENGTH_SHORT).show();
                    // Retour à la bibliothèque
                    returnToLibrary();
                } else {
                    Toast.makeText(this, R.string.existing_book, Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, R.string.complete_all_fields, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Méthode permettant de retourner à l'activité LibraryActivity
     */
    public void returnToLibrary() {
        Intent intent = new Intent(this, LibraryActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Méthode permettant de convertir un EditText en Date
     * @param editText : EditText Date à convertir
     * @return Date : date convertie
     */
    public Date convertEditTextToDate(EditText editText) {
        String str_date = editText.getText().toString();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return format.parse(str_date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Méthode permettant de vérifier si tous les champs sont remplis
     * @return boolean : true si tous les champs sont remplis, false sinon
     */
    public boolean allFieldsAreFilled() {
        return !authorEditText.getText().toString().isEmpty() &&
                !titleEditText.getText().toString().isEmpty() &&
                !publisherEditText.getText().toString().isEmpty() &&
                !descriptionEditText.getText().toString().isEmpty() &&
                !publishingDateEditText.getText().toString().isEmpty() &&
                !priceEditText.getText().toString().isEmpty();
    }
}