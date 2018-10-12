package com.example.abhinandan.onlinedb;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class Studentlayout extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawerLayout;
    SharedPreferences sp;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        mDrawerLayout = findViewById(R.id.drawer_layout_student);
        NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId())
        {
            case R.id.nav_details_student:
                Toast.makeText(this,"Details",Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_quickmessages:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Student_quick_msg()).commit();
                break;

            case R.id.nav_about_student:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new about()).commit();
                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.appbar_logout,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.logout:
                sp = getSharedPreferences("MY_SHARE",MODE_PRIVATE);
                sp.edit().putBoolean("IsLogged",false).apply();
                sp.edit().putBoolean("IsStudent",false).apply();
                sp.edit().commit();
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

}
