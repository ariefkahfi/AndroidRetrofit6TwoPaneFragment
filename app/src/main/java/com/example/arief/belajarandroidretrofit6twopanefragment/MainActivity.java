package com.example.arief.belajarandroidretrofit6twopanefragment;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TableLayout;
import android.widget.Toast;

import com.example.arief.belajarandroidretrofit6twopanefragment.adapters.ViewPagerAdapter;
import com.example.arief.belajarandroidretrofit6twopanefragment.tabfragments.TabOneFragment;

public class MainActivity extends AppCompatActivity {


    private ViewPager pager;
    private ViewPagerAdapter pagerAdapter;

    private TabLayout tab;
    private Toolbar tol1;


    int tolColor ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tol1 =(Toolbar)findViewById(R.id.tol1);
        setSupportActionBar(tol1);

        tab = (TabLayout)findViewById(R.id.tabLayout);
        pager = (ViewPager)findViewById(R.id.viewPager);


        pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        pager.setAdapter(pagerAdapter);
        tab.setupWithViewPager(pager);


        tolColor = ((ColorDrawable)tol1.getBackground()).getColor();


        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));

        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
               buatAnimasi(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void buatAnimasi(int tabPosition){

        int toolColor = tolColor;
        int tabColor = getColorForTab(tabPosition);
        ValueAnimator animator = ValueAnimator.ofObject(new ArgbEvaluator(),toolColor,tabColor);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int)animation.getAnimatedValue();

                tol1.setBackgroundColor(value);
                tab.setBackgroundColor(value);

            }
        });
        animator.setDuration(500);
        animator.start();
    }


    private int getColorForTab(int tabPosition){
         switch (tabPosition){
             case 0 :
                 //return getResources().getColor(R.color.colorPrimaryDark);
                 return   Color.parseColor("#008FCC");
             case 1 :
                 return getResources().getColor(R.color.colorAccent);
         }
         return 0;
    }

}
