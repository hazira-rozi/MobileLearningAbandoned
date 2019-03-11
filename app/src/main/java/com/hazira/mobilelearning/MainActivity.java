package com.hazira.mobilelearning;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        BottomNavigationView bottomHomeNav = findViewById(R.id.bottom_home);
        bottomHomeNav.setOnNavigationItemSelectedListener(botNavListener);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);

        }

    }

    private BottomNavigationView.OnNavigationItemSelectedListener botNavListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    int title = R.string.titleHome;
                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            title = R.string.titleHome;
                            break;
                        case R.id.about:
                            selectedFragment = new AboutFragment();
                            title = R.string.titleAbout;
                            break;
                        case R.id.help:
                            selectedFragment = new HelpFragment();
                            title = R.string.titleHelp;
                            break;
                        case R.id.source:
                            selectedFragment = new MoreFragment();
                            title = R.string.titleMore;
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    if (getSupportActionBar() != null) {
                        getSupportActionBar().setTitle(title);
                    }

                    return true;
                }
            };


    public void selectItem(int position) {
        Intent intent = null;

        switch (position) {
            case R.id.nav_home:
                intent = new Intent(this, MainActivity.class);
                break;

            case R.id.nav_materi_1:
                intent = new Intent(this, M1Activity.class);
                break;

            case R.id.nav_materi_2:
                intent = new Intent(this, M2Activity.class);
                break;


            case R.id.nav_chat:
//                InterfaceManager.shared().a.startLoginActivity(this, false);
                intent = getPackageManager().getLaunchIntentForPackage("com.hazira.chatsiskomdig");

//
                break;

            default:
                intent = new Intent(this, MainActivity.class);
                break;

        }

        startActivity(intent);
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int position = item.getItemId();
        selectItem(position);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}

