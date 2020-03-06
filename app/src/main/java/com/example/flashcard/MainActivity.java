package com.example.flashcard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    boolean isShowingIcon = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.toggle_choices_invisibility).setVisibility(View.INVISIBLE);

        findViewById(R.id.A2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.A2).setBackgroundColor(getResources().getColor(R.color.incorrectAns, null));
                findViewById(R.id.A1).setBackgroundColor(getResources().getColor(R.color.correctAnswer, null));
            }
        });

        findViewById(R.id.A3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.A3).setBackgroundColor(getResources().getColor(R.color.incorrectAns, null));
                findViewById(R.id.A1).setBackgroundColor(getResources().getColor(R.color.correctAnswer, null));
            }
        });

        findViewById(R.id.A1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.A1).setBackgroundColor(getResources().getColor(R.color.correctAnswer, null));
            }
        });

        findViewById(R.id.rootView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.A2).setBackgroundColor(getResources().getColor(R.color.normal, null));
                findViewById(R.id.A3).setBackgroundColor(getResources().getColor(R.color.normal, null));
                findViewById(R.id.A1).setBackgroundColor(getResources().getColor(R.color.normal, null));
            }
        });

        findViewById(R.id.toggle_choices_visibility).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isShowingIcon) {
                    ((ImageView) findViewById(R.id.toggle_choices_visibility)).setImageResource(R.drawable.ic_iconmonstr_eye_5);
                    findViewById(R.id.A2).setVisibility(View.INVISIBLE);
                    findViewById(R.id.A3).setVisibility(View.INVISIBLE);
                    findViewById(R.id.A1).setVisibility(View.INVISIBLE);
                    isShowingIcon = false;
                } else {
                    ((ImageView) findViewById(R.id.toggle_choices_visibility)).setImageResource(R.drawable.ic_iconmonstr_eye_8);
                    findViewById(R.id.A2).setVisibility(View.VISIBLE);
                    findViewById(R.id.A3).setVisibility(View.VISIBLE);
                    findViewById(R.id.A1).setVisibility(View.VISIBLE);
                    isShowingIcon = true;
                }
            }
        });

        findViewById(R.id.addButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                MainActivity.this.startActivityForResult(intent, 100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) { // this 100 needs to match the 100 we used when we called startActivityForResult!
            String mainQ = data.getExtras().getString("string1"); // 'string1' needs to match the key we used when we put the string in the Intent
            String correct = data.getExtras().getString("string2");
            String W1 = data.getExtras().getString("string3"); // 'string1' needs to match the key we used when we put the string in the Intent
            String W2 = data.getExtras().getString("string4");

            TextView Question = findViewById(R.id.q);
            Question.setText(mainQ);

            TextView rightAnswer = findViewById(R.id.A1);
            rightAnswer.setText(correct);

            TextView wrong1 = findViewById(R.id.A2);
            wrong1.setText(W1);

            TextView wrong2 = findViewById(R.id.A3);
            wrong2.setText(W2);
        }
    }
}
