package com.lgh.coordinatorlayout;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.gyf.immersionbar.ImmersionBar;

import butterknife.ButterKnife;

/**
 * Activity基类
 *
 * @author geyifeng
 * @date 2017/5/9
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected String mTag = this.getClass().getSimpleName();

    protected Activity mActivity;
    protected ActionBar actionBar;
    protected ImmersionBar mImmersionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getInstance().addActivity(this);
        mActivity = this;
        setContentView(getLayoutId());
        //绑定控件
        ButterKnife.bind(this);
        //初始化沉浸式
        initImmersionBar();

        if (null != initToolBar()) {
            setSupportActionBar(initToolBar());
            initActionBar();
        }
        //view与数据绑定
        initView();
        //初始化数据
        initData();
        //设置监听
        setListener();
    }
    public void initActionBar() {
        actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.mipmap.iv_back_white);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true); //设置返回键可用
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setElevation(0);
    }
    public void setHomeAsUpIndicator(int resid){
        actionBar.setHomeAsUpIndicator(resid);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().removeActivity(this);
    }

    /**
     * 子类设置布局Id
     *
     * @return the layout id
     */
    protected abstract int getLayoutId();

    /**
     * 初始化沉浸式
     * Init immersion bar.
     */
    protected void initImmersionBar() {
        //设置共同沉浸式样式
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();
    }
    /**
     * 初始化toolBar
     *
     * @return
     */
    public Toolbar initToolBar() {
        return null;
    }

    protected abstract void initView();

    protected abstract void initData();


    protected void setListener() {
    }
}
