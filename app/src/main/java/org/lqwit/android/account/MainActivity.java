package org.lqwit.android.account;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import org.lqwit.android.account.base.AppBaseActivity;
import org.lqwit.android.account.fragment.FundFlowFragment;
import org.lqwit.android.account.fragment.HomeAccountFragment;
import org.lqwit.android.account.fragment.HomeMineFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppBaseActivity {

    @BindView(R.id.home_view_pager)
    ViewPager homeViewPager;
    @BindView(R.id.home_tabLayout)
    TabLayout tabLayout;


    private static final String TAG = "MainActivity";

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        List<Fragment> homeFragments = new ArrayList<>();
        homeFragments.add(new FundFlowFragment());
        homeFragments.add(new HomeAccountFragment());
        homeFragments.add(new HomeMineFragment());
        homeViewPager.setAdapter(new HomePagerAdapter(getSupportFragmentManager(), homeFragments));
        tabLayout.setupWithViewPager(homeViewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home_black_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_dashboard_black_24dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_notifications_black_24dp);
    }

    class HomePagerAdapter extends FragmentStatePagerAdapter{

        List<Fragment> fragments;

        public HomePagerAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
