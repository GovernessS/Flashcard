package com.example.flashcard;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    boolean isShowingIcon = true;
    FlashcardDatabase flashcardDatabase;
    List<Flashcard> allFlashcards;
    int currentCardDisplayedIndex = 0;
    CountDownTimer countDownTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.toggle_choices_invisibility).setVisibility(View.INVISIBLE);

        countDownTimer = new CountDownTimer(16000, 1000) {
            public void onTick(long millisUntilFinished) {
                ((TextView) findViewById(R.id.timer)).setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
            }
            private void startTimer() {
                countDownTimer.cancel();
                countDownTimer.start();
            }
        };

        flashcardDatabase = new FlashcardDatabase(getApplicationContext());
        allFlashcards = flashcardDatabase.getAllCards();

        if (allFlashcards != null && allFlashcards.size() > 0) {
            ((TextView) findViewById(R.id.q)).setText(allFlashcards.get(0).getQuestion());
            ((TextView) findViewById(R.id.A1)).setText(allFlashcards.get(0).getAnswer());
            ((TextView) findViewById(R.id.A2)).setText(allFlashcards.get(0).getWrongAnswer1());
            ((TextView) findViewById(R.id.A3)).setText(allFlashcards.get(0).getWrongAnswer2());
        }

        findViewById(R.id.A2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.A2).setBackgroundColor(getResources().getColor(R.color.incorrectAns, null));

                View answerSideView = findViewById(R.id.A1);

                // get the center for the clipping circle
                int cx = answerSideView.getWidth() / 2;
                int cy = answerSideView.getHeight() / 2;

                // get the final radius for the clipping circle
                float finalRadius = (float) Math.hypot(cx, cy);

                // create the animator for this view (the start radius is zero)
                Animator anim = ViewAnimationUtils.createCircularReveal(answerSideView, cx, cy, 0f, finalRadius);

//              //hide the question and show the answer to prepare for playing the animation!
                //questionSideView.setVisibility(View.INVISIBLE);
                //answerSideView.setVisibility(View.VISIBLE);

                anim.setDuration(3000);
                anim.start();
                findViewById(R.id.A1).setBackgroundColor(getResources().getColor(R.color.correctAnswer, null));
            }
        });

        findViewById(R.id.A3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.A3).setBackgroundColor(getResources().getColor(R.color.incorrectAns, null));

                View answerSideView = findViewById(R.id.A1);

                // get the center for the clipping circle
                int cx = answerSideView.getWidth() / 2;
                int cy = answerSideView.getHeight() / 2;

                // get the final radius for the clipping circle
                float finalRadius = (float) Math.hypot(cx, cy);

                // create the animator for this view (the start radius is zero)
                Animator anim = ViewAnimationUtils.createCircularReveal(answerSideView, cx, cy, 0f, finalRadius);

//              //hide the question and show the answer to prepare for playing the animation!
                //questionSideView.setVisibility(View.INVISIBLE);
                //answerSideView.setVisibility(View.VISIBLE);

                anim.setDuration(3000);
                anim.start();
                findViewById(R.id.A1).setBackgroundColor(getResources().getColor(R.color.correctAnswer, null));
            }
        });

        findViewById(R.id.A1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.A1).setBackgroundColor(getResources().getColor(R.color.correctAnswer, null));

                View answerSideView = findViewById(R.id.A1);

                // get the center for the clipping circle
                int cx = answerSideView.getWidth() / 2;
                int cy = answerSideView.getHeight() / 2;

                // get the final radius for the clipping circle
                float finalRadius = (float) Math.hypot(cx, cy);

                // create the animator for this view (the start radius is zero)
                Animator anim = ViewAnimationUtils.createCircularReveal(answerSideView, cx, cy, 0f, finalRadius);

