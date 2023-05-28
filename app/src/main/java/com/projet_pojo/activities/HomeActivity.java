package com.projet_pojo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.projet_pojo.R;

public class HomeActivity extends AppCompatActivity {

    Button openLibraryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        openLibraryButton = findViewById(R.id.openLibraryButton);
        onClickOpenLibrary(openLibraryButton);
    }

    public void onClickOpenLibrary(Button button) {
        button.setOnClickListener(v -> {
            Intent intent = new Intent(this, LibraryActivity.class);
            startActivity(intent);
            finish();
        });
    }

}