package com.hazira.mobilelearning;

import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;

public class M2Activity extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m2);


        Toolbar toolbar = findViewById(R.id.toolbar_m2);
        setSupportActionBar(toolbar);

        /*Dynamic Title */


        BottomNavigationView bottomHomeNav = findViewById(R.id.bottom_m2);
        bottomHomeNav.setOnNavigationItemSelectedListener(botNavListener_m2);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_m2,
                    new M2Fragment()).commit();
        }

    }


    private BottomNavigationView.OnNavigationItemSelectedListener botNavListener_m2 =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    int title = 0;

                    switch (item.getItemId()) {
                        case R.id.nav_materi_2:
                            selectedFragment = new M1Fragment();
                            title = R.string.titleMateri2;
                            break;
                        case R.id.langkah_2:
                            selectedFragment = new M1LangkahFragment();
                            title = R.string.titleMateri2Langkah;
                            break;
                        case R.id.latihan_2:
                            selectedFragment = new M1LatihanFragment();
                            title = R.string.titleMateri1Latihan;
                            break;
                        case R.id.evaluasi_2:
                            selectedFragment = new M1EvaluasiFragment();
                            title = R.string.titleMateri2Evaluas;
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_m2,
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
}