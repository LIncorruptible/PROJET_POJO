package com.projet_pojo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.projet_pojo.R;

/**
 * Activité d'accueil
 */
public class HomeActivity extends AppCompatActivity {

    Button openLibraryButton;

    /**
     * Méthode appelée à la création de l'activité
     * @param savedInstanceState : état de l'activité
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        openLibraryButton = findViewById(R.id.openLibraryButton);
        onClickOpenLibrary(openLibraryButton);
    }

    /**
     * Méthode permettant d'ouvrir l'activité LibraryActivity
     * @param button : bouton permettant d'ouvrir l'activité LibraryActivity
     */
    public void onClickOpenLibrary(Button button) {
        button.setOnClickListener(v -> {
            Intent intent = new Intent(this, LibraryActivity.class);
            startActivity(intent);
            finish();
        });
    }

}