package com.hazira.mobilelearning;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hazira.mobilelearning.QuizContract.*;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "QuizDBAwesome.db";
    private static final int DATABASE_VERSION = 2;


    private static QuizDbHelper instance;

    private SQLiteDatabase db;


    public QuizDbHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /* used to access from multiple thread*/
//    public static synchronized QuizDbHelper getInstance(Context context) {
//        if (instance == null) {
//            instance = new QuizDbHelper(context.getApplicationContext());
//        }
//        return instance;
//    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER," +
                QuestionsTable.COLUMN_KD_QUIZ + " INTEGER )";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    private void fillQuestionsTable() {
        Question q1 = new Question("A is Correct", "A", "B", "C", "D", 1,
                1);
        addQuestion(q1);
        Question q2 = new Question(" 2 B is Correct", "A", "B", "C", "D", 2,
                1);
        addQuestion(q2);
        Question q3 = new Question("A is Correct", "A", "B", "C", "D", 1,
                1);
        addQuestion(q3);
        Question q4 = new Question("C is Correct", "A", "B", "C", "D", 3,
                3);
        addQuestion(q4);
        Question q5 = new Question("D is also Correct", "A", "B", "C", "D", 1,
                4);
        addQuestion(q5);
        Question q6 = new Question("A is Correct 6", "A", "B", "C", "D", 1,
                2);
        addQuestion(q6);
        Question q7 = new Question(" 2 B is Correct7", "A", "B", "C", "D", 2,
                3);
        addQuestion(q7);
        Question q8 = new Question("C is Correct 8", "A", "B", "C", "D", 3,
                1);
        addQuestion(q8);
        Question q9 = new Question("D is Correct", "A", "B", "C", "D", 4,
                4);
        addQuestion(q9);
        Question q10 = new Question("A is 10 Correct", "A", "B", "C", "D", 1,
                1);
        addQuestion(q10);
        Question q11 = new Question("2 B 11 is Correct", "A", "B", "C", "D", 2,
                2);
        addQuestion(q11);
        Question q12 = new Question("C 12 is Correct", "A", "B", "C", "D", 3,
                3);
        addQuestion(q12);
        Question q13 = new Question("A 14 is Correct", "A", "B", "C", "D", 1,
               4);
        addQuestion(q13);
        Question q14 = new Question("2 B is Correct", "A", "B", "C", "D", 2,
                1);
        addQuestion(q14);
    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_OPTION4, question.getOption4());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuestionsTable.COLUMN_KD_QUIZ, question.getQuizKDID());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);

    }

    /* this method only used if you want to retrieve the entire questions*/
    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setQuizKDID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_KD_QUIZ)));
                questionList.add(question);

            }
            while (c.moveToNext());

        }
        c.close();
        return questionList;
    }

    public ArrayList<Question> getQuestions(int quizKDID) {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();

        String qKDId = String.valueOf(quizKDID);
        String[] selectionArgs = new String[]{qKDId};

        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME+
            " WHERE " + QuestionsTable.COLUMN_KD_QUIZ + " = ?", selectionArgs);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setQuizKDID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_KD_QUIZ)));
                questionList.add(question);

            }
            while (c.moveToNext());

        }else{
            Question question = new Question();
            question.setQuestion("Empty");
            question.setOption1("Empty");
            question.setOption2("Empty");
            question.setOption3("Empty");
            question.setOption4("4 Empty");
            question.setAnswerNr(1);
            question.setQuizKDID(quizKDID);
            questionList.add(question);
        }
        c.close();
        return questionList;
    }
}
