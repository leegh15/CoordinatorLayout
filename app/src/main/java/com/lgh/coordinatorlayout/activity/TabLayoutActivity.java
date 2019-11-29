package com.lgh.coordinatorlayout.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.lgh.coordinatorlayout.BaseActivity;
import com.lgh.coordinatorlayout.fragment.HotFragment;
import com.lgh.coordinatorlayout.R;
import com.lgh.coordinatorlayout.adapter.FmPagerAdapter;
import com.lgh.coordinatorlayout.fragment.RecommendFragment;
import com.lgh.coordinatorlayout.utils.LogUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liguohui on 2019/11/29 16:47
 */
public class TabLayoutActivity extends BaseActivity {
    @BindView(R.id.iv_bg)
    ImageView ivBg;
    @BindView(R.id.iv_useravator)
    ImageView ivUseravator;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.tv_author)
    TextView tvAuthor;
    @BindView(R.id.ll_username)
    LinearLayout llUsername;
    @BindView(R.id.ll_personal_me)
    RelativeLayout llPersonalMe;
    @BindView(R.id.lay_header)
    RelativeLayout layHeader;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private String[] titles = new String[]{"热门", "推荐","其他"};
    private ArrayList<Fragment> fragments = new ArrayList<>();

    @Override
    public Toolbar initToolBar() {
        return toolbar;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(toolbar).init();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tablayout;
    }

    @Override
    protected void initView() {
        actionBar.setDisplayHomeAsUpEnabled(false);
        init();
//        initAppbar();
    }

    @Override
    protected void initData() {
        tvTitle.setText(tvUsername.getText().toString());

    }

    private void init() {
        fragments.add(new HotFragment());
        fragments.add(new RecommendFragment());
        fragments.add(new RecommendFragment());
        for (int i = 0; i < titles.length; i++) {
            tablayout.addTab(tablayout.newTab());
        }

        viewpager.setAdapter(new FmPagerAdapter(fragments, getSupportFragmentManager()));
        tablayout.setupWithViewPager(viewpager);

        for (int j = 0; j < titles.length; j++) {
            tablayout.getTabAt(j).setText(titles[j]);
        }

//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.iv_bg_top);
//        ivBg.setImageBitmap(BlurImageViewUtils.BlurImages(bitmap));
        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                LogUtils.e(i);
                //verticalOffset始终为0以下的负数
                float percent = (Math.abs(i * 1.0f) / appBarLayout.getTotalScrollRange());

                tvTitle.setAlpha(percent);

            }
        });
    }



    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
