package com.kr.hs.gmma;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<MealListItem> mMealDataset = new ArrayList<>();
    public static ArrayList<NoticeListItem> mNoticeDataset = new ArrayList<>();
    private TabLayout tabLayout;
    private ViewPager viewPager;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mDate;
        public TextView mInfo;
        public LinearLayout mToday;

        public ViewHolder(View view) {
            super(view);
            mDate = (TextView) view.findViewById(R.id.sub_text);
            mInfo = (TextView)view.findViewById(R.id.main_text);
            mToday = (LinearLayout)view.findViewById(R.id.today_check);
        }
    }

    public static class NoticeViewHolder extends RecyclerView.ViewHolder {
        public final Context mContext;
        public TextView mDate;
        public TextView mTitle;
        public TextView mWriter;
        public Button mButton;

        public NoticeViewHolder(View view) {
            super(view);
            mContext = view.getContext();
            mDate = (TextView)view.findViewById(R.id.date_text);
            mTitle = (TextView)view.findViewById(R.id.notice_title_text);
            mWriter = (TextView)view.findViewById(R.id.writer_text);
            mButton = (Button)view.findViewById(R.id.in_web_notice_btn);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Adding Toolbar to the activity
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initializing the TabLayout
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("홈"));
        tabLayout.addTab(tabLayout.newTab().setText("공지"));
        tabLayout.addTab(tabLayout.newTab().setText("급식표"));
        tabLayout.addTab(tabLayout.newTab().setText("커뮤니티"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        // Initializing ViewPager
        viewPager = (ViewPager) findViewById(R.id.pager);

        // Creating TabPagerAdapter adapter
        TabPagerAdapter pagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        // Set TabSelectedListener
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //
            }
        });
    }

    @Override
    public void onDestroy(){
        IntroActivity.mDBManager.close();
        super.onDestroy();
    }
}
