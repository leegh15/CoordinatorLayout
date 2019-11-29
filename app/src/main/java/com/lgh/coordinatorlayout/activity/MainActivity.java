package com.lgh.coordinatorlayout.activity;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.gyf.immersionbar.ImmersionBar;
import com.lgh.coordinatorlayout.BaseActivity;
import com.lgh.coordinatorlayout.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.btn_detail)
    Button btnDetail;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.btn_tab)
    Button btnTab;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        ImmersionBar.with(this).titleBar(toolbar).init();

    }

    @Override
    public Toolbar initToolBar() {
        return toolbar;
    }

    @Override
    public void initActionBar() {
        super.initActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        toolbar.setTitleTextColor(Color.WHITE);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }



    @OnClick({R.id.btn_detail, R.id.btn_tab})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_detail:
                Intent intent = new Intent(this, DetailActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_tab:
                Intent intent_tab = new Intent(this, TabLayoutActivity.class);
                startActivity(intent_tab);
                break;
        }
    }
}
