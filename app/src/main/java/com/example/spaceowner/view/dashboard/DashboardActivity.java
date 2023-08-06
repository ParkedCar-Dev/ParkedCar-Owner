package com.example.spaceowner.view.dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;

import com.example.spaceowner.R;
import com.example.spaceowner.view.addspace.AddSpaceActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class DashboardActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager2 viewPager;
    DashboardViewpagerAdapter adapter;
    FloatingActionButton addSpaceButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

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
            public void onTabUnselected(TabLayout.Tab tab){

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab){

            }
        });

        addSpaceButton.setOnClickListener((v) -> {
//            go to add space activity
            Intent intent = new Intent(this, AddSpaceActivity.class);
            startActivity(intent);
            this.finish();
        });

    }
}