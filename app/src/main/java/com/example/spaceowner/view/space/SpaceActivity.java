package com.example.spaceowner.view.space;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.spaceowner.R;
import com.example.spaceowner.model.data.Space;
import com.example.spaceowner.utils.TokenManager;
import com.example.spaceowner.view.auth.AuthActivity;
import com.example.spaceowner.viewmodel.SpaceViewModel;
import com.example.spaceowner.viewmodel.ViewModelFactory;
import com.google.android.material.navigation.NavigationView;

public class SpaceActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ViewPager2 viewPager;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;

    Space activitySpace;

    SpaceViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_space);

        activitySpace = (Space) getIntent().getSerializableExtra("space");
        viewModel = new ViewModelFactory().create(SpaceViewModel.class);
        if(viewModel == null){

        }
        viewModel.setSpace(activitySpace);


        drawerLayout = findViewById(R.id.space_drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        viewPager = findViewById(R.id.space_viewpager);
        SpaceViewpagerAdapter adapter = new SpaceViewpagerAdapter(this);
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(drawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.nav_logout){
            Log.d("DASHBOARD_ACTIVITY: ", "onNavigationItemSelected: " + "LOGOUT");
            TokenManager.getInstance().clearToken();
            Intent intent = new Intent(this, AuthActivity.class);
            startActivity(intent);
            this.finish();
        }
        return true;
    }
}