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

/**
 * Classe représentant une liste de livres sous forme d'adaptateur
 */
public class BookItemAdapter extends BaseAdapter {

    /**
     * Contexte de l'application
     */
    private Context context;

    /**
     * Liste des livres
     */
    private List<Book> books;

    /**
     * Permet de créer des vues à partir d'un fichier XML
     */
    private LayoutInflater inflater;


    /**
     * Constructeur
     * @param context : contexte de l'application
     * @param booksItemList : liste des livres
     */
    public BookItemAdapter(Context context, List<Book> booksItemList) {
        this.context = context;
        this.books = booksItemList;
        this.inflater = LayoutInflater.from(context);
    }

    // Getters & Setters

    /**
     * Récupère le contexte de l'application
     * @return
     */
    public Context getContext() {
        return context;
    }

    /**
     * Modifie le contexte de l'application
     * @param context : contexte de l'application
     */
    public void setContext(Context context) {
        this.context = context;
    }

    /**
     * Récupère la liste des livres
     * @return
     */
    public List<Book> getBooksItemList() {
        return books;
    }

    /**
     * Modifie la liste des livres
     * @param booksItemList : liste des livres
     */
    public void setBooksItemList(List<Book> booksItemList) {
        this.books = booksItemList;
    }

    /**
     * Récupère le LayoutInflater
     * @return
     */
    public LayoutInflater getInflater() {
        return inflater;
    }

    /**
     * Modifie le LayoutInflater
     * @param inflater : LayoutInflater
     */
    public void setInflater(LayoutInflater inflater) {
        this.inflater = inflater;
    }


    // Méthodes

    /**
     * Récupère le nombre de livres
     * @return
     */
    @Override
    public int getCount() {
        return books.size();
    }

    /**
     * Récupère un livre
     * @param i : index du livre
     * @return
     */
    @Override
    public Book getItem(int i) {
        return books.get(i);
    }

    /**
     * Récupère l'identifiant d'un livre
     * @param i : index du livre
     * @return
     */
    @Override
    public long getItemId(int i) {
        return 0;
    }

    /**
     * Récupère la vue d'un livre
     * @param i : index du livre
     * @param view : vue
     * @param viewGroup : vue
     * @return
     */
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
