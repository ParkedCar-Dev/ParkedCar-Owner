package com.example.spaceowner.view.bookings;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.spaceowner.R;
import com.example.spaceowner.utils.TokenManager;
import com.example.spaceowner.view.auth.AuthActivity;
import com.example.spaceowner.view.dashboard.DashboardActivity;
import com.example.spaceowner.viewmodel.BookingViewModel;
import com.example.spaceowner.viewmodel.ViewModelFactory;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class BookingActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    BookingViewModel viewModel;
    TabLayout tabLayout;
    ViewPager2 viewPager;
    BookingViewpagerAdapter adapter;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);

        viewModel = new ViewModelFactory().create(BookingViewModel.class);
        drawerLayout = findViewById(R.id.booking_drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        tabLayout = findViewById(R.id.tab_layout_booking);
        viewPager = findViewById(R.id.booking_viewpager);
        adapter = new BookingViewpagerAdapter(this);
        viewPager.setAdapter(adapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) { tabLayout.selectTab(tabLayout.getTabAt(position)); }
        });
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
        if(item.getItemId() == R.id.nav_dashboard) {
            Intent intent = new Intent(this, DashboardActivity.class);
            startActivity(intent);
            this.finish();
        } else if(item.getItemId() == R.id.nav_logout) {
            TokenManager.getInstance().clearToken();
            Intent intent = new Intent(this, AuthActivity.class);
            startActivity(intent);
            this.finish();
        }
        return true;
    }
}