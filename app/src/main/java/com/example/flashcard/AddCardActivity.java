package com.example.flashcard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        findViewById(R.id.cancelButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.saveButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string1 = ((EditText) findViewById(R.id.editQ)).getText().toString();
                String string2 = ((EditText) findViewById(R.id.editA)).getText().toString();
                String string3 = ((EditText) findViewById(R.id.editWrongA1)).getText().toString();
                String string4 = ((EditText) findViewById(R.id.editWrongA2)).getText().toString();

                Intent data = new Intent(); // create a new Intent, this is where we will put our data
                data.putExtra("string1", string1); // puts one string into the Intent, with the key as 'string1'
                data.putExtra("string2", string2); // puts another string into the Intent, with the key as 'string2
                data.putExtra("string3", string3); // puts one string into the Intent, with the key as 'string1'
                data.putExtra("string4", string4); // puts another string into the Intent, with the key as 'string2
                setResult(RESULT_OK, data); // set result code and bundle data for response
                finish(); // closes this activity and pass data to the original activity that launched this activity
            }
        });

    }


}
