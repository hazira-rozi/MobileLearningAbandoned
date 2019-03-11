package com.hazira.mobilelearning;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public class M1Activity extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m1);

        Toolbar toolbar = findViewById(R.id.toolbar_m1);
        setSupportActionBar(toolbar);

        BottomNavigationView bottomHomeNav = findViewById(R.id.bottom_m1);
        bottomHomeNav.setOnNavigationItemSelectedListener(botNavListener_m1);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }

        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_m1,
                    new M1Fragment()).commit();


        }

    }

    private BottomNavigationView.OnNavigationItemSelectedListener botNavListener_m1 =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    int title = R.string.titleMateri1;
                    switch (item.getItemId()) {
                        case R.id.nav_materi_1:
                            selectedFragment = new M1Fragment();
                            title = R.string.titleMateri1;
                            break;
                        case R.id.langkah_1:
                            selectedFragment = new M1LangkahFragment();
                            title = R.string.titleMateri1Langkah;
                            break;
                        case R.id.latihan_1:
                            selectedFragment = new M1LatihanFragment();
                            title = R.string.titleMateri1Latihan;
                            break;
                        case R.id.evaluasi_1:
                            selectedFragment = new M1EvaluasiFragment();
                            title = R.string.titleMateri1Evaluas;
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_m1,
                            selectedFragment).commit();
                    if (getSupportActionBar() != null) {
                        getSupportActionBar().setTitle(title);
                    }
                    return true;
                }
            };


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    /* button evaluasi */
    public void ev1(View viewQuiz) {
        switch (viewQuiz.getId()) {
            case R.id.button_evaluasi_1_1:
                Intent quizIntent_q1_1 = new Intent(this, Quiz1Activity.class);
                startActivity(quizIntent_q1_1);
                break;
            case R.id.button_evaluasi_1_2:
                Intent quizIntent_q1_2 = new Intent(this, Quiz1Activity.class);
                startActivity(quizIntent_q1_2);
                break;
        }

    }
}