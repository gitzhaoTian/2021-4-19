package com.example.day10lianxi;

import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.day10lianxi.adapter.FragmentAdapter;
import com.example.day10lianxi.fragment.HomeFragment;
import com.example.day10lianxi.fragment.MyFragment;
import com.example.day10lianxi.fragment.MyLoveFragment;
import com.example.mymvplibrary.ui.BaseActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.cl_main)
    ConstraintLayout clMain;
    @BindView(R.id.nv_main)
    NavigationView nvMain;
    @BindView(R.id.dl_main)
    DrawerLayout dlMain;
    private MyLoveFragment myLoveFragment;
    private MyFragment myFragment;
    private HomeFragment homeFragment;

    @Override
    protected void initListener() {
        dlMain.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                clMain.setX(drawerView.getRight());
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        nvMain.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.item1:
                        vp.setCurrentItem(0);
                        dlMain.closeDrawer(Gravity.LEFT);
//                        tab.getTabAt(1).select();
                        break;
                    case R.id.item2:
                        vp.setCurrentItem(1);
                        dlMain.closeDrawer(Gravity.LEFT);
                        break;
                    case R.id.item3:
                        vp.setCurrentItem(2);
                        dlMain.closeDrawer(Gravity.LEFT);
                        break;
                }
                return false;
            }
        });
    }

    @Override

    protected void initData() {
        ArrayList<Fragment> list = new ArrayList<>();
        ArrayList<String> strings = new ArrayList<>();
        strings.add("首页");
        strings.add("收藏");
        strings.add("我的");
        list.add(homeFragment);
        list.add(myLoveFragment);
        list.add(myFragment);
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), list, strings);
        vp.setAdapter(adapter);
        tab.setupWithViewPager(vp);
    }

    @Override
    protected void initView() {
        homeFragment = new HomeFragment();
        myLoveFragment = new MyLoveFragment();
        myFragment = new MyFragment();

        toolbar.setTitle("");
        nvMain.setItemIconTintList(null);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, dlMain, toolbar, R.string.app_name, R.string.app_name);
        dlMain.addDrawerListener(toggle);
        toggle.syncState();
        setSupportActionBar(toolbar);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_icon,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
