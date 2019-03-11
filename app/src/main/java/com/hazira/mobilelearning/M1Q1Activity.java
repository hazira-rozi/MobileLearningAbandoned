package com.hazira.mobilelearning;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;
import java.util.Collections;


public class M1Q1Activity extends AppCompatActivity {

    public static final String TOTAL_SCORE_M1Q1 = "totalScore";
    private static final String KEY_SCORE = "keyscore";
    private static final String KEY_QUESTION_COUNT = "keyQuestionCount";
    private static final String KEY_ANSWERED = "keyAnswered";
    private static final String KEY_QUESTION_LIST = "keyQuestionList";

    private TextView textViewQuestion;
    private TextView textViewScore;
    private TextView textViewQuestionCount;
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private Button buttonConfirmNext;

    private ColorStateList textColorDefaultRb;
    private ColorStateList textColorDefaultCD;

    private ArrayList<Question> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;

    private int score;
    private boolean answered;

    private long backPressedTIme;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m1q1);

        textViewQuestion = findViewById(R.id.textViewQuestionM1Q1);
        textViewScore = findViewById(R.id.textViewScoreM1Q1);
        textViewQuestionCount = findViewById(R.id.textViewQuestionCountM1Q1);

        int value= 2;
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            value = bundle.getInt("quizKDID", 2);
        }

        rbGroup = findViewById(R.id.radio_group_m1q1);
        rb1 = findViewById(R.id.radio_button1_m1q1);
        rb2 = findViewById(R.id.radio_button2_m1q1);
        rb3 = findViewById(R.id.radio_button3_m1q1);
        rb4 = findViewById(R.id.radio_button4_m1q1);
        buttonConfirmNext = findViewById(R.id.button_confirm_next_m1q1);
        textColorDefaultRb = rb1.getTextColors();
//        Toast.makeText(this, String.valueOf(quizKDID), Toast.LENGTH_SHORT);
        if (savedInstanceState == null) {
            QuizDbHelper dbHelper = new QuizDbHelper(this);
            questionList = dbHelper.getQuestions(value);
            questionCountTotal = questionList.size();
            Collections.shuffle(questionList);

            showNextQuestion();
        } else {
            questionList = savedInstanceState.getParcelableArrayList(KEY_QUESTION_LIST);
            questionCountTotal = questionList.size();
            questionCounter = savedInstanceState.getInt(KEY_QUESTION_COUNT);
            currentQuestion = questionList.get(questionCounter - 1);
            score = savedInstanceState.getInt(KEY_SCORE);
            answered = savedInstanceState.getBoolean(KEY_ANSWERED);
        }

        buttonConfirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewM1Q1) {
                if (!answered) {
                    if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked()) {
                        checkAnswer();
                    } else {
                        Toast.makeText(M1Q1Activity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    showNextQuestion();
                }
            }
        });
    }


    private void checkAnswer() {
        answered = true;

        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        int answerNr = rbGroup.indexOfChild(rbSelected) + 1;

        if (answerNr == currentQuestion.getAnswerNr()) {
            score++;
            textViewScore.setText("Score: " + score);
        }

        showSolution();
    }

    private void showSolution() {
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);
        rb4.setTextColor(Color.RED);

        switch (currentQuestion.getAnswerNr()) {
            case 1:
                rb1.setTextColor(Color.GREEN);
                textViewQuestion.setText("Pilihan A Benar");
                break;
            case 2:
                rb2.setTextColor(Color.GREEN);
                textViewQuestion.setText("Pilihan B Benar");
                break;
            case 3:
                rb3.setTextColor(Color.GREEN);
                textViewQuestion.setText("Pilihan C Benar");
                break;
            case 4:
                rb4.setTextColor(Color.GREEN);
                textViewQuestion.setText("Pilihan D Benar");
                break;
        }

        if (questionCounter < questionCountTotal) {
            buttonConfirmNext.setText("Next");
        } else {
            buttonConfirmNext.setText("Finish");
        }

    }

    private void showNextQuestion() {
        rb1.setTextColor(textColorDefaultRb);
        rb2.setTextColor(textColorDefaultRb);
        rb3.setTextColor(textColorDefaultRb);
        rb4.setTextColor(textColorDefaultRb);
        rbGroup.clearCheck();

        if (questionCounter < questionCountTotal) {
            currentQuestion = questionList.get(questionCounter);

            textViewQuestion.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());
            rb4.setText(currentQuestion.getOption4());
            questionCounter++;
            textViewQuestionCount.setText("Question: " + questionCounter + "/" + questionCountTotal);
            answered = false;
            buttonConfirmNext.setText("Confirm");

        } else {
            finishQuiz();
        }
    }

    private void finishQuiz() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(TOTAL_SCORE_M1Q1, score);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    public void onBackPressed() {
        if (backPressedTIme + 2000 > System.currentTimeMillis()) {
            finishQuiz();
        } else {
            Toast.makeText(this, "Press back again to finish", Toast.LENGTH_SHORT);
        }
        backPressedTIme = System.currentTimeMillis();
    }

    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_SCORE, score);
        outState.putInt(KEY_QUESTION_COUNT, questionCounter);
        outState.putBoolean(KEY_ANSWERED, answered);
        outState.putParcelableArrayList(KEY_QUESTION_LIST, questionList);

    }
}