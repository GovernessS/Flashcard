package com.example.flashcard;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    boolean isShowingIcon = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.toggle_choices_invisibility).setVisibility(View.INVISIBLE);

        findViewById(R.id.Bill).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.Bill).setBackgroundColor(getResources().getColor(R.color.incorrectAns, null));
                findViewById(R.id.barack).setBackgroundColor(getResources().getColor(R.color.correctAnswer, null));
            }
        });

        findViewById(R.id.George).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.George).setBackgroundColor(getResources().getColor(R.color.incorrectAns, null));
                findViewById(R.id.barack).setBackgroundColor(getResources().getColor(R.color.correctAnswer, null));
            }
        });

        findViewById(R.id.barack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.barack).setBackgroundColor(getResources().getColor(R.color.correctAnswer, null));
            }
        });

        findViewById(R.id.rootView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.Bill).setBackgroundColor(getResources().getColor(R.color.normal, null));
                findViewById(R.id.George).setBackgroundColor(getResources().getColor(R.color.normal, null));
                findViewById(R.id.barack).setBackgroundColor(getResources().getColor(R.color.normal, null));
            }
        });

        findViewById(R.id.toggle_choices_visibility).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isShowingIcon) {
                    ((ImageView) findViewById(R.id.toggle_choices_visibility)).setImageResource(R.drawable.ic_iconmonstr_eye_5);
                    findViewById(R.id.Bill).setVisibility(View.INVISIBLE);
                    findViewById(R.id.George).setVisibility(View.INVISIBLE);
                    findViewById(R.id.barack).setVisibility(View.INVISIBLE);
                    isShowingIcon = false;
                } else {
                    ((ImageView) findViewById(R.id.toggle_choices_visibility)).setImageResource(R.drawable.ic_iconmonstr_eye_8);
                    findViewById(R.id.Bill).setVisibility(View.VISIBLE);
                    findViewById(R.id.George).setVisibility(View.VISIBLE);
                    findViewById(R.id.barack).setVisibility(View.VISIBLE);
                    isShowingIcon = true;
                }
            }
        });
    }
}