//              //hide the question and show the answer to prepare for playing the animation!
                //questionSideView.setVisibility(View.INVISIBLE);
                //answerSideView.setVisibility(View.VISIBLE);

                anim.setDuration(3000);
                anim.start();
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
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });

        findViewById(R.id.editButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                intent.putExtra("stringKey1", ((TextView) findViewById(R.id.q)).getText().toString());
                intent.putExtra("stringKey2", ((TextView) findViewById(R.id.A1)).getText().toString());
                intent.putExtra("stringKey3", ((TextView) findViewById(R.id.A2)).getText().toString());
                intent.putExtra("stringKey4", ((TextView) findViewById(R.id.A3)).getText().toString());
                MainActivity.this.startActivityForResult(intent, 100);
            }
        });

        findViewById(R.id.arrow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // advance our pointer index so we can show the next card
                //currentCardDisplayedIndex++;

                currentCardDisplayedIndex = getRandomNumber(0, allFlashcards.size()-1);

                // make sure we don't get an IndexOutOfBoundsError if we are viewing the last indexed card in our list
                //if (currentCardDisplayedIndex > allFlashcards.size() - 1) {
                    //currentCardDisplayedIndex = 0;
                //}

                final Animation leftOutAnim = AnimationUtils.loadAnimation(v.getContext(), R.anim.left_out);
                final Animation rightInAnim = AnimationUtils.loadAnimation(v.getContext(), R.anim.right_in);

                leftOutAnim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        // set the question and answer TextViews with data from the database
                        ((TextView) findViewById(R.id.q)).setText(allFlashcards.get(currentCardDisplayedIndex).getQuestion());
                        ((TextView) findViewById(R.id.A1)).setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());
                        ((TextView) findViewById(R.id.A2)).setText(allFlashcards.get(currentCardDisplayedIndex).getWrongAnswer1());
                        ((TextView) findViewById(R.id.A3)).setText(allFlashcards.get(currentCardDisplayedIndex).getWrongAnswer2());
                        findViewById(R.id.q).startAnimation(rightInAnim);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        // we don't need to worry about this method
                    }
                });

                findViewById(R.id.q).startAnimation(leftOutAnim);
            }
        });


        findViewById(R.id.trash).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numTotalCards = allFlashcards.size();
                System.out.println(allFlashcards.size());
                System.out.println(currentCardDisplayedIndex);
                if (numTotalCards == 1) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Need at least one card", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, -150);
                    toast.show();
                } else {
                    flashcardDatabase.deleteCard(((TextView) findViewById(R.id.q)).getText().toString());
                    allFlashcards.remove(currentCardDisplayedIndex);
                    numTotalCards--;
                    if (currentCardDisplayedIndex >= 0 && currentCardDisplayedIndex < allFlashcards.size()) {
                        currentCardDisplayedIndex--;
                    } else {
                        currentCardDisplayedIndex++;
                    }
                    ((TextView) findViewById(R.id.q)).setText(allFlashcards.get(currentCardDisplayedIndex).getQuestion());
                    ((TextView) findViewById(R.id.A1)).setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());
                    ((TextView) findViewById(R.id.A2)).setText(allFlashcards.get(currentCardDisplayedIndex).getWrongAnswer1());
                    ((TextView) findViewById(R.id.A3)).setText(allFlashcards.get(currentCardDisplayedIndex).getWrongAnswer2());
                }
            }
        });
    }

    // returns a random number between minNumber and maxNumber, inclusive.
// for example, if i called getRandomNumber(1, 3), there's an equal chance of it returning either 1, 2, or 3.
    public int getRandomNumber(int minNumber, int maxNumber) {
        Random rand = new Random();
        return rand.nextInt((maxNumber - minNumber) + 1) + minNumber;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((requestCode == 100) && (resultCode == RESULT_OK)) { // this 100 needs to match the 100 we used when we called startActivityForResult!
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

            Snackbar.make(findViewById(R.id.q),
                    "Card successfully created",
                    Snackbar.LENGTH_SHORT)
                    .show();

            flashcardDatabase.insertCard(new Flashcard(mainQ, correct, W1, W2));
            allFlashcards = flashcardDatabase.getAllCards();
        }
    }
}
