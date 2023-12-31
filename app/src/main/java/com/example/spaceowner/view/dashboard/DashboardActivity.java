package com.example.spaceowner.view.dashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.spaceowner.R;
import com.example.spaceowner.model.data.Space;
import com.example.spaceowner.utils.TokenManager;
import com.example.spaceowner.view.addspace.AddSpaceActivity;
import com.example.spaceowner.view.auth.AuthActivity;
import com.example.spaceowner.view.bookings.BookingActivity;
import com.example.spaceowner.view.space.SpaceActivity;
import com.example.spaceowner.view.space.SpaceViewpagerAdapter;
import com.example.spaceowner.viewmodel.SpaceListViewModel;
import com.example.spaceowner.viewmodel.ViewModelFactory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    TabLayout tabLayout;
    ViewPager2 viewPager;
    DashboardViewpagerAdapter adapter;
    FloatingActionButton addSpaceButton;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    SpaceListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);


        viewModel = new ViewModelFactory().create(SpaceListViewModel.class);
        setContentView(R.layout.activity_dashboard);


        drawerLayout = findViewById(R.id.dashboard_drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);


        tabLayout = findViewById(R.id.tab_layout_dashboard);
        viewPager = findViewById(R.id.dashboard_viewpager);
        addSpaceButton = findViewById(R.id.fab_add_space);
        adapter = new DashboardViewpagerAdapter(this);
        viewPager.setAdapter(adapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab){
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab){}
            @Override
            public void onTabReselected(TabLayout.Tab tab){}
        });

        addSpaceButton.setOnClickListener((v) -> {
            Intent intent = new Intent(this, AddSpaceActivity.class);
            startActivity(intent);
            this.finish();
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position){
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });


    }

    public SpaceListViewModel getViewModel(){
        return viewModel;
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
        }else if(item.getItemId() == R.id.nav_bookings) {
            Log.d("DASHBOARD_ACTIVITY: ", "onNavigationItemSelected: " + "DASHBOARD");
            Intent intent = new Intent(this, BookingActivity.class);
            startActivity(intent);
            this.finish();
        }
        return true;
    }

    public void changeFragment(Space space, SpaceViewpagerAdapter.SpaceFragmentType fragmentType){
        Intent intent = new Intent(this, SpaceActivity.class);
        intent.putExtra("space", space);
        intent.putExtra("fragment", fragmentType);
        startActivity(intent);
    }
}