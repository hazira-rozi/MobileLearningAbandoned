package com.hazira.mobilelearning;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Quiz1Activity extends AppCompatActivity {

    private static final int REQUEST_CODE_QUIZ = 1;
//    public static final int EXTRA_QUIZ_KD_ID = 1;

    public static final String SHARED_PREFS_M1Q1 = "sharedPrefs";
    public static final String KEY_HIGHSCORE_M1Q1 = "keyHighscore";
//    public static final int KEY_QUIZKD = 1;

    private TextView textViewHighscore;
    private int quizKDID;
    private int highscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz1);

        textViewHighscore = findViewById(R.id.materi1_quiz_1_highscore);
        loadHighscore();

        final Button buttonStartQuiz = findViewById(R.id.button_start_m1q1);
        buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewM1Q1) {
                startQuiz();
            }
        });

    }

    private void startQuiz() {

        String quizKDName = "Materi 1 - Quiz 1";
        int value = 2;
        Intent intent = new Intent(Quiz1Activity.this, M1Q1Activity.class);
        intent.putExtra("quizKDID",value);
        startActivityForResult(intent, REQUEST_CODE_QUIZ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_QUIZ) {
            if (resultCode == RESULT_OK) {
                int score = data.getIntExtra(M1Q1Activity.TOTAL_SCORE_M1Q1, 0);

                if (score > highscore) {
                    updateHighscore(score);
                } else {

                }
            } else {

            }
        } else {

        }
    }

    private void loadHighscore() {
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS_M1Q1, MODE_PRIVATE);
        highscore = prefs.getInt(KEY_HIGHSCORE_M1Q1, 0);
        textViewHighscore.setText("Highscore: " + highscore);
    }

    private void updateHighscore(int highscoreNew) {
        highscore = highscoreNew;
        textViewHighscore.setText("Highscore: " + highscore);

        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS_M1Q1, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGHSCORE_M1Q1, highscore);
        editor.apply();

    }
}
