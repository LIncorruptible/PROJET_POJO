package com.projet_pojo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
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

    public void onClickOpenLibrary(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.library_activity);
            }
        });
    }
}