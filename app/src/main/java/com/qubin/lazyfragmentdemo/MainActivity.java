package com.qubin.lazyfragmentdemo;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager)findViewById(R.id.viewpager);
        List<Fragment> list = new ArrayList<>();
        list.add(new ViewPagerFragmentTwo());
        list.add(new ViewPagerFragmentThree());
        list.add(new ViewPagerFragmentFour());
        ViewPageAdapter viewPageAdapter = new ViewPageAdapter(getSupportFragmentManager(),list);
        viewPager.setAdapter(viewPageAdapter);
    }
}
