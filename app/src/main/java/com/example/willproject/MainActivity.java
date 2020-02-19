package com.example.willproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {


    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ActionBarDrawerToggle drawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.DrawerLayout);
        navigationView = findViewById(R.id.NavigationView);
        toolbar =  findViewById(R.id.app_Bar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        // This will display an Up icon (<-), we will replace it with hamburger later
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.tap);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.profile:
                        Toast.makeText(MainActivity.this,"you tapped on profile",Toast.LENGTH_LONG).show();
                        Intent profile = new Intent(MainActivity.this, ProfileUpdate.class);
                        startActivity(profile);
                        return true;
                    case R.id.con:
                        Toast.makeText(MainActivity.this,"you tapped on consultants",Toast.LENGTH_LONG).show();
                        Intent consultants = new Intent(MainActivity.this, NearByConsultants.class);
                        startActivity(consultants);
                        return true;
                    case R.id.nav_break:
                        Toast.makeText(MainActivity.this,"you tapped on consultants",Toast.LENGTH_LONG).show();
                        Intent breakdown = new Intent(MainActivity.this, BreakDown.class);
                        startActivity(breakdown);
                        return true;

                    case R.id.loc:
                        Toast.makeText(MainActivity.this,"you tapped on UserLocationPage",Toast.LENGTH_LONG).show();
                        Intent loc = new Intent(MainActivity.this, MapActivtiy.class);
                        startActivity(loc);
                        return true;

                }

                return true;
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
