package com.lgh.coordinatorlayout.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.lgh.coordinatorlayout.BaseActivity;
import com.lgh.coordinatorlayout.R;
import com.lgh.coordinatorlayout.utils.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liguohui on 2019/11/29 09:33
 */
public class DetailActivity extends BaseActivity {
    @BindView(R.id.mIv)
    ImageView mIv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.nestedScrollView)
    NestedScrollView nestedScrollView;
    @BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(toolbar).init();

    }

    @Override
    public Toolbar initToolBar() {
        return toolbar;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_detail;
    }

    @Override
    protected void initView() {
//        collapsingToolbarLayout.setCollapsedTitleGravity(Gravity.CENTER);//设置收缩后标题的位置
        collapsingToolbarLayout.setExpandedTitleGravity(Gravity.CENTER);////设置展开后标题的位置
        collapsingToolbarLayout.setTitle("美景");//设置标题的名字
        appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                LogUtils.e(verticalOffset);
                //verticalOffset始终为0以下的负数
                float percent = (Math.abs(verticalOffset * 1.0f) / appBarLayout.getTotalScrollRange());

                toolbar.setBackgroundColor(changeAlpha(Color.WHITE, percent));
//                toolbar.setAlpha(percent);

                if (percent > 0.5) {
//                    toolbar.setTitleTextColor(Color.BLACK);//无效
                    collapsingToolbarLayout.setCollapsedTitleTextColor(Color.BLACK);//设置收缩后标题的颜色

                    setHomeAsUpIndicator(R.mipmap.iv_back_black);
                    mImmersionBar.statusBarDarkFont(true).init();
                } else {
                    collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);//设置收缩后标题的颜色
                    setHomeAsUpIndicator(R.mipmap.iv_back_white);
                    mImmersionBar.statusBarDarkFont(false).init();

                }

            }
        });
    }

    @Override
    protected void initData() {

    }

    /**
     * 根据百分比改变颜色透明度
     */
    public int changeAlpha(int color, float fraction) {
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        int alpha = (int) (Color.alpha(color) * fraction);
        return Color.argb(alpha, red, green, blue);
    }


}
