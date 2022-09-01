package ifes.eric.aprendaingles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import ifes.eric.aprendaingles.fragments.BichosFragment;
import ifes.eric.aprendaingles.fragments.NumerosFragment;
import ifes.eric.aprendaingles.fragments.VogaisFragment;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private SmartTabLayout smartTabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setElevation(0);

        smartTabLayout = findViewById(R.id.view_pagertab);
        viewPager = findViewById(R.id.view_pager);


        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add(R.string.titleA, BichosFragment.class)
                .add(R.string.titleB, NumerosFragment.class)
                .add(R.string.titleB, VogaisFragment.class)
                .create());

        viewPager.setAdapter(adapter);
        smartTabLayout.setViewPager(viewPager);

    }




}