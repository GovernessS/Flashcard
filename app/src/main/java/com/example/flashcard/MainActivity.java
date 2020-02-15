package com.example.flashcard;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((TextView) findViewById(R.id.barack)).setVisibility(View.INVISIBLE);

        findViewById(R.id.q).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TextView) findViewById(R.id.barack)).setVisibility(View.VISIBLE);
                ((TextView) findViewById(R.id.q)).setVisibility(View.INVISIBLE);
            }
        });
    }
}
