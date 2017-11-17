package org.lqwit.android.account;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import org.lqwit.android.R;
import org.lqwit.android.account.list.AccountListFragment;
import org.lqwit.android.global.base.AppBaseActivity;
import org.lqwit.android.other.mine.MineFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppBaseActivity{

    @BindView(R.id.home_viewpager)
    ViewPager homeViewPager;
    @BindView(R.id.home_menu)
    BottomNavigationView homeMenu;

    public static Intent newIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(HomeFragment.newInstance());
        fragmentList.add(AccountListFragment.newInstance());
        fragmentList.add(StatisticsFragment.newInstance());
        fragmentList.add(MineFragment.newInstance());
        homeViewPager.setAdapter(new HomeAdapter(getSupportFragmentManager(),
                fragmentList));
    }




    @Override
    public void initListener() {
        homeViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        homeMenu.setBackgroundResource(android.R.color.transparent);
                        homeMenu.setSelectedItemId(R.id.home_one);
                        break;
                    case 1:
                        homeMenu.setBackgroundResource(android.R.color.white);
                        homeMenu.setSelectedItemId(R.id.home_two);
                        break;
                    case 2:
                        homeMenu.setBackgroundResource(android.R.color.white);
                        homeMenu.setSelectedItemId(R.id.home_three);
                        break;
                    case 3:
                        homeMenu.setBackgroundResource(android.R.color.white);
                        homeMenu.setSelectedItemId(R.id.home_four);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        homeMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home_one:
                        homeViewPager.setCurrentItem(0, false);
                        return true;
                    case R.id.home_two:
                        homeViewPager.setCurrentItem(1, false);
                        return true;
                    case R.id.home_three:
                        homeViewPager.setCurrentItem(2, false);
                        return true;
                    case R.id.home_four:
                        homeViewPager.setCurrentItem(3, false);
                        return true;
                }
                return false;
            }
        });
    }

    class HomeAdapter extends FragmentStatePagerAdapter {

        private List<Fragment> fragmentList;

        public HomeAdapter(FragmentManager fm, List<Fragment> fragmentList) {
            super(fm);
            this.fragmentList = fragmentList;
        }


        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }
}
