package com.projet_pojo.pojo;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.projet_pojo.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BookItemAdapter extends BaseAdapter {

    // Attributs
    private Context context;
    private List<Book> books;
    private LayoutInflater inflater;

    // Constructeur
    public BookItemAdapter(Context context, List<Book> booksItemList) {
        this.context = context;
        this.books = booksItemList;
        this.inflater = LayoutInflater.from(context);
    }

    // Getters & Setters

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Book> getBooksItemList() {
        return books;
    }

    public void setBooksItemList(List<Book> booksItemList) {
        this.books = booksItemList;
    }

    public LayoutInflater getInflater() {
        return inflater;
    }

    public void setInflater(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    // Méthodes
    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public Book getItem(int i) {
        return books.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.book_item_adapter, null);

        // Récupération du livre
        Book book = getItem(i);

        // Remplissage des champs
        TextView authorTextView = view.findViewById(R.id.book_item_author);
        authorTextView.setText(context.getString(R.string.author) + " : " + book.getAuthor());

        TextView titleTextView = view.findViewById(R.id.book_item_title);
        titleTextView.setText(book.getTitle());

        TextView publisherTextView = view.findViewById(R.id.book_item_publisher);
        publisherTextView.setText(context.getString(R.string.publisher) + " : " + book.getPublisher());

        TextView descriptionTextView = view.findViewById(R.id.book_item_description);
        descriptionTextView.setText(context.getString(R.string.description) + " : " + book.getDescription());

        TextView publishingDateTextView = view.findViewById(R.id.book_item_publishing_date);

        // récupération de la date de publication du livre
        Date publishingDate = book.getPublishingDate();
        // Formatage de la date au format "dd/MM/yyyy"
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = dateFormat.format(publishingDate);
        // Remplissage du TextView
        publishingDateTextView.setText(context.getString(R.string.publishing_date) + " : " + formattedDate);


        TextView priceTextView = view.findViewById(R.id.book_item_price);
        priceTextView.setText(context.getString(R.string.price) + " : " + book.getPrice() + "€");

        return view;
    }


}
