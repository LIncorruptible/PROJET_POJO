package com.projet_pojo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.projet_pojo.R;
import com.projet_pojo.pojo.Book;
import com.projet_pojo.pojo.Helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BookActivity extends AppCompatActivity {

    Helper helper = new Helper(this);
    EditText authorEditText, titleEditText, publisherEditText, descriptionEditText, publishingDateEditText, priceEditText;
    Button saveButton, cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_activity);

        // Initialisation des champs
        initFields();

        onClickSave();
        onClickCancel();
    }

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

    public void onClickCancel() {
        cancelButton.setOnClickListener(v -> {
            // Retour à la bibliothèque
            setContentView(R.layout.library_activity);
        });
    }

    public void onClickSave() {
        saveButton.setOnClickListener(v -> {
            Book book = new Book();
            book.setAuthor(authorEditText.getText().toString());
            book.setTitle(titleEditText.getText().toString());
            book.setPublisher(publisherEditText.getText().toString());
            book.setDescription(descriptionEditText.getText().toString());
            book.setPublishingDate(convertEditTextToDate(publishingDateEditText));
            book.setPrice(Double.parseDouble(priceEditText.getText().toString()));
            // Ajout du livre dans la base de données
            helper.insertBook(book);
        });
    }

    public Date convertEditTextToDate(EditText editText) {
        String str_date = editText.getText().toString();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return format.parse(str_date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}